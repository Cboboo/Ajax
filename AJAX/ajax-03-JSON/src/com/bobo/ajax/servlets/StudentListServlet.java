package com.bobo.ajax.servlets;

import com.alibaba.fastjson.JSON;
import com.bobo.ajax.beans.Student;
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
 * ClassName: StudentListServlet
 * PackageName: com.bobo.ajax.servlet
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/10/4 15:05
 * @Version 1.0
 */
@WebServlet("/list")
public class StudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder json = new StringBuilder();
        String jsonStr = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ajax";
            String username = "root";
            String password = "1234";
            conn = DriverManager.getConnection(url, username, password);
            String sql = "select name,gender,address from t_student";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            json.append("[");
            /*
            使用fastJSON组件简化JSON转换
             */
            List<Student> stus = new ArrayList<>();
            while (rs.next()){
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                Student student = new Student(name,gender,address);
                stus.add(student);
                //{"name":"  name  ","gender":"  gender  ","address":"  address  "},


                //JSON拼串 - 繁琐痛苦
                /*json.append("{\"name\":\"");
                json.append(name);
                json.append("\",\"gender\":\"");
                json.append(gender);
                json.append("\",\"address\":\"");
                json.append(address);
                json.append("\"},");*/
            }
            jsonStr = JSON.toJSONString(stus);
//            jsonStr=json.substring(0,json.length()-1)+"]";
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
        out.print(jsonStr);
    }
}
