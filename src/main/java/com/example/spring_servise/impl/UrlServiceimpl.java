package com.example.spring_servise.impl;

import com.example.spring_servise.Service.*;
import com.example.spring_servise.dto.StatisticDto;
import com.example.spring_servise.model.Url;
import com.example.spring_servise.utils.Base62;
import com.example.spring_servise.utils.Base62Exception;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.spring_servise.utils.Base62.to;

@Slf4j
@Service
@Data
@Transactional
public class UrlServiceimpl<UrlRepository>
        private final UrlRepository repository;

        @Autowired

        public UrlServiceimpl(UrlRepository repository) {
            this.repository = repository;
        }


        @Override
        public String getShortUrl(String original) {
            Url url = repository.findByOriginal(original).orElse(new Url(original));
            repository.save(url);
            String link = to(url.getId());

            log.info("The short link '/l/{}' is derived from the url {} ", link, original);

            this.toRank();

            return link;
        }

        @Override
        public String doRedirect(String link) {
            try {
                int id = Base62.from(link);
                Url url = repository
                        .findById(id)
                        .orElseThrow(() -> new LinkNotFoundException(link));

                url.setCount(url.getCount() + 1);
                repository.save(url);

                log.info("New redirection made by short link {}", link);

                this.toRank();

                return url.getOriginal();
            } catch (Base62Exception e) {
                throw new WrongLinkException(link, e);
            }
        }

        @Override
        public List<StatisticDto> getRankedUrl(Pageable pageable) {
            List<StatisticDto> result = new ArrayList<>();
            repository.findAll(pageable).get()
                    .forEach(url -> result.add(new StatisticDto(url))
                    );
            return result;
        }

        void toRank() {
            List<Url> list = repository.findAll();

            repository.findAll()
                    .forEach(url -> {
                        url.setRank(list.indexOf(url) + 1);
                    });
        }


        @Override
        public StatisticDto getRankedUrlByShort(String link) {

            try {
                Url url = repository.findById(Base62.from(link))
                        .orElseThrow(() -> new LinkNotFoundException(link));
                return new StatisticDto(url);

            } catch (Base62Exception e) {
                throw new WrongLinkException(link, e);

