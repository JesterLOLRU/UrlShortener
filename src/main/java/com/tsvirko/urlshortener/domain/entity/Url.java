package com.tsvirko.urlshortener.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "url")
public class Url extends BaseEntity{

    @Column(name = "base_url")
    private String baseUrl;

    @Column(name = "processed_url")
    private String processedUrl;

}
