package co.jelly.main.member.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jelly.main.common.Command;
import co.jelly.main.member.serviceImpl.MemberDAO;
import co.jelly.main.member.vo.MemberVO;

public class MemberList implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = new ArrayList<MemberVO>();
		list = dao.memberSelectList();
		request.setAttribute("members", list); //결과값을 객체에 보낸다.
		
		return "member/memberList.jsp";
	}

}
