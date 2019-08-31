package Shop.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Shop.service.UserService;

public class CheckUsername extends HttpServlet {

	private static final String String = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������
		String username = request.getParameter("username");
		
		//���͵�service��
		UserService service = new UserService();
		boolean isExist = service.checkUsername(username);
		
		String json="{\"isExist\":"+isExist+"}";
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}