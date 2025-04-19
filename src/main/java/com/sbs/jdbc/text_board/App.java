package com.sbs.jdbc.text_board;

import com.sbs.jdbc.text_board.boundedContext.article.dto.Article;
import com.sbs.jdbc.text_board.container.Container;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  public Scanner sc;
  public int lastId;
  public List<Article> articles;

  final String url = "jdbc:mysql://127.0.0.1:3306/text_board?useSSL=false&autoReconnect=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useUnicode=true&allowPublicKeyRetrieval=true";
  final String username = "sbsst";     // 데이터베이스 계정
  final String password = "sbs123414";  // 데이터베이스 비밀번호

  Connection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  public App() {
    sc = Container.sc;
    lastId = 0;
    articles = new ArrayList<>();
  }

  public void run() {
    System.out.println("== 자바 텍스트 게시판 시작 ==");

    while (true) {
      System.out.print("명령어) ");
      String cmd = sc.nextLine();

      try {
        // 드라이버 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");

        // DB 연결
        conn = DriverManager.getConnection(url, username, password);

        doAction(cmd);

      } catch (ClassNotFoundException e) {
        // JDBC 드라이버를 찾지 못했을 때의 처리
        System.out.println("DB 드라이버 로드 실패!");
        e.printStackTrace();
      } catch (SQLException e) {
        // SQL 관련 오류가 발생했을 때의 처리
        System.out.println("DB 연결 또는 SQL 실행 실패!");
        e.printStackTrace();
      } finally {
        try {
          if(conn != null && !conn.isClosed()) {
            conn.close();
            System.out.println("데이터베이스 연결 종료!");
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void doAction(String cmd) {
    if(cmd.equals("/usr/article/write")) {
      System.out.println("== 게시물 작성 ==");

      System.out.print("제목 : ");
      String subject = sc.nextLine();

      if (subject.trim().isEmpty()) {
        System.out.println("subject(을)를 입력해주세요.");
        return;
      }

      System.out.print("내용 : ");
      String content = sc.nextLine();

      if (content.trim().isEmpty()) {
        System.out.println("content(을)를 입력해주세요.");
        return;
      }

      try {
        // 4. SQL 쿼리 준비
        // -> 통역사에게 전달할 메시지(SQL)를 준비합니다.
        String sql = "INSERT INTO article";
        sql += " SET regDate = NOW()";
        sql += ", updateDate = NOW()";
        sql += ", subject = '%s'".formatted(subject);
        sql += ", content = '%s'".formatted(content);

        System.out.println(sql);

        System.out.println("게시물이 생성되었습니다.");

        pstmt = conn.prepareStatement(sql);

        // SQL 실행
        pstmt.executeUpdate();

      } catch (SQLException e) {
        // SQL 관련 오류가 발생했을 때의 처리
        System.out.println("DB 연결 또는 SQL 실행 실패!");
        e.printStackTrace();
      } finally {
        try {
          if(pstmt != null && !pstmt.isClosed()) pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    else if(cmd.equals("/usr/article/list")) {
      try {
        String sql = "SELECT *";
        sql += " FROM article";
        sql += " ORDER BY id DESC";

        System.out.println(sql);

        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        // 결과 처리
        while (rs.next()) {
          int id = rs.getInt("id");
          LocalDateTime regDate = rs.getTimestamp("regDate").toLocalDateTime();
          LocalDateTime updateDate = rs.getTimestamp("updateDAte").toLocalDateTime();
          String subject = rs.getString("subject");
          String content = rs.getString("content");

          Article article = new Article(id, regDate, updateDate, subject, content);
          articles.add(article);
        }

        System.out.println("== 게시물 리스트 ==");
        System.out.println("제목 | 내용");
        articles.forEach(
            article -> System.out.printf("%d | %s\n", article.getId(), article.getSubject())
        );


      } catch (SQLException e) {
        // SQL 관련 오류가 발생했을 때의 처리
        System.out.println("DB 연결 또는 SQL 실행 실패!");
        e.printStackTrace();
      } finally {
        try {
          if(rs != null && !rs.isClosed()) rs.close();
          if(pstmt != null && !pstmt.isClosed()) pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }

    }
    else if(cmd.equals("exit")) {
      System.out.println("프로그램을 종료합니다.");
      System.exit(0); // 프로그램 강제종룔
    }
    else {
      System.out.println("잘못 입력 된 명령어입니다.");
    }
  }
}
