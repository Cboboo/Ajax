package com.bobo.ajax.servlets;

import com.alibaba.fastjson.JSON;
import com.bobo.ajax.beans.City;
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
 * ClassName: CityListServlet
 * PackageName: com.bobo.ajax.servlets
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/10/6 19:14
 * @Version 1.0
 */
@WebServlet("/listcity")
public class CityListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pcode1 = request.getParameter("pcode");
        String jsonStr = "";
        String sql = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ajax";
            String username = "root";
            String password = "1234";
            conn = DriverManager.getConnection(url, username, password);
            if(pcode1 == null){
                sql = "select * from t_area where pcode is null";
                ps = conn.prepareStatement(sql);
            }else {
                sql = "select * from t_area where pcode = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,pcode1);
            }
            rs = ps.executeQuery();
            /*
            使用fastJSON组件简化JSON转换
             */
            List<City> cities = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                String pcode = rs.getString("pcode");
                City city = new City(id,code,name,pcode);
                cities.add(city);
            }
            jsonStr = JSON.toJSONString(cities);
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
