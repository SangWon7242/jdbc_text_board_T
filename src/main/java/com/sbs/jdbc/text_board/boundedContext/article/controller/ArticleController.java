package com.sbs.jdbc.text_board.boundedContext.article.controller;

import com.sbs.jdbc.text_board.boundedContext.common.Controller;
import com.sbs.jdbc.text_board.boundedContext.member.dto.Member;
import com.sbs.jdbc.text_board.global.base.Rq;
import com.sbs.jdbc.text_board.boundedContext.article.dto.Article;
import com.sbs.jdbc.text_board.boundedContext.article.service.ArticleService;
import com.sbs.jdbc.text_board.container.Container;

import java.util.List;

public class ArticleController implements Controller {
  private ArticleService articleService;

  public ArticleController() {
    articleService = Container.articleService;
  }

  @Override
  public void performAction(Rq rq) {
    if (rq.getActionPath().equals("/usr/article/write")) {
      doWrite(rq);
    } else if (rq.getActionPath().equals("/usr/article/list")) {
      showList(rq);
    } else if (rq.getActionPath().equals("/usr/article/detail")) {
      showDetail(rq);
    } else if (rq.getActionPath().equals("/usr/article/modify")) {
      doModify(rq);
    } else if (rq.getActionPath().equals("/usr/article/delete")) {
      doDelete(rq);
    } else {
      System.out.println("존재하지 않는 명령어입니다.");
    }
  }

  public void doWrite(Rq rq) {
    if(rq.isLogouted()) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    System.out.println("== 게시물 작성 ==");

    System.out.print("제목 : ");
    String subject = Container.sc.nextLine();

    if (subject.trim().isEmpty()) {
      System.out.println("subject(을)를 입력해주세요.");
      return;
    }

    System.out.print("내용 : ");
    String content = Container.sc.nextLine();

    if (content.trim().isEmpty()) {
      System.out.println("content(을)를 입력해주세요.");
      return;
    }

    Member member = rq.getLoginedMember();
    int memberId = member.getId();

    int id = articleService.write(memberId, subject, content);

    System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
  }

  public void showList(Rq rq) {
    List<Article> articles = articleService.findAll();

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.println("== 게시물 리스트 ==");
    System.out.println("번호 | 제목 | 작성 날짜 | 작성자 | 조회수");
    articles.forEach(
        article
            -> System.out.printf("%d | %s | %s | %s | %d\n", article.getId(), article.getSubject(), article.getRegDate(), article.getWriterName(), article.getHit())
    );
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    articleService.increaseHit(id);

    Article article = articleService.findById(id);

    if(article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
    }

    System.out.printf("== %d번 게시물 상세보기 ==\n", id);
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("작성자 : %s\n", article.getWriterName());
    System.out.printf("작성날짜 : %s\n", article.getRegDate());
    System.out.printf("수정날짜 : %s\n", article.getUpdateDate());
    System.out.printf("조회수 : %d\n", article.getHit());
    System.out.printf("제목 : %s\n", article.getSubject());
    System.out.printf("내용 : %s\n", article.getContent());
  }

  public void doModify(Rq rq) {
    if(rq.isLogouted()) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);
    Member member = rq.getLoginedMember(); // 로그인한 사용자의 대한 정보를 세션에서 가져옴

    if(article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    if(article.getMemberId() != member.getId()) {
      System.out.println("해당 게시물에 대한 접근 권한이 없습니다.");
      return;
    }

    System.out.printf("== %d번 게시물 수정 ==\n", id);
    System.out.print("새 제목 : ");
    String subject = Container.sc.nextLine();

    System.out.print("새 내용 : ");
    String content = Container.sc.nextLine();

    articleService.modify(id, subject, content);

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  public void doDelete(Rq rq) {
    if(rq.isLogouted()) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);
    Member member = rq.getLoginedMember();

    if(article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    if(article.getMemberId() != member.getId()) {
      System.out.println("해당 게시물에 대한 접근 권한이 없습니다.");
      return;
    }

    articleService.delete(id);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }


}
