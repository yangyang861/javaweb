package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns = "/logout.do" )
public class LogoutController extends HttpServlet {
public void doGet (HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
HttpSession session = request . getSession(); //获取ses son对象
session. removeAttribute("currentUser"); //清除存放在 session中名为currentUser的信息
response.sendRedirect("http:login.html"); //重定向到1ogin. htm1页面a href="login.html"
}
}

