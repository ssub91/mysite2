package com.subb.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subb.mysite.dao.GuestbookDao;
import com.subb.mysite.vo.GuestbookVo;
import com.subb.web.Action;
import com.subb.web.util.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestbookDao dao = new GuestbookDao();
		List<GuestbookVo> list = dao.getList();
		
		request.setAttribute( "list", list );
		WebUtil.forward(
			"/WEB-INF/views/guestbook/list.jsp",
			request,
			response);
	}

}
