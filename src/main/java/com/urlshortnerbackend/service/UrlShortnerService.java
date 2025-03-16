package com.urlshortnerbackend.service;

import com.urlshortnerbackend.dto.UrlRequest;
import com.urlshortnerbackend.dto.UrlResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class UrlShortnerService {

  private ValueOperations<String, String> redisTemplate;
  private static final String BACKEND_URL = "https://url-shortner-app-production.up.railway.app/";

  public UrlResponse shortenUrl(UrlRequest request) {
    UrlResponse res = new UrlResponse();
    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    byte[] hash = digest.digest(request.getUrl().getBytes());
    String hexString = base62encode(hash, 6);

    redisTemplate.set(hexString, request.getUrl());
    res.setKey(hexString);
    res.setShortUrl(BACKEND_URL + hexString);
    res.setUrl(request.getUrl());
    return res;
  }

  public String getOriginalUrl(String key) {
    return redisTemplate.get(key);
  }

  public String base62encode(byte[] hash, int length) {
    String base62Chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder shortKey = new StringBuilder();

    for(int i = 0; i < length; i++){
      int index = hash[i] & 0xff;
      shortKey.append(base62Chars.charAt(index % 62));
    }

    return shortKey.toString();
  }
}
