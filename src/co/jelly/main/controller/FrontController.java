package co.jelly.main.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jelly.main.common.Command;
import co.jelly.main.main.MainCommand;
import co.jelly.main.member.web.MemberList;
import co.jelly.main.member.web.MemberRegister;


@WebServlet(description = "process all request", urlPatterns = { "*.do" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String,Command>();
       
  
    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		 map.put("/main.do", new MainCommand()); //main.jsp
		 map.put("/memberList.do", new MemberList());
		 map.put("/memberRegister.do", new MemberRegister());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length());
		
		Command command = map.get(path);
		String viewPage = command.execute(request, response); //실행 후 보여 줄 페이지.
		
		if(viewPage.endsWith(".jsp")) {
			viewPage = "WEB-INF/views/" + viewPage;  //jsp에 접근 할 수 있도록 변환.
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
