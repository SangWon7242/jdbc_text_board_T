package com.sbs.jdbc.text_board.boundedContext.board.controller;

import com.sbs.jdbc.text_board.boundedContext.board.boardService.BoardService;
import com.sbs.jdbc.text_board.boundedContext.board.dto.Board;
import com.sbs.jdbc.text_board.boundedContext.common.controller.Controller;
import com.sbs.jdbc.text_board.boundedContext.member.dto.Member;
import com.sbs.jdbc.text_board.container.Container;
import com.sbs.jdbc.text_board.global.base.Rq;

import java.util.List;

public class BoardController implements Controller {

  private BoardService boardService;

  public BoardController() {
    boardService = Container.boardService;
  }

  @Override
  public void performAction(Rq rq) {
    if (rq.getActionPath().equals("/usr/board/makeBoard")) {
      doMakeBoard(rq);
    } if (rq.getActionPath().equals("/usr/board/list")) {
      showBoardList(rq);
    } else {
      System.out.println("존재하지 않는 명령어입니다.");
    }
  }

  private void showBoardList(Rq rq) {
    System.out.println("== 게시판 목록 ==");

    System.out.println("번호 | 이름 | 코드 | 게시물 수");
    System.out.println("-".repeat(40));

    List<Board> boards = boardService.findAllWithArticleCount();  // 게시물 수를 포함한 게시판 목록 조회

    if (boards.isEmpty()) {
      System.out.println("등록된 게시판이 없습니다.");
      return;
    }

    boards.forEach(
        board -> System.out.printf("%d | %s | %s | %d개\n",
            board.getId(),
            board.getName(),
            board.getCode(),
            board.getArticleCount())  // 게시물 수 표시
    );

  }

  private void doMakeBoard(Rq rq) {
    System.out.println("== 게시판 생성 ==");

    if (rq.isLogouted()) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    Member member = rq.getLoginedMember();

    if (!member.isAdmin()) {
      System.out.println("관리자만 게시판을 생성할 수 있습니다.");
      return;
    }

    System.out.print("게시판 이름 : ");
    String boardName = Container.sc.nextLine();

    if (boardName.trim().isEmpty()) {
      System.out.println("boardName(을)를 입력해주세요.");
      return;
    }

    Board boardByName = boardService.findByBoardName(boardName);

    if(boardByName != null) {
      System.out.printf("%s 게시판은 이미 존재합니다.\n", boardName);
      return;
    }

    System.out.println("=== 게시판 코드 예시 ===");
    System.out.println("예: free, notice, qna, etc...");
    System.out.println("========================");

    System.out.print("게시판 코드(영문) : ");
    String boardCode = Container.sc.nextLine();

    if (boardCode.trim().isEmpty()) {
      System.out.println("boardCode(을)를 입력해주세요.");
      return;
    }

    Board boardByCode = boardService.findByBoardCode(boardCode);

    if(boardByCode != null) {
      System.out.printf("'%s' 코드는 이미 사용중입니다.\n", boardCode);
      return;
    }

    boardService.makeBoard(boardCode, boardName);

    System.out.printf("'%s' 게시판이 생성되었습니다.\n", boardName);
  }
}
