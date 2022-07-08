package com.tsvirko.urlshortener.service.serviceImpl;

import com.tsvirko.urlshortener.domain.entity.Url;
import com.tsvirko.urlshortener.repository.UrlRepository;
import com.tsvirko.urlshortener.service.ShortenerUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.net.URI;
import java.security.SecureRandom;

@Service
public class ShortenerUrlServiceImpl implements ShortenerUrlService {

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    @Autowired
    private UrlRepository urlRepository;

    public String shortURL(String url) {
        String processedUrl = generateURL();
        if (urlRepository.findByBaseUrl(url).isEmpty()) {
            Url urlObject = new Url();
            urlObject.setBaseUrl(url);
            urlObject.setProcessedUrl(processedUrl);
            urlRepository.saveAndFlush(urlObject);
        } else {
            var urlObjectEntity = urlRepository.findByBaseUrl(url);
            var urlObject = urlObjectEntity.get();
            urlObject.setProcessedUrl(processedUrl);
            urlRepository.saveAndFlush(urlObject);
        }
        return "http://localhost:8080/url/" + processedUrl;
    }

    public String generateURL() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public ResponseEntity<Void> getUrlForRedirect(String url) {
        var urlEntity = urlRepository.findByProcessedUrl(url);
        if (urlEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(urlEntity.get().getBaseUrl()))
                    .build();
        }
    }
}
