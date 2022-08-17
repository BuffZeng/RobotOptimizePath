package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/getRoom")
public class GetRoom extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession session = req.getSession();

		//		session.removeAttribute("k");

		int row = (int) session.getAttribute("row");
		int col = (int) session.getAttribute("col");
		int initx = (int) session.getAttribute("initx");
		int inity = (int) session.getAttribute("inity");
		int finx = (int) session.getAttribute("finx");
		int finy = (int) session.getAttribute("finy");
		
		String messages = (String) session.getAttribute("messages");

		//		req.setAttribute("row", row);


		PrintWriter out = res.getWriter();

		out.print("room is " + row + "," + col);
		out.print("room is " + initx + "," + inity);
		out.print("room is " + finx + "," + finy);

	}
}
