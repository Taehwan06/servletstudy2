package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberVO;

/**
 * Servlet implementation class MemberConrtoller
 */
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response, String[] commands) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MemberVO> alist = new ArrayList<MemberVO>();
		
		MemberDAO memberDAO = new MemberDAO();
		alist = memberDAO.selectAll();
		
		request.setAttribute("alist", alist);
		RequestDispatcher rd = request.getRequestDispatcher("/member/list.jsp");
		rd.forward(request, response);
	}
	
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String midx =  request.getParameter("midx");
		
		MemberDAO memberDAO = new MemberDAO();
		MemberVO vo = memberDAO.selectOne(midx);
		
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("/member/view.jsp");
		rd.forward(request, response);
	}
	
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String midx =  request.getParameter("midx");
		
		MemberDAO memberDAO = new MemberDAO();
		MemberVO vo = memberDAO.selectOne(midx);
		
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("/member/modify.jsp");
		rd.forward(request, response);
	}
	
	private void modifyOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		MemberVO vo = new MemberVO();
		
		vo.setMidx(Integer.parseInt(request.getParameter("midx")));
		vo.setEmail(request.getParameter("email"));
		vo.setPhone(request.getParameter("phone"));
		
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.update(vo);
		
		response.sendRedirect(request.getContextPath()+"/member/view.do?midx="+request.getParameter("midx"));		
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/insert.jsp");
		rd.forward(request, response);
	}
	

}
