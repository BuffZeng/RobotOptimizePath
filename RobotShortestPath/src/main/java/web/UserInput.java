package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class UserInput extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {

		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		String row = req.getParameter("row");
		String col = req.getParameter("col");
		String initx = req.getParameter("initx");
		String inity = req.getParameter("inity");
		String finx = req.getParameter("finx");
		String finy = req.getParameter("finy");
		String obsx = req.getParameter("obsx");
		String obsy = req.getParameter("obsy");
		pw.println(row + " " +  col);
		pw.println(initx + " " +  inity);
		pw.println(obsx + " " +  obsy);
		pw.close();
	}
}