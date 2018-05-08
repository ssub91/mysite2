package com.subb.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subb.mysite.dao.BoardDao;
import com.subb.mysite.vo.BoardVo;
import com.subb.web.Action;
import com.subb.web.util.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = WebUtil.checkLongParam( request.getParameter( "no" ), 0L );
		int page = WebUtil.checkIntParam( request.getParameter( "p" ), 1 );
		String keyword = WebUtil.checkNullParam( request.getParameter( "kwd" ), "" );
		
		BoardDao dao = new BoardDao();
		BoardVo boardVo = dao.get( no );

		if( boardVo == null ) {
			WebUtil.redirect( request.getContextPath() + "/board", request, response );
			return;
		}
		
		// view count 증가
		dao.updateHit( no );
		
		request.setAttribute( "boardVo", boardVo );
		request.setAttribute( "page", page );
		request.setAttribute( "keyword", keyword );
		
		WebUtil.forward(
			"/WEB-INF/views/board/view.jsp", 
			request,
			response);
	}

}
