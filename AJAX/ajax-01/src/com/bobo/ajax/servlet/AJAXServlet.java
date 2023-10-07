package com.bobo.ajax.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: AJAXServlet
 * PackageName: com.bobo.ajax.servlet
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/10/3 14:30
 * @Version 1.0
 */
@WebServlet("/ajax_get")
public class AJAXServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("欢迎学习ajax");
    }




}
