package com.sbs.jdbc.text_board.container;

import com.sbs.jdbc.text_board.boundedContext.article.controller.ArticleController;

import java.util.Scanner;

public class Container {
  public static Scanner sc;

  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);

    articleController = new ArticleController();
  }
}
