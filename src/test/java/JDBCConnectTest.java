import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectTest {
  public static void main(String[] args) {
    // JDBC 드라이버 클래스 이름
    String driverClass = "com.mysql.cj.jdbc.Driver";

    // 데이터베이스 연결 정보
    String url = "jdbc:mysql://127.0.0.1:3306/text_board?useSSL=false&autoReconnect=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useUnicode=true&allowPublicKeyRetrieval=true";
    
    // root 계정인 경우에 username은 'root'로 
    // 비번은 공백처리
    String username = "sbsst";
    String password = "sbs123414";

    Connection conn = null;

    try {
      // 1. JDBC 드라이버 로드
      Class.forName(driverClass);
      System.out.println("JDBC 드라이버 로드 성공!");

      // 2. 데이터베이스 연결
      conn = DriverManager.getConnection(url, username, password);
      System.out.println("데이터베이스 연결 성공!");

    } catch (ClassNotFoundException e) {
      // JDBC 드라이버 로드 실패 시 예외 처리
      System.out.println("JDBC 드라이버 로드 실패!");
      e.printStackTrace();

    } catch (SQLException e) {
      // 데이터베이스 연결 실패 시 예외 처리
      System.out.println("데이터베이스 연결 실패!");
      e.printStackTrace();

    } finally {
      try {
        // 3. 연결 종료 (리소스 해제)
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
