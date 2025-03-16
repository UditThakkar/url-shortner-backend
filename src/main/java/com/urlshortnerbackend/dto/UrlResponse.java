package com.urlshortnerbackend.dto;

import lombok.Data;

@Data
public class UrlResponse {
  private String url;
  private String shortUrl;
  private String key;
}
