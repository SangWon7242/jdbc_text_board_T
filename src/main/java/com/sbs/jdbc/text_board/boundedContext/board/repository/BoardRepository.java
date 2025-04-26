package com.sbs.jdbc.text_board.boundedContext.board.repository;

import com.sbs.jdbc.text_board.boundedContext.article.dto.Article;
import com.sbs.jdbc.text_board.boundedContext.board.dto.Board;
import com.sbs.jdbc.text_board.global.util.dbUtil.MysqlUtil;
import com.sbs.jdbc.text_board.global.util.dbUtil.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardRepository {
  public void makeBoard(String code, String name) {
    SecSql sql = new SecSql();
    sql.append("INSERT INTO board");
    sql.append(" SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", `code` = ?", code);
    sql.append(", `name` = ?", name);

    MysqlUtil.insert(sql);
  }

  public Board findByBoardName(String name) {
    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM board");
    sql.append("WHERE name = ?", name);

    Map<String, Object> boardMap = MysqlUtil.selectRow(sql);

    if(boardMap.isEmpty()) return null;

    return new Board(boardMap);
  }

  public Board findByBoardCode(String code) {
    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM board");
    sql.append("WHERE code = ?", code);

    Map<String, Object> boardMap = MysqlUtil.selectRow(sql);

    if(boardMap.isEmpty()) return null;

    return new Board(boardMap);
  }

  public List<Board> findAll() {
    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM board");
    sql.append("ORDER BY id DESC");

    List<Map<String, Object>> boardListMap = MysqlUtil.selectRows(sql);

    if(boardListMap.isEmpty()) return null;

    List<Board> boards = new ArrayList<>();

    for(Map<String, Object> boardMap : boardListMap) {
      boards.add(new Board(boardMap));
    }

    return boards;
  }

  public Board findById(int id) {
    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM board");
    sql.append("WHERE id = ?", id);

    Map<String, Object> boardMap = MysqlUtil.selectRow(sql);

    if(boardMap.isEmpty()) return null;

    return new Board(boardMap);
  }

  public List<Board> findAllWithArticleCount() {
    SecSql sql = new SecSql();
    sql.append("SELECT B.*,");
    sql.append("COUNT(A.id) AS articleCount");  // 게시물 수 카운트
    sql.append("FROM board AS B");
    sql.append("LEFT JOIN article AS A");  // LEFT JOIN으로 게시물이 없는 게시판도 포함
    sql.append("ON B.id = A.boardId");
    sql.append("GROUP BY B.id");  // 게시판별 그룹화
    sql.append("ORDER BY B.id ASC");

    List<Map<String, Object>> boardListMap = MysqlUtil.selectRows(sql);

    if(boardListMap.isEmpty()) return new ArrayList<>();

    List<Board> boards = new ArrayList<>();
    for(Map<String, Object> boardMap : boardListMap) {
      boards.add(new Board(boardMap));
    }

    return boards;
  }
}
