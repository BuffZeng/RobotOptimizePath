package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sq")
public class SqServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession session = req.getSession();
		
//		session.removeAttribute("k");
		
		int row = (int) session.getAttribute("row");
		int col = (int) session.getAttribute("col");
		
		req.setAttribute("row", row);
		
		
//		PrintWriter out = res.getWriter();
//		
//		 out.print("room is " + row + "," + col);
		
	}
}
