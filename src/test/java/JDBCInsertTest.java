import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JDBCInsertTest {
  public static void main(String[] args) {
    // 1. 데이터베이스 연결 정보 설정
    // -> 마치 데이터베이스 서버의 주소와 비밀번호를 적는 것과 같습니다.
    String url = "jdbc:mysql://127.0.0.1:3306/text_board?useSSL=false&autoReconnect=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useUnicode=true&allowPublicKeyRetrieval=true";
    String username = "sbsst";     // 데이터베이스 계정
    String password = "sbs123414";  // 데이터베이스 비밀번호

    Connection conn = null;
    PreparedStatement pstmt = null;

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
      String sql = "INSERT INTO article";
      sql += " SET regDate = NOW()";
      sql += ", updateDate = NOW()";
      sql += ", subject = CONCAT(\"제목\", RAND())";
      sql += ", content = CONCAT(\"내용\", RAND())";

      System.out.println(sql);

      // 5. PreparedStatement 생성
      // -> 준비한 SQL 메시지를 통역사에게 전달할 준비를 합니다.
      pstmt = conn.prepareStatement(sql);

      // 6. SQL 실행
      // -> 통역사를 통해 데이터베이스에 메시지를 전달합니다.
      int result = pstmt.executeUpdate();

      System.out.printf("실행 된 결과 : %d\n", result);

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
