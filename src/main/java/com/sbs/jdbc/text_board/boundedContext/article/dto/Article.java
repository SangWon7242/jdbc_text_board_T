package com.sbs.jdbc.text_board.boundedContext.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Article {
  private final int id;
  private String regDate;
  private String updateDate;
  private int memberId;
  private String subject;
  private String content;
  private int hit;

  private String writerName;

  public Article(Map<String, Object> articleMap) {
    this.id = (int) articleMap.get("id");
    this.regDate = (String) articleMap.get("regDate");
    this.updateDate = (String) articleMap.get("updateDate");
    this.memberId = (int) articleMap.get("memberId");
    this.subject = (String) articleMap.get("subject");
    this.content = (String) articleMap.get("content");
    this.hit = (int) articleMap.get("hit");

    if(articleMap.get("writerName") != null) {
      this.writerName = (String) articleMap.get("writerName");
    }
  }
}
