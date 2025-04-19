package com.sbs.jdbc.text_board;

import com.sbs.jdbc.text_board.boundedContext.article.dto.Article;
import com.sbs.jdbc.text_board.container.Container;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  public Scanner sc;
  public int lastId;

  public List<Article> articles;

  public App() {
    sc = Container.sc;
    lastId = 0;
    articles = new ArrayList<>();
  }

  public void run() {
    System.out.println("== 자바 텍스트 게시판 시작 ==");

    String url = "jdbc:mysql://127.0.0.1:3306/text_board?useSSL=false&autoReconnect=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useUnicode=true&allowPublicKeyRetrieval=true";
    String username = "sbsst";     // 데이터베이스 계정
    String password = "sbs123414";  // 데이터베이스 비밀번호

    Connection conn = null;
    PreparedStatement pstmt = null;
    
    while (true) {
      System.out.print("명령어) ");
      String cmd = sc.nextLine();

      if(cmd.equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");

        System.out.print("제목 : ");
        String subject = sc.nextLine();

        if (subject.trim().isEmpty()) {
          System.out.println("subject(을)를 입력해주세요.");
          continue;
        }

        System.out.print("내용 : ");
        String content = sc.nextLine();

        if (content.trim().isEmpty()) {
          System.out.println("content(을)를 입력해주세요.");
          continue;
        }

        int id = ++lastId;

        Article article = new Article(id, subject, content);
        articles.add(article);
        System.out.println("생성 된 게시물 객체 : " + article);

        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          System.out.println("드라이버 로드 성공!");

          conn = DriverManager.getConnection(url, username, password);
          System.out.println("데이터베이스 연결 성공!");

          // 4. SQL 쿼리 준비
          // -> 통역사에게 전달할 메시지(SQL)를 준비합니다.
          String sql = "INSERT INTO article";
          sql += " SET regDate = NOW()";
          sql += ", updateDate = NOW()";
          sql += ", subject = '%s'".formatted(subject);
          sql += ", content = '%s'".formatted(content);

          System.out.println(sql);

          // 5. PreparedStatement 생성
          // -> 준비한 SQL 메시지를 통역사에게 전달할 준비를 합니다.
          pstmt = conn.prepareStatement(sql);

          // 6. SQL 실행
          // -> 통역사를 통해 데이터베이스에 메시지를 전달합니다.
          int result = pstmt.executeUpdate();

          System.out.printf("실행 된 결과 : %d\n", result);

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
            if(pstmt != null && !pstmt.isClosed()) pstmt.close();
            if(conn != null && !conn.isClosed()) {
              conn.close();
              System.out.println("데이터베이스 연결 종료!");
            }
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }

        System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
      }
      else if(cmd.equals("/usr/article/list")) {
        System.out.println("== 게시물 리스트 ==");
      }
      else if(cmd.equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
      else {
        System.out.println("잘못 입력 된 명령어입니다.");
      }
    }

    System.out.println("== 자바 텍스트 게시판 종료 ==");
    sc.close();
  }
}
