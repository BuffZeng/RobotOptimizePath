package algorithm;

import java.util.Arrays;
import java.util.Stack;

public class ASTAR {

	private int[][] room;
	private int row;
	private int col;
	private int initx;
	private int inity;
	private int finx;
	private int finy;

	public ASTAR(int[][] room, int row, int col, int initx, int inity, int finx, int finy) {
		this.room = room;
		this.row = row;
		this.col = col;
		this.initx = initx;
		this.inity = inity;
		this.finx = finx;
		this.finy = finy;
	}

	private class Node {
		private int x;
		private int y;
		private int f = Integer.MAX_VALUE;
		// Present node's father node.
		private Node Father = null;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public char[][] astar() {
		Stack<Node> open = new Stack<>();
		Stack<Node> close = new Stack<>();

		Node start = new Node(initx, inity);
		// Put start node into open list.
		open.add(start);
		while (! open.isEmpty()) {
			Node curMinF = GetMinFNode(open);
			// Remove curMinF from open list.
			open.remove(curMinF);
			// Put curMinF into close list.
			close.add(curMinF);

			// If reach the destination.
			if (curMinF.x == finx && curMinF.y == finy) {
				break;
			} else {
				// Run through four directions.
				// right, down, left, up
				// {0,1},{1,0},{0,-1},{-1,0}
				// Right
				CheckChildNode(curMinF, curMinF.x, curMinF.y + 1, open, close);
				// Down
				CheckChildNode(curMinF, curMinF.x + 1, curMinF.y, open, close);
				// Left
				CheckChildNode(curMinF, curMinF.x, curMinF.y - 1, open, close);
				// Up
				CheckChildNode(curMinF, curMinF.x - 1, curMinF.y, open, close);
			}
		}
		return getPath(close);
	}

	private Node GetMinFNode(Stack<Node> open) {
		int minF = Integer.MAX_VALUE;
		for (Node i : open) {
			// Update min if found to be more than the current element
			if (minF > i.f) {
				minF = i.f;
			}
		}

		for (Node n : open) {
			if (n.f == minF) {
				return n;
			}    
		}
		return null;
	}

	private void CheckChildNode(Node curMinF, int x, int y, Stack<Node> open, Stack<Node> close) {
		Node c = null;
		if (x >= 0 && x < row && y >= 0 && y < col && room[x][y] != 0) {
			c = new Node(x, y);
			if (! inList(c, open) && ! inList(c, close)) {
				c.f = getFValue(x, y);
				c.Father = curMinF;
				open.add(c);
			}
		}
	}

	private boolean inList(Node c, Stack<Node> l) {
		for (Node n : l) {
			if (n.x == c.x && n.y == c.y) {
				return true;
			}
		}
		return false;
	}

	private int getFValue(int x, int y) {
		// g(n): cost of from start to search point
		// h(n): cost of from search point to destination
		// Use Manhattan distance: d(i,j)=|x1−x2|+|y1−y2|
		int g = Math.abs(initx - x) + Math.abs(inity - y);
		int h = Math.abs(finx - x) + Math.abs(finy - y);
		// f(n) = g(n) + h(n)
		return g+h;
	}

	public char[][] getPath(Stack<Node> close) {
		// Setup an empty 2D array as path.
		char[][] path = new char[row][col];
		for (char[] p : path) {
			Arrays.fill(p, '-');
		}
		
		int discnt = 0;
		
		path[initx][inity] = '*';
		
		System.out.print("\nSpace: " + close.size());
		
		Node cur = close.peek();
		while (cur.Father != null) {
			path[cur.x][cur.y] = '*';
			discnt++;
			cur = cur.Father;
		}
		
		System.out.print("\nDistance: " + discnt);
		
		return path;
	}
}
