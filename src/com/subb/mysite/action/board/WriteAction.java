package com.subb.mysite.action.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.subb.mysite.dao.BoardDao;
import com.subb.mysite.vo.BoardVo;
import com.subb.mysite.vo.UserVo;
import com.subb.web.Action;
import com.subb.web.util.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// is auth?
		HttpSession session = request.getSession();
		if( session == null ) {
			WebUtil.redirect( request.getContextPath() + "/board", request, response );
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ) {
			WebUtil.redirect( request.getContextPath() + "/board", request, response );
			return;
		}
		
		String title = WebUtil.checkNullParam( request.getParameter( "title" ), "" );
		String content = WebUtil.checkNullParam( request.getParameter( "content" ), "" );
		String keyword = WebUtil.checkNullParam( request.getParameter( "kwd" ), "" );
		String gno = request.getParameter( "gno" );
		String ono = request.getParameter( "ono" );
		String d = request.getParameter( "d" );

		if( "".equals( title ) || "".equals( title ) ) {
			WebUtil.redirect( request.getContextPath() + "/board", request, response );
			return;			
		}
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUserNo( authUser.getNo() );
		
		if( gno != null ) {
			int groupNo = Integer.parseInt( gno );
			int orderNo = Integer.parseInt( ono );
			int depth = Integer.parseInt( d );
			
			// 같은 그룹의 orderNo 보다 큰 글 들의 order_no 1씩 증가
			dao.increaseGroupOrder( groupNo, orderNo );
			
			vo.setGroupNo( groupNo );
			vo.setOrderNo( orderNo + 1 );
			vo.setDepth( depth + 1 );
		}
		
		dao.insert(vo);

		WebUtil.redirect( request.getContextPath() + "/board?kwd=" + URLEncoder.encode( keyword, "UTF-8" ), request, response );
	}

}
