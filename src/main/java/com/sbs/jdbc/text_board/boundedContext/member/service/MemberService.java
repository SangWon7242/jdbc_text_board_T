package com.sbs.jdbc.text_board.boundedContext.member.service;

import com.sbs.jdbc.text_board.boundedContext.member.dto.Member;
import com.sbs.jdbc.text_board.boundedContext.member.repository.MemberRepository;
import com.sbs.jdbc.text_board.container.Container;

public class MemberService {
  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.memberRepository;
  }

  public Member findByUsername(String username) {
    return memberRepository.findByUsername(username);
  }

  public int join(String username, String password, String name) {
    return memberRepository.join(username, password, name);
  }
}
