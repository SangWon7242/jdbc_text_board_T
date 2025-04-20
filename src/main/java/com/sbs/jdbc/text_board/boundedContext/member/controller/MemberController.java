package com.sbs.jdbc.text_board.boundedContext.member.controller;

import com.sbs.jdbc.text_board.base.Rq;
import com.sbs.jdbc.text_board.boundedContext.member.dto.Member;
import com.sbs.jdbc.text_board.container.Container;
import com.sbs.jdbc.text_board.dbUtil.MysqlUtil;
import com.sbs.jdbc.text_board.dbUtil.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberController {
  private List<Member> members;

  public MemberController() {
     members = new ArrayList<>();
  }

  public void doJoin(Rq rq) {
    String username;
    String password;
    String passwordConfirm;
    String name;
    
    System.out.println("== 회원 가입 ==");
    
    // 로그인 아이디 입력
    while (true) {
      System.out.print("로그인 아이디 : ");
      username = Container.sc.nextLine();
      
      if(username.trim().isEmpty()) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      // 입력한 로그인 아이디에 대한 중복을 확인!!!
      // 중복되었는지에 대한 쿼리를 DB 날림!!
      // 결과를 받아와서 있는지 없는지 확인
      // MysqlUtil에 있는 selectRow 함수 사용
      SecSql sql = new SecSql();
      sql.append("SELECT *");
      sql.append("FROM `member`");
      sql.append("WHERE username = ?", username);

      Map<String, Object> memberMap = MysqlUtil.selectRow(sql);

      if(!memberMap.isEmpty()) {
        System.out.printf("'%s'(은)는 이미 가입된 로그인 아이디입니다.\n", username);
        continue;
      }
      
      break;
    }

    // 비밀번호 입력
    while (true) {
      System.out.print("로그인 비밀번호 : ");
      password = Container.sc.nextLine();

      if(password.trim().isEmpty()) {
        System.out.println("비밀번호를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.print("로그인 비밀번호 확인 : ");
        passwordConfirm = Container.sc.nextLine();

        if(passwordConfirm.trim().isEmpty()) {
          System.out.println("비밀번호 확인을 입력해주세요.");
          continue;
        }
        
        if(!passwordConfirm.equals(password)) {
          System.out.println("비밀번호가 일치하지 않습니다.");
          continue;
        }
        
        break;
      }

      break;
    }

    // 이름 입력
    while (true) {
      System.out.print("이름 : ");
      name = Container.sc.nextLine();

      if(name.trim().isEmpty()) {
        System.out.println("이름을 입력해주세요.");
        continue;
      }

      break;
    }

    SecSql sql = new SecSql();
    sql.append("INSERT INTO `member`");
    sql.append("SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", username = ?", username);
    sql.append(", password = ?", password);
    sql.append(", name = ?", name);

    int id = MysqlUtil.insert(sql);

    System.out.printf("'%s'님 회원가입 되었습니다.\n", name);
  }
}
