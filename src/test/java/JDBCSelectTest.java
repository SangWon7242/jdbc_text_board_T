import com.sbs.jdbc.text_board.boundedContext.article.dto.Article;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JDBCSelectTest {
  public static void main(String[] args) {
    // 1. 데이터베이스 연결 정보 설정
    // -> 마치 데이터베이스 서버의 주소와 비밀번호를 적는 것과 같습니다.
    String url = "jdbc:mysql://127.0.0.1:3306/text_board?useSSL=false&autoReconnect=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useUnicode=true&allowPublicKeyRetrieval=true";
    String username = "sbsst";     // 데이터베이스 계정
    String password = "sbs123414";  // 데이터베이스 비밀번호

    // 데이터베이스 연결 객체
    Connection conn = null;
    // SQL 실행을 위한 PreparedStatement 객체
    PreparedStatement pstmt = null;
    // 조회 결과를 담을 ResultSet 객체
    ResultSet rs = null;

    List<Article> articles = new ArrayList<>();

    try {
      // 2. JDBC 드라이버 로드
      // -> 통역사를 부르는 과정이라고 생각하면 됩니다.
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("드라이버 로드 성공!");

      // 3. 데이터베이스 연결
      // -> 통역사를 통해 데이터베이스와 대화할 수 있는 상태를 만듭니다.
      conn = DriverManager.getConnection(url, username, password);
      System.out.println("데이터베이스 연결 성공!");

      // 4. SQL 쿼리 준비
      // -> 통역사에게 전달할 메시지(SQL)를 준비합니다.
      String sql = "SELECT *";
      sql += " FROM article";
      sql += " ORDER BY id DESC";

      System.out.println(sql);

      // 5. PreparedStatement 생성
      // -> 준비한 SQL 메시지를 통역사에게 전달할 준비를 합니다.
      pstmt = conn.prepareStatement(sql);

      // 6. SQL 실행
      // -> 통역사를 통해 데이터베이스에 메시지를 전달합니다.
      rs = pstmt.executeQuery();

      // 5. 결과 처리
      while (rs.next()) {
        // ResultSet에서 각 컬럼의 값을 가져옴
        int id = rs.getInt("id");
        /*
        LocalDateTime regDate = rs.getTimestamp("regDate").toLocalDateTime();
        LocalDateTime updateDate = rs.getTimestamp("updateDAte").toLocalDateTime();
        */
        String regDate = rs.getString("regDate");
        String updateDate = rs.getString("updateDate");
        String subject = rs.getString("subject");
        String content = rs.getString("content");

        Article article = new Article(id, regDate, updateDate, subject, content);
        articles.add(article);
      }

      System.out.println("결과 : " + articles);


    } catch (ClassNotFoundException e) {
      // JDBC 드라이버를 찾지 못했을 때의 처리
      System.out.println("DB 드라이버 로드 실패!");
      e.printStackTrace();
    } catch (SQLException e) {
      // SQL 관련 오류가 발생했을 때의 처리
      System.out.println("DB 연결 또는 SQL 실행 실패!");
      e.printStackTrace();
    } finally {
      try {
        if(rs != null && !rs.isClosed()) rs.close();
        if(pstmt != null && !pstmt.isClosed()) pstmt.close();
        if(conn != null && !conn.isClosed()) {
          conn.close();
          System.out.println("데이터베이스 연결 종료!");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
