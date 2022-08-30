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

import algorithm.ASTAR;
import algorithm.BFS;
import algorithm.DIJKSTRA;

@WebServlet("/input")
public class InputServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HashMap<String, String> messages = new HashMap<>();

		// Create a session object if it is already not  created.
		HttpSession session = req.getSession(true);

		session.setAttribute("messages", messages);

		String rowString = req.getParameter("row");
		String colString = req.getParameter("col");
		String initxString = req.getParameter("initx");
		String inityString = req.getParameter("inity");
		String finxString = req.getParameter("finx");
		String finyString = req.getParameter("finy");
		String algo =  req.getParameter("algo");

		if (rowString == null || rowString.trim().isEmpty()) {
			messages.put("Fail", "Please enter valid row number!");
		} else if (colString == null || colString.trim().isEmpty()) {
			messages.put("Fail", "Please enter valid column number!");
		} else if (initxString == null || initxString.trim().isEmpty()) {
			messages.put("Fail", "Please enter valid x of Starter Point!");
		} else if (inityString == null || inityString.trim().isEmpty()) {
			messages.put("Fail", "Please enter valid y of Starter Point!");
		} else if (finxString == null || finxString.trim().isEmpty()) {
			messages.put("Fail", "Please enter valid x of End Point!");
		} else if (finyString == null || finyString.trim().isEmpty()) {
			messages.put("Fail", "Please enter valid y of End Point!");
		} else {
			int row = Integer.parseInt(rowString);
			int col = Integer.parseInt(colString);
			int initx = Integer.parseInt(initxString);
			int inity = Integer.parseInt(inityString);
			int finx = Integer.parseInt(finxString);
			int finy = Integer.parseInt(finyString);

			if (row < 2) {
				messages.put("Fail", "Please enter valid row number!");
			} else if (col < 2) {
				messages.put("Fail", "Please enter valid column number!");
			} else if (row * col > 10000 || row * col < 2) {
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

				session.setAttribute("row", row);
				session.setAttribute("col", col);
				session.setAttribute("initx", initx);
				session.setAttribute("inity", inity);
				session.setAttribute("finx", finx);
				session.setAttribute("finy", finy);
				session.setAttribute("algo", algo);
			}
		}


		req.getRequestDispatcher("/input.jsp").forward(req, res);
		//		res.sendRedirect("drawRoom.jsp");

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		// Create a session object if it is already not  created.
		HttpSession session = req.getSession(true);

		String roomString =  req.getParameter("room");

		session.setAttribute("room", roomString);

		int row = (int) session.getAttribute("row");
		int col = (int) session.getAttribute("col");
		int initx = (int) session.getAttribute("initx");
		int inity = (int) session.getAttribute("inity");
		int finx = (int) session.getAttribute("finx");
		int finy = (int) session.getAttribute("finy");
		String algo = (String) session.getAttribute("algo");

		String[] result = getPath(row, col, initx, inity, finx, finy, algo, roomString);
		String path = result[0];
		String time = result[1];
		String disCnt = result[2];
		String spaceCnt = result[3];

		session.setAttribute("path", path);
		session.setAttribute("time", time);
		session.setAttribute("disCnt", disCnt);
		session.setAttribute("spaceCnt", spaceCnt);

		//		req.getRequestDispatcher("/result.jsp").forward(req, res);
		res.sendRedirect("result.jsp");
	}

	public int[][] getRoom(int row, int col, String r) {

		int[][] room = new int[row][col];

		char[] s = r.replaceAll("[\\,\\[\\],-]", "").toCharArray();

		int index = 0;

		for(int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				room[i][j] = Character.getNumericValue(s[index]);
				index++;
			}
		}

		return room;
	}

	public String[] getPath(int row, int col, int initx, int inity, int finx, int finy, String algo, String r) {

		String[] res = new String[4];
		
		int[][] room = new int[row][col];

		room = getRoom(row, col, r);

		if (algo.equals("bfs")) {
			long startTime=System.nanoTime();
			BFS b = new BFS(room, row, col, initx, inity, finx, finy);
			long endTime=System.nanoTime();
			res[0] = jsonPath(b.getPath());
			res[1] = String.valueOf(endTime-startTime);
			res[2] = String.valueOf(b.getDiscnt());
			res[3] = String.valueOf(b.getSpacecnt());
			return res;
		} else if (algo.equals("dijkstra")) {
			long startTime=System.nanoTime();
			DIJKSTRA dj = new DIJKSTRA(room, row, col, initx, inity, finx, finy);
			long endTime=System.nanoTime();
			res[0] = jsonPath(dj.getPath());
			res[1] = String.valueOf(endTime-startTime);
			res[2] = String.valueOf(dj.getDiscnt());
			res[3] = String.valueOf(dj.getSpacecnt());
			return res;
		} else if (algo.equals("astar")) {
			long startTime=System.nanoTime();
			ASTAR astar = new ASTAR(room, row, col, initx, inity, finx, finy);
			long endTime=System.nanoTime();
			res[0] = jsonPath(astar.getPath());
			res[1] = String.valueOf(endTime-startTime);
			res[2] = String.valueOf(astar.getDiscnt());
			res[3] = String.valueOf(astar.getSpacecnt());
			return res;
			}
		return res;
	}
	
	public long getTime(long time) {
		return time;
	}

	public String jsonPath(char[][] path) {
		StringBuilder sb = new StringBuilder();

		sb.append("[");

		for (int i = 0; i < path.length; i++) {
			sb.append("[");
			for (int j = 0; j < path[0].length; j++) {
				if (path[i][j] == '-') {
					sb.append(0);
				} else {
					sb.append(1);
				}
				if (j != path[0].length - 1) {
					sb.append(",");
				}
			}
			sb.append("]");
			if (i != path.length - 1) {
				sb.append(",");
			}
		}
		sb.append("]");

		return sb.toString();

	}


}
