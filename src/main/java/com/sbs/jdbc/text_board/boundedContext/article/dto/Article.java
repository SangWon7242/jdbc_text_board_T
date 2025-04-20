package com.sbs.jdbc.text_board.boundedContext.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Article {
  private final int id;
  private String regDate;
  private String updateDate;
  private String subject;
  private String content;

  public Article(Map<String, Object> articleMap) {
    this.id = (int) articleMap.get("id");
    this.regDate = (String) articleMap.get("regDate");
    this.updateDate = (String) articleMap.get("updateDate");
    this.subject = (String) articleMap.get("subject");
    this.content = (String) articleMap.get("content");
  }
}
