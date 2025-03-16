package com.urlshortnerbackend.controller;

import com.urlshortnerbackend.service.UrlShortnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
public class RedirectController {

  private final UrlShortnerService urlShortnerService;

  @GetMapping("/{key}")
  public RedirectView redirect(@PathVariable String key) {
    String url = urlShortnerService.getOriginalUrl(key);

    RedirectView redirectView = new RedirectView(url);
    redirectView.setStatusCode(HttpStatus.FOUND);
    return redirectView;
  }
}
