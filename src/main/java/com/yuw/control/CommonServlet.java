package com.yuw.control;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CommonServlet", value = "/jump")
public class CommonServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取跳转页面的路径参数
        String strURI = request.getParameter("pg");
        // 服务器转发进入到目标页面
        request.getRequestDispatcher(strURI).forward(request, response);
    }
}
