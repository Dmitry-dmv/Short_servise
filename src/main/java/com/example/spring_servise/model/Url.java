package com.example.spring_servise.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        indexes = {
                @Index(name = "url_redirects_index", columnList = "redirects DESC")
        }
)
public class Url {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer redirects = 0;

    @NotNull
    @Column(nullable = false, length = 2000)
    private String original;

    public Url(String original) {
        this.original = original;
    }
}
