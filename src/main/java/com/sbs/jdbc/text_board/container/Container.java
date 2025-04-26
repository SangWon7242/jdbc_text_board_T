package com.sbs.jdbc.text_board.container;

import com.sbs.jdbc.text_board.boundedContext.article.controller.ArticleController;
import com.sbs.jdbc.text_board.boundedContext.article.repository.ArticleRepository;
import com.sbs.jdbc.text_board.boundedContext.article.service.ArticleService;
import com.sbs.jdbc.text_board.boundedContext.board.boardService.BoardService;
import com.sbs.jdbc.text_board.boundedContext.board.controller.BoardController;
import com.sbs.jdbc.text_board.boundedContext.board.repository.BoardRepository;
import com.sbs.jdbc.text_board.boundedContext.member.controller.MemberController;
import com.sbs.jdbc.text_board.boundedContext.member.repository.MemberRepository;
import com.sbs.jdbc.text_board.boundedContext.member.service.MemberService;
import com.sbs.jdbc.text_board.global.session.Session;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static Session session;

  public static MemberRepository memberRepository;
  public static BoardRepository boardRepository;
  public static ArticleRepository articleRepository;

  public static MemberService memberService;
  public static BoardService boardService;
  public static ArticleService articleService;

  public static MemberController memberController;
  public static BoardController boardController;
  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    articleRepository = new ArticleRepository();
    boardRepository = new BoardRepository();
    memberRepository = new MemberRepository();

    memberService = new MemberService();
    boardService = new BoardService();
    articleService = new ArticleService();

    memberController = new MemberController();
    boardController = new BoardController();
    articleController = new ArticleController();
  }
}
