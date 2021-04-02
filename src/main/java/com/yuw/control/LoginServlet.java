package com.yuw.control;


import com.yuw.modal.UserModel;
import com.yuw.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*
@WebServlet 是servlet3.0以后支持使用；这样就不需要在web.xml中配置servlet了
name属性是servlet的名字；
value属性是servlet的服务器响应的映射路径；
 */
@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行了后台的servlet业务逻辑处理。。。");
        // 获取表单数据
        /*
            要求：request.getParameter（）方法的参数值和form表单控件的name属性值 保持一致；

         */
        String strName = request.getParameter("userName");
        String strPsw = request.getParameter("userPsw");
        System.out.println("登录的用户名：" + strName);
        System.out.println("登录的密码：" + strPsw);
        // 跳转到业务逻辑处理成功页面
        // 创建业务逻辑处理对象
        //UserModel userModel = new UserModel();
        boolean canLogined = MyUtils.getNewInstance(UserModel.class).doLogin(strName, strPsw);
        if (canLogined) {
            // 登录成功
            // 将登陆成功的用户名缓存到session中，传给前台页面
            request.getSession().setAttribute("loginedUserName", strName);
            // 转发
            request.getRequestDispatcher("WEB-INF/viewpages/loginedmain.jsp").forward(request, response);
        } else {
            // 失败之后继续回到登录页面
            // 重定向
            response.sendRedirect("index.jsp");
        }
    }
}
