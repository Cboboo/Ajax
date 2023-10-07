package com.bobo.ajax.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * ClassName: VerifyServlet
 * PackageName: com.bobo.ajax.servlet
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/10/3 18:43
 * @Version 1.0
 */
@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String uname = request.getParameter("username");
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ajax";
            String username = "root";
            String password = "1234";
            conn = DriverManager.getConnection(url, username, password);
            String sql = "select id,username from t_user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uname);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        PrintWriter out = response.getWriter();
        if(flag){
//            out.print("<font color='red'>exsist</font>");
            out.print("<font color='red'>用户名已存在</font>");
        }else {
//            out.print("<font color='green'>ok</font>");
            out.print("<font color='green'>用户名可用</font>");
        }
    }
}
