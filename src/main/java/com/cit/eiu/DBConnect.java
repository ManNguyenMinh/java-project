package com.cit.eiu;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cit.eiu.IAMDatabaseAuthenticationTester;

@WebServlet(urlPatterns = { "/count" })
public class DBConnect extends HttpServlet {

  private static final String JDBC_URL = "jdbc:mysql://cse470db.cdgzhcum4cno.ap-southeast-2.rds.amazonaws.com:3306/cse470";
  private static final String DB_USER = "admin";
  private static final String DB_PASSWORD = "Tu#07042000";

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    BufferedWriter writer = new BufferedWriter(
      new OutputStreamWriter(resp.getOutputStream(), "UTF-8")
    );

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection mySQLClient = DriverManager.getConnection(
        JDBC_URL,
        DB_USER,
        DB_PASSWORD
      );
      // Connection mySQLClient = getDBConnectionUsingIam();
      String query = "SELECT count(*) as count FROM courses";
      PreparedStatement st = mySQLClient.prepareStatement(query);
      ResultSet rs = st.executeQuery();
      String report = "";
      if (rs.next()) {
        // Retrieve data from the result set
        // System.out.println(rs.getString("count"));
        report = rs.getString("count");
      }
      resp.setContentType("text/plain");
      resp.setStatus(200);
      writer.write(report);
      writer.flush();
      writer.close();
    } catch (ClassNotFoundException | SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}