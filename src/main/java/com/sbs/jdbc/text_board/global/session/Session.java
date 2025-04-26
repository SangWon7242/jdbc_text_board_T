package com.sbs.jdbc.text_board.global.session;

import java.util.HashMap;
import java.util.Map;

public class Session {
  private Map<String, Object> sessionStore;

  public Session() {
    sessionStore = new HashMap<>();
  }

  // get(가져오기), set(저장하기), contain(존재여부), remove(삭제)
  public Object getAttribute(String key) {
    return sessionStore.get(key);
  }

  public void setAttribute(String key, Object value) {
    sessionStore.put(key, value);
  }

  public boolean hasAttribute(String key) {
    return sessionStore.containsKey(key);
  }

  public void removeAttribute(String key) {
    sessionStore.remove(key);
  }
}
