package com.subb.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.subb.mysite.dao.UserDao;
import com.subb.mysite.vo.UserVo;
import com.subb.web.Action;
import com.subb.web.util.WebUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인증 유무 체크
		HttpSession session = request.getSession();
		if( session == null ) {
			WebUtil.redirect( request.getContextPath(), request, response);
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ) {
			WebUtil.redirect( request.getContextPath(), request, response);
			return;
		}

		UserVo userVo = new UserDao().get( authUser.getNo() );
		
		request.setAttribute( "userVo", userVo );
		WebUtil.forward( "/WEB-INF/views/user/modifyform.jsp", request, response);
	}
}
