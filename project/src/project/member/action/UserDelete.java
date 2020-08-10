package project.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import project.member.model.MemberDTO;
import project.member.model.SMemberDAOImpl;

/**
 * Servlet implementation class UserDelete
 */
@WebServlet("/member/userDelete.me")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid =request.getParameter("userid");
		SMemberDAOImpl dao = SMemberDAOImpl.getInstance();
		dao.memberDel(userid);//삭제
		ArrayList<MemberDTO> arr = dao.memberList();
		//JSON형태로 값을 가져가야됨
		int count =dao.getCount();
		JSONObject mainObj = new JSONObject();
		JSONArray jarr =new JSONArray();
		for(MemberDTO dto: arr) {
			String mode =dto.getAdmin()==1?"관리자":"일반회원";
			JSONObject obj = new JSONObject();
			obj.put("name", dto.getName());
			obj.put("userid", dto.getUserid());
			obj.put("email", dto.getEmail());
			obj.put("phone", dto.getPhone());
			obj.put("mode",mode);
			jarr.add(obj);
			
		}
		JSONObject countObj = new JSONObject();
		countObj.put("count",count);
		
		mainObj.put("root",jarr);
		mainObj.put("rootCount", count);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(mainObj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
