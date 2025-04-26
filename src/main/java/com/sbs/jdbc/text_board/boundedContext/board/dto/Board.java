package com.sbs.jdbc.text_board.boundedContext.board.dto;

import com.sbs.jdbc.text_board.boundedContext.common.baseDto.BaseDto;
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
public class Board extends BaseDto {
  private int id;
  private String code;
  private String name;

  public Board(Map<String, Object> boardMap) {
    this.id = (int) boardMap.get("id");
    this.regDate = (LocalDateTime) boardMap.get("regDate");
    this.updateDate = (LocalDateTime) boardMap.get("updateDate");
    this.code = (String) boardMap.get("code");
    this.name = (String) boardMap.get("name");
  }
}
