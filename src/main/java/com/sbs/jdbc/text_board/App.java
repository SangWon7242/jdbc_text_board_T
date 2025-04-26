package com.sbs.jdbc.text_board;

import com.sbs.jdbc.text_board.boundedContext.common.Controller;
import com.sbs.jdbc.text_board.boundedContext.member.dto.Member;
import com.sbs.jdbc.text_board.global.base.Rq;
import com.sbs.jdbc.text_board.boundedContext.article.controller.ArticleController;
import com.sbs.jdbc.text_board.boundedContext.member.controller.MemberController;
import com.sbs.jdbc.text_board.container.Container;
import com.sbs.jdbc.text_board.global.util.dbUtil.MysqlUtil;

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
        Rq rq = new Rq();

        Member member = rq.getLoginedMember();

        String promptName = "명령어";
        
        if(member != null) {
          promptName = member.getUsername();
        }

        System.out.printf("%s) ", promptName);
        String cmd = Container.sc.nextLine();

        if(cmd.equals("exit")) {
          break;
        }

        rq.setCommand(cmd); // 명령어 전달

        // DB 세팅
        MysqlUtil.setDBInfo("localhost", "root", "", "text_board");
        MysqlUtil.setDevMode(isDevMode());
        // DB 끝

        rq.getActionPath();

        Controller controller = getControllerByRequestUri(rq);

        if(controller != null) {
          controller.performAction(rq);
        }

      }
    } finally {
      System.out.println("== 프로그램을 종료합니다. ==");
      Container.sc.close();
    }
  }

  private Controller getControllerByRequestUri(Rq rq) {
    switch (rq.getControllerTypeCode()) {
      case "usr":
        switch (rq.getControllerName()) {
          case "article":
            return Container.articleController;
          case "member":
            return Container.memberController;
        }
      case "exit":
        System.out.println("프로그램을 종료합니다.");
    }

    return null;
  }
}

