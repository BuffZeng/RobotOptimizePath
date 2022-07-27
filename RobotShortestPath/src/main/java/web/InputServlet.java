package web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input")
public class InputServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HashMap<String, String> messages = new HashMap<>();

		req.setAttribute("messages", messages);

		String rowString = req.getParameter("row");
		String colString = req.getParameter("col");

		if (rowString == null || colString == null ||
				rowString.trim().isEmpty() || colString.trim().isEmpty()) {
			messages.put("Fail", "Please enter valid number!");
		} else {
			int row = Integer.parseInt(rowString);
			int col = Integer.parseInt(colString);
			if (row <= 1 && col <= 1) {
				messages.put("Fail", "Please enter valid number!");
			} else if (row * col > 250000) {
				messages.put("Fail", "Please enter valid number!");
			} else {
				messages.put("Success", "Success");

				req.setAttribute("row", row);
				req.setAttribute("col", col);
			}
		}

		req.getRequestDispatcher("/input.jsp").forward(req, res);

	}


}
