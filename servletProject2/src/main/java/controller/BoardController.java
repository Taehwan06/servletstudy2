package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import vo.BoardVO;

/**
 * Servlet implementation class BoardController
 */
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response, String[] commands) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println(request.getMethod());
		
		if(commands[1].equals("list.do")) {
			list(request, response);
		}else if(commands[1].equals("view.do")) {
			view(request, response);
		}else if(commands[1].equals("modify.do")) {
			if(request.getMethod().equals("GET")) {
				modify(request, response);
			}else if(request.getMethod().equals("POST")) {
				modifyOk(request, response);
			}
		}else if(commands[1].equals("insert.do")) {
			if(request.getMethod().equals("GET")) {
				insert(request, response);
			}else if(request.getMethod().equals("POST")) {
				insertOk(request, response);
			}			
		}else if(commands[1].equals("delete.do")) {
			delete(request, response);
		}else if(commands[1].equals("index.do")) {
			index(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<BoardVO> alist = new ArrayList<>();
		
		BoardDAO boardDAO = new BoardDAO();
		alist = boardDAO.selectAll();
		
		request.setAttribute("alist", alist);
		RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp");
		rd.forward(request, response);
	}
	
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("view 진입");
		
		request.setCharacterEncoding("UTF-8");
		String bidx = request.getParameter("bidx");
		
		System.out.println("bidx = "+bidx);
		
		BoardDAO boardDAO = new BoardDAO();
		BoardVO vo = boardDAO.selectOne(bidx);
		
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("/board/view.jsp");
		rd.forward(request, response);
	}
	
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("modify 진입");
				
		request.setCharacterEncoding("UTF-8");
		String bidx = request.getParameter("bidx");
		
		System.out.println("bidx = "+bidx);
		
		BoardDAO boardDAO = new BoardDAO();
		BoardVO vo = boardDAO.selectOne(bidx);
		
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("/board/modify.jsp");
		rd.forward(request, response);
		
	}
	
	private void modifyOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("modifyOk 진입");
		
		BoardVO vo = new BoardVO();
				
		request.setCharacterEncoding("UTF-8");
		vo.setBidx(Integer.parseInt(request.getParameter("bidx")));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.update(vo);
		
		response.sendRedirect(request.getContextPath()+"/board/view.do?bidx="+request.getParameter("bidx"));
		
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("insert 진입");
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/insert.jsp");
		rd.forward(request, response);
		
	}
	
	private void insertOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("insertOk 진입");
		
		// HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		
		BoardVO vo = new BoardVO();
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setWriter(request.getParameter("writer"));
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.insert(vo);
		
		response.sendRedirect(request.getContextPath()+"/board/list.do");
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("delete 진입");
		
		String bidx = request.getParameter("bidx");
		
		System.out.println("bidx = "+bidx);
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.delete(bidx);
		
		response.sendRedirect(request.getContextPath()+"/board/list.do");
		
	}
		
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("index 진입");
		
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		
	}
}
