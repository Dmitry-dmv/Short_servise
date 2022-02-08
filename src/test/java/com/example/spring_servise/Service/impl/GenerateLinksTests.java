package com.example.spring_servise.Service.impl;

import com.example.spring_servise.Service.GetLinkService;
import com.example.spring_servise.dto.RequestOriginalLink;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GenerateLinksTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetLinkService getLinkService;

    @BeforeEach
    public void setUp() {
        when(getLinkService.generateShortLink("https://qwe.wer.asd/qwe/asd/zxc?param=1"))
                .thenReturn("C59A2aJ");
        when(getLinkService.getOriginalLink("C59A2aJ"))
                .thenReturn("https://qwe.wer.asd/qwe/asd/zxc?param=1");
        when(getLinkService.generateShortLink("https://qwe.wer.asd/qwe/asd/zxc?param=2"))
                .thenReturn("C59rPL8");
        when(getLinkService.getOriginalLink("C59rPL8"))
                .thenReturn("https://qwe.wer.asd/qwe/asd/zxc?param=2");
    }

    @Test
    public void return_correctShortLink()  throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/generate")
                        .content(objectMapper.writeValueAsString(RequestOriginalLink.builder()
                                .original("https://qwe.wer.asd/qwe/asd/zxc?param=1").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.link", is("/l/C59A2aJ")));

        mockMvc.perform(post("/generate")
                        .content(objectMapper.writeValueAsString(RequestOriginalLink.builder()
                                .original("https://qwe.wer.asd/qwe/asd/zxc?param=2").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.link", is("/l/C59rPL8")));

    }

    @Test
    public void return_and_redirect_correctOriginalLink() throws Exception {
        mockMvc.perform(get("/l/C59A2aJ"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("https://qwe.wer.asd/qwe/asd/zxc?param=1"));
        mockMvc.perform(get("/l/C59rPL8"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("https://qwe.wer.asd/qwe/asd/zxc?param=2"));
    }

}