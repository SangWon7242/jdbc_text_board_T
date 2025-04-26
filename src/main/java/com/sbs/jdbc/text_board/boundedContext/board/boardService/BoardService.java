package com.sbs.jdbc.text_board.boundedContext.board.boardService;

import com.sbs.jdbc.text_board.boundedContext.board.dto.Board;
import com.sbs.jdbc.text_board.boundedContext.board.repository.BoardRepository;
import com.sbs.jdbc.text_board.container.Container;

public class BoardService {
  private BoardRepository boardRepository;

  public BoardService() {
    boardRepository = Container.boardRepository;
  }

  public void makeBoard(String code, String name) {
    boardRepository.makeBoard(code, name);
  }

  public Board findByBoardName(String boardName) {
    return boardRepository.findByBoardName(boardName);
  }

  public Board findByBoardCode(String boardCode) {
    return boardRepository.findByBoardCode(boardCode);
  }
}
