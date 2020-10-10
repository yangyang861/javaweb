package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.User;
import dao.UserDao;

@WebServlet (urlPatterns = "/login.do")
public class LoginController extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException{

			request.setCharacterEncoding("utf-8");
		      //获取Cookie
			String forwardPath="";
	        Cookie[] allCookies = request.getCookies();
	        
	        if (allCookies!=null) {
	            String userName = null;
	            String password= null;
	            //获取Cookie中保存的用户名和密码*/
	            for (int i = 0; i < allCookies.length; i++) {
	              //  if (allCookies[i].getName().equals("userName")) {
	                    userName = allCookies[i].getValue();
	              //  }
	               // if (allCookies[i].getName().equals("password")) {
	               //     password = allCookies[i].getValue();
	             //   }*/
	                    UserDao dao=new UserDao();
	                    User user =dao.get(userName);
	            	HttpSession session=request.getSession();
	            	session.setAttribute("currentUser",user);
	            }
				
	            forwardPath="/main.jsp";
	        }
			//1.取数据
	        else{String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			String vcode=request.getParameter("vcode");
			
			HttpSession session=request.getSession();
			String saveVcode=(String)session.getAttribute("verifyCode");
		//	String forwardPath="";
	
			if(!vcode.equalsIgnoreCase(saveVcode)){//验证码不正确
				request.setAttribute("info","验证码不正确");
				forwardPath="error.jsp";
			
			}else{//验证码正确
				UserDao userDao=new UserDao();
				User user=userDao.get(userName);
				if(user==null){//用户名不存在
					request.setAttribute("info","您输入的用户名不存在");
					forwardPath="/error.jsp";
System.out.println("mmmmmmmm");
					
				}else{//用户名存在
					if(!user.getPassword().equals(password)){//密码错误
						request.setAttribute("info","密码不正确");
						forwardPath="/error.jsp";
					}else{//密码正确
						session.setAttribute("currentUser",user);
						 //判断复选框是否被选中
			            
			            String keep = request.getParameter("keep");
			            
			            if (keep!=null) {
			                //实例化Cookie对象
			                Cookie userNameCookie = new Cookie("userName", userName);
			                Cookie passwordCookie = new Cookie("password", password);
			                //设置cookie一周后失效
			                userNameCookie.setMaxAge(7*24*3600);
			                passwordCookie.setMaxAge(7*24*3600);
			                //将cookie信息返回给客户端浏览器
			                response.addCookie(userNameCookie);
			                response.addCookie(passwordCookie);
			            }
			           // response.sendRedirect("success.html");
						forwardPath="/main.jsp";
					}
				}
			}
	        }
			//3.转发
			RequestDispatcher rd=request.getRequestDispatcher(forwardPath);
			rd.forward(request,response);
		}
}

			
	
