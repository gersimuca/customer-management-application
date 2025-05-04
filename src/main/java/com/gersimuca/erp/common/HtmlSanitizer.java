package com.gersimuca.erp.common;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Component;

/**
 * HtmlSanitizer is a Spring component that provides functionality to sanitize HTML content. It uses
 * the Jsoup library to clean the input text by removing any HTML tags and content.
 */
@Component
public class HtmlSanitizer {

  /**
   * Sanitizes the given text by removing all HTML tags and content.
   *
   * <p>This method uses Jsoup to clean the input text. It sets the output settings to not pretty
   * print, which means it will not format the output with additional whitespace or newlines.
   * Newline characters (\n) will be preserved in the sanitized text.
   *
   * @param text the input text to be sanitized
   * @return the sanitized text with all HTML tags removed
   */
  public String sanitize(String text) {
    final Document.OutputSettings outputSettings = new Document.OutputSettings();
    outputSettings.prettyPrint(false);
    return Jsoup.clean(StringUtils.defaultString(text), EMPTY, Safelist.none(), outputSettings);
  }
}
