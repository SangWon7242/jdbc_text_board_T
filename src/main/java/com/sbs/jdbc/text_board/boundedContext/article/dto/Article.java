package com.sbs.jdbc.text_board.boundedContext.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Article {
  private final int id;
  private LocalDateTime regDate;
  private LocalDateTime updateDate;
  private String subject;
  private String content;
}
