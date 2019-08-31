package Shop.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.net.httpserver.HttpsServer;

import Shop.domain.User;

public class UserLoginPrivilegeFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse reps = (HttpServletResponse) arg0;
		HttpSession session = req.getSession();
		//判断用户是否已经登录 未登录下面代码不执行
				User user = (User) session.getAttribute("user");
				if(user==null){
					//没有登录
					reps.sendRedirect(req.getContextPath()+"/login.jsp");
					return;
				}
				//改动文件较多  暂时不改权限控制 了解即可
		  arg2.doFilter(req, reps);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
