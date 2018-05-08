package com.subb.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subb.mysite.dao.GuestbookDao;
import com.subb.mysite.vo.GuestbookVo;
import com.subb.web.Action;
import com.subb.web.util.WebUtil;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter( "name" );
		String password = request.getParameter( "pass" );
		String content = request.getParameter( "content" );
		
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setContent(content);
		vo.setPassword(password);
		
		GuestbookDao dao = new GuestbookDao();
		dao.insert(vo);
		
		WebUtil.redirect( request.getContextPath() + "/guestbook", request, response );
	}

}
