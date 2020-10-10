package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PermissionFilter implements Filter {
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
			HttpSession session = request . getSession();
				if (session . getAttribute("currentUser") == null) { //û�е�½
				request . setAttribute("info","û��Ȩ�޷���");
				request . getRequestDispatcher("/error.jsp").forward(request,resp);
				
				return;
				} 
				else { //�Ѿ���¼,ֱ�ӷ���
					chain. doFilter(req,resp);
					
				}
			} 
			else { //�����ַ�ǲ���Ҫ���˵ģ�ֱ�ӷ���
					chain. doFilter(req,resp);
				
		}
		



	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//��web. xm1�����ļ���filter�ж�ȡ��ΪnotCheckPath�ĳ�ʼ��ֵ
		notCheckPath = config.getInitParameter( "notCheckPath" );


	}

}
