package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

@WebServlet("/input")
public class InputServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HashMap<String, String> messages = new HashMap<>();

		
		req.setAttribute("messages", messages);

		String rowString = req.getParameter("row");
		String colString = req.getParameter("col");

		String initxString = req.getParameter("initx");
		String inityString = req.getParameter("inity");

		String finxString = req.getParameter("finx");
		String finyString = req.getParameter("finy");
		
		String algo =  req.getParameter("algo");

		if (rowString == null || colString == null ||
				rowString.trim().isEmpty() || colString.trim().isEmpty() ||
				initxString == null || inityString == null ||
				initxString.trim().isEmpty() || inityString.trim().isEmpty() ||
				finxString == null || finyString == null ||
				finxString.trim().isEmpty() || finyString.trim().isEmpty()) {
			messages.put("Fail", "Please enter valid number!");
		} else {
			int row = Integer.parseInt(rowString);
			int col = Integer.parseInt(colString);
			int initx = Integer.parseInt(initxString);
			int inity = Integer.parseInt(inityString);
			int finx = Integer.parseInt(finxString);
			int finy = Integer.parseInt(finyString);
			if (row <= 1 && col <= 1) {
				messages.put("Fail", "Please enter valid Room Size!");
			} else if (row * col > 250000) {
				messages.put("Fail", "Please enter valid Room Size!");
			} else if (initx < 0 || initx >= row) {
				messages.put("Fail", "Please enter valid x of Starter Point!");
			} else if (inity < 0 || inity >= col) {
				messages.put("Fail", "Please enter valid y of Starter Point!");
			} else if (finx < 0 || finx >= row) {
				messages.put("Fail", "Please enter valid x of End Point!");
			} else if (finy < 0 || finy >= col) {
				messages.put("Fail", "Please enter valid y of End Point!");
			} else if (finx == initx && finy == inity) {
				messages.put("Fail", "Starter point and End point cannot be the same!");
			} else {
				messages.put("Success", "Success");
				
				req.setAttribute("row", row);
				req.setAttribute("col", col);
				req.setAttribute("initx", initx);
				req.setAttribute("inity", inity);
				req.setAttribute("finx", finx);
				req.setAttribute("finy", finy);
				req.setAttribute("algo", algo);
			}
		}
		
		req.getRequestDispatcher("/input.jsp").forward(req, res);
//		res.sendRedirect("drawRoom.jsp");

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doGet(req, res);
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		String room = req.getParameter("room");
		
		Map<String, String[]> map = req.getParameterMap();
        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println("key=" + key );
            String[] value =  (String[]) map.get(key);
            System.out.print("value=");
            for(String v:value){                
                System.out.print(v + "  ");
            }            
            System.out.println();
        }
        
        System.out.println(room);
	}


}
