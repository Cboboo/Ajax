package com.bobo.ajax.servlets;

import com.alibaba.fastjson.JSON;
import com.bobo.ajax.beans.Data;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: QueryServlet
 * PackageName: com.bobo.ajax.servlets
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/10/7 20:10
 * @Version 1.0
 */
@WebServlet("/query")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");
        String json = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ajax";
            String username = "root";
            String password = "1234";
            conn = DriverManager.getConnection(url, username, password);
            String sql = "select content from t_query where content like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,text+"%");
            rs = ps.executeQuery();
            List<Data> datas = new ArrayList<>();
            while (rs.next()){
                String content = rs.getString("content");
                Data data = new Data(content);
                datas.add(data);
            }
            json=JSON.toJSONString(datas);
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
        out.print(json);
    }
}
