package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("frontcontroller ¡¯¿‘!!");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
				
		String command = uri.substring(conPath.length()+1);
		
		System.out.println(uri);
		System.out.println(conPath);
		System.out.println(command);
		
		String[] commands = command.split("/");
		
		if(commands[0].equals("board")) {
			BoardController boardController = new BoardController();
			boardController.doGet(request, response, commands);
		}else if(commands[0].equals("member")) {
			MemberController memberController = new MemberController();
			memberController.doGet(request, response, commands);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
