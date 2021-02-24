package qq.com.springbook.view.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;

	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) {

	}
//
//	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//		String uri = request.getRequestURI();
//		String path = uri.substring(uri.lastIndexOf("/"));
//
//		Controller ctrl = handlerMapping.getController(path);
//
//		String viewName = ctrl.handleRequest(request, response);
//
//		String view = null;
//		if (!viewName.contains(".do")) {
//			view = viewResolver.getView(viewName);
//		} else {
//			view = viewName;
//		}
//
//		response.sendRedirect(view);

//====================MVC2====================================================
//		// 1. 클라이언트의 요청 path 정보를 추출한다.
//
//		String uri = request.getRequestURI();
//		String path = uri.substring(uri.lastIndexOf("/"));
//		System.out.println(path);
//
//		// 2. 클라이언트의 요청 path에 따라 적절히 분기처리 한다.
//
//		if (path.equals("/login.do")) {
//			System.out.println("로그인 처리");
//			// 1.사용자 입력 정보 추출
//			String id = request.getParameter("id");
//			String password = request.getParameter("password");
//
//			// 2. DB연동 처리
//			UserVO vo = new UserVO();
//			vo.setId(id);
//			vo.setPassword(password);
//
//			UserDAO userDAO = new UserDAO();
//			UserVO user = userDAO.getUser(vo);
//
//			// 3.화면 네비게이션
//			if (user != null) {
//				response.sendRedirect("getBoardList.do");
//			} else {
//				response.sendRedirect("login.jsp");
//			}
//
//		} else if (path.equals("/logout.do")) {
//			System.out.println("로그아웃 처리");
//			
//			HttpSession session = request.getSession();
//			session.invalidate();
//			
//			response.sendRedirect("login.jsp");
//			
//		} else if (path.equals("/insertBoard.do")) {
//			System.out.println("글 등록 처리");
//			String title = request.getParameter("title");
//			String writer =  request.getParameter("writer");
//			String content = request.getParameter("content");
//			
//			BoardVO vo = new BoardVO();
//			vo.setTitle(title);
//			vo.setWriter(writer);
//			vo.setContent(content);
//			
//			BoardDAO boardDAO = new BoardDAO();
//			boardDAO.insertBoard(vo);
//			
//			response.sendRedirect("getBoardList.do");
//			
//		} else if (path.equals("/updateBoard.do")) {
//			System.out.println("글 수정 처리");
//			
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//			String seq = request.getParameter("seq");
//			
//			BoardVO vo = new BoardVO();
//			vo.setTitle(title);
//			vo.setContent(content);
//			vo.setSeq(Integer.parseInt(seq));
//			
//			BoardDAO boardDAO = new BoardDAO();
//			boardDAO.updateBoard(vo);
//			
//			response.sendRedirect("getBoardList.do");
//			
//		} else if (path.equals("/deleteBoard.do")) {
//			System.out.println("글 삭제 처리");
//			
//			String seq = request.getParameter("seq");
//			
//			BoardVO vo = new BoardVO();
//			vo.setSeq(Integer.parseInt(seq));
//			
//			BoardDAO boardDAO = new BoardDAO();
//			boardDAO.deleteBoard(vo);
//			
//			response.sendRedirect("getBoardList.do");
//					
//			
//		} else if (path.equals("/getBoard.do")) {
//			System.out.println("글 상세 조회 처리");
//			
//			//1.검색할 게시물 번호 추출
//			String seq = request.getParameter("seq");
//			
//			BoardVO vo = new BoardVO();
//			vo.setSeq(Integer.parseInt(seq));
//			
//			BoardDAO boardDAO = new BoardDAO();
//			BoardVO board = boardDAO.getBoard(vo);
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("board", board);
//			response.sendRedirect("getBoard.jsp");
//			
//		} else if (path.equals("/getBoardList.do")) {
//			System.out.println("글 목록 검색 처리");
//			
//			BoardVO vo = new BoardVO();
//			BoardDAO boardDAO = new BoardDAO();
//			
//			List<BoardVO> boardList = boardDAO.getBoardList(vo);
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("boardList", boardList);
//			response.sendRedirect("getBoardList.jsp");
//		}
//	}
}
