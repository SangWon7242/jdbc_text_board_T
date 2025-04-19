package com.sbs.jdbc.text_board;

import com.sbs.jdbc.text_board.base.Rq;
import com.sbs.jdbc.text_board.boundedContext.article.controller.ArticleController;
import com.sbs.jdbc.text_board.container.Container;
import com.sbs.jdbc.text_board.dbUtil.MysqlUtil;

public class App {
  private static boolean isDevMode() {
    // 이 부분을 false로 바꾸면 production 모드 이다.
    // true는 개발자 모드이다.(개발할 때 좋다.)
    return true;
  }

  public void run() {
    System.out.println("== 자바 텍스트 게시판 시작 ==");

    try {
      while (true) {
        System.out.print("명령어) ");
        String cmd = Container.sc.nextLine();

        Rq rq = new Rq();
        rq.setCommand(cmd);

        // DB 세팅
        // root, ""
        MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "text_board");
        MysqlUtil.setDevMode(isDevMode());
        // DB 끝

        doAction(rq);
      }
    } finally {
      System.out.println("== 프로그램을 종료합니다. ==");
      Container.sc.close();
    }

  }

  private void doAction(Rq rq) {
    ArticleController articleController = Container.articleController;

    if (rq.getUrlPath().equals("/usr/article/write")) {
      articleController.doWrite();
    } else if (rq.getUrlPath().equals("/usr/article/list")) {
      articleController.showList();
    } else if (rq.getUrlPath().equals("/usr/article/detail")) {
      articleController.showDetail(rq);
    } else if (rq.getUrlPath().equals("/usr/article/modify")) {
      articleController.doModify(rq);
    } else if (rq.getUrlPath().equals("/usr/article/delete")) {
      articleController.doDelete(rq);
    } else if (rq.getUrlPath().equals("exit")) {
      System.out.println("프로그램을 종료합니다.");
      System.exit(0); // 프로그램 강제종룔
    } else {
      System.out.println("잘못 입력 된 명령어입니다.");
    }
  }
}

