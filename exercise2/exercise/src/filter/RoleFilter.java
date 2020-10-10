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
	private String notCheckPath; //��Ҫ�ù��˵������ַ����web.xml�ļ���ȡ
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//��ServletRequest���Ͳ���ת��ΪHttpServletRequest����
		HttpServletRequest request = (HttpServletRequest) req; 
		String path = request . getServletPath(); //��ȡ�����URL-Pattern��ַ
		System. out. println( "�����ַurl-pattern:"+path);
		//�����ַ���ڲ�����˵��б�Χ�ڣ���Ҫ�ж��Ƿ��Ѿ���¼
			if (notCheckPath.indexOf(path) == -1) {
				
				 //�����ַ�ǲ���Ҫ���˵ģ�ֱ�ӷ���
				int judg=0;
				RoleDao dao=new RoleDao();
				HttpSession session = request . getSession();
				User user=(User) session.getAttribute("currentUser");
				String name=user.getUserName();
		
				ArrayList<Resouce> resouce=dao.get(name);
					for(int i=0;i<resouce.size();i++){
				
						if (path.equals(resouce.get(i).getUrl())) { //��Ȩ��
							judg=1;
						} 
					}
					if(judg==1){
						chain.doFilter(req,resp);//����
					}
					else{
						request . setAttribute("info","û��Ȩ�޷���");
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


