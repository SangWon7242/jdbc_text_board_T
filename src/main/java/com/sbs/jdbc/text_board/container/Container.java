package com.sbs.jdbc.text_board.container;

import com.sbs.jdbc.text_board.base.global.session.Session;
import com.sbs.jdbc.text_board.boundedContext.article.controller.ArticleController;
import com.sbs.jdbc.text_board.boundedContext.article.repository.ArticleRepository;
import com.sbs.jdbc.text_board.boundedContext.article.service.ArticleService;
import com.sbs.jdbc.text_board.boundedContext.member.controller.MemberController;
import com.sbs.jdbc.text_board.boundedContext.member.repository.MemberRepository;
import com.sbs.jdbc.text_board.boundedContext.member.service.MemberService;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static Session session;

  public static MemberRepository memberRepository;
  public static ArticleRepository articleRepository;

  public static MemberService memberService;
  public static ArticleService articleService;

  public static MemberController memberController;
  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    memberRepository = new MemberRepository();
    articleRepository = new ArticleRepository();

    memberService = new MemberService();
    articleService = new ArticleService();

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
