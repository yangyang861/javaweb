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
HttpSession session = request . getSession(); //��ȡses son����
session. removeAttribute("currentUser"); //�������� session����ΪcurrentUser����Ϣ
response.sendRedirect("http:login.html"); //�ض���1ogin. htm1ҳ��a href="login.html"
}
}

