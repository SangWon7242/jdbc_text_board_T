package com.sbs.jdbc.text_board;

import com.sbs.jdbc.text_board.container.Container;

import java.util.Scanner;

public class App {
  public Scanner sc;
  public int lastId;

  public App() {
    sc = Container.sc;
    lastId = 0;
  }

  public void run() {
    System.out.println("== 자바 텍스트 게시판 시작 ==");
    
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
