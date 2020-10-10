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
		      //��ȡCookie
			String forwardPath="";
	        Cookie[] allCookies = request.getCookies();
	        
	        if (allCookies!=null) {
	            String userName = null;
	            String password= null;
	            //��ȡCookie�б�����û���������*/
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
			//1.ȡ����
	        else{String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			String vcode=request.getParameter("vcode");
			
			HttpSession session=request.getSession();
			String saveVcode=(String)session.getAttribute("verifyCode");
		//	String forwardPath="";
	
			if(!vcode.equalsIgnoreCase(saveVcode)){//��֤�벻��ȷ
				request.setAttribute("info","��֤�벻��ȷ");
				forwardPath="error.jsp";
			
			}else{//��֤����ȷ
				UserDao userDao=new UserDao();
				User user=userDao.get(userName);
				if(user==null){//�û���������
					request.setAttribute("info","��������û���������");
					forwardPath="/error.jsp";
System.out.println("mmmmmmmm");
					
				}else{//�û�������
					if(!user.getPassword().equals(password)){//�������
						request.setAttribute("info","���벻��ȷ");
						forwardPath="/error.jsp";
					}else{//������ȷ
						session.setAttribute("currentUser",user);
						 //�жϸ�ѡ���Ƿ�ѡ��
			            
			            String keep = request.getParameter("keep");
			            
			            if (keep!=null) {
			                //ʵ����Cookie����
			                Cookie userNameCookie = new Cookie("userName", userName);
			                Cookie passwordCookie = new Cookie("password", password);
			                //����cookieһ�ܺ�ʧЧ
			                userNameCookie.setMaxAge(7*24*3600);
			                passwordCookie.setMaxAge(7*24*3600);
			                //��cookie��Ϣ���ظ��ͻ��������
			                response.addCookie(userNameCookie);
			                response.addCookie(passwordCookie);
			            }
			           // response.sendRedirect("success.html");
						forwardPath="/main.jsp";
					}
				}
			}
	        }
			//3.ת��
			RequestDispatcher rd=request.getRequestDispatcher(forwardPath);
			rd.forward(request,response);
		}
}

			
	
