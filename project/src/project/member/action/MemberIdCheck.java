package project.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jrockit.jfr.RequestDelegate;

import project.member.model.SMemberDAOImpl;

/**
 * Servlet implementation class MemberIdCheck
 */
@WebServlet("/member/idCheck.me")
public class MemberIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberIdCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd
		= request.getRequestDispatcher("idCheck.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		SMemberDAOImpl dao = SMemberDAOImpl.getInstance();
		String flag = dao.idCheck(userid);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(flag);
	}
}
