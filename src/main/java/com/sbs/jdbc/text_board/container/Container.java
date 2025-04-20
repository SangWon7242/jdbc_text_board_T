package com.sbs.jdbc.text_board.container;

import com.sbs.jdbc.text_board.boundedContext.article.controller.ArticleController;
import com.sbs.jdbc.text_board.boundedContext.article.repository.ArticleRepository;
import com.sbs.jdbc.text_board.boundedContext.article.service.ArticleService;
import com.sbs.jdbc.text_board.boundedContext.member.controller.MemberController;

import java.util.Scanner;

public class Container {
  public static Scanner sc;


  public static ArticleRepository articleRepository;

  public static ArticleService articleService;

  public static MemberController memberController;
  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);

    articleRepository = new ArticleRepository();

    articleService = new ArticleService();

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
