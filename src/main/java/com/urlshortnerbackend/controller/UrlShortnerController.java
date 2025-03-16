package com.urlshortnerbackend.controller;

import com.urlshortnerbackend.dto.UrlRequest;
import com.urlshortnerbackend.dto.UrlResponse;
import com.urlshortnerbackend.service.UrlShortnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "${frontend.url}")
@AllArgsConstructor
public class UrlShortnerController {
  private final UrlShortnerService urlShortnerService;

  @PostMapping("/shorten")
  public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest request) {
    UrlResponse res = urlShortnerService.shortenUrl(request);

    return ResponseEntity.ok(res);
  }
}
