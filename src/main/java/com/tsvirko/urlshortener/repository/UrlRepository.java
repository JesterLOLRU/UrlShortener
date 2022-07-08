package com.tsvirko.urlshortener.repository;

import com.tsvirko.urlshortener.domain.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlRepository extends JpaRepository<Url, UUID> {
    Optional<Url> findByBaseUrl(String url);
    Optional<Url> findByProcessedUrl(String url);
}
