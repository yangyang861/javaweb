package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Resouce;
import vo.User;
import dao.RoleDao;

public class RoleFilter implements Filter {
	private String notCheckPath; //不要用过滤的请求地址，从web.xml文件读取
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//将ServletRequest类型参数转换为HttpServletRequest类型
		HttpServletRequest request = (HttpServletRequest) req; 
		String path = request . getServletPath(); //获取请求的URL-Pattern地址
		System. out. println( "请求地址url-pattern:"+path);
		//请求地址不在不需过滤的列表范围内，需要判断是否已经登录
			if (notCheckPath.indexOf(path) == -1) {
				
				 //请求地址是不需要过滤的，直接放行
				int judg=0;
				RoleDao dao=new RoleDao();
				HttpSession session = request . getSession();
				User user=(User) session.getAttribute("currentUser");
				String name=user.getUserName();
		
				ArrayList<Resouce> resouce=dao.get(name);
					for(int i=0;i<resouce.size();i++){
				
						if (path.equals(resouce.get(i).getUrl())) { //有权限
							judg=1;
						} 
					}
					if(judg==1){
						chain.doFilter(req,resp);//放行
					}
					else{
						request . setAttribute("info","没有权限访问");
						request.getRequestDispatcher("/error.jsp").forward(request,resp);
						return;
					}
					
					
		
		} 
			else {chain. doFilter(req,resp);}
		




	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		notCheckPath = config.getInitParameter( "notCheckPath" );

	}

}


