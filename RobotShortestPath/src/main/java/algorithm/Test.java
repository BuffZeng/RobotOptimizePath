package algorithm;

public class Test {

	public static void main(String[] args) {
		int row = 5;
		int col = 5;
		int initx = 2;
		int inity = 1;
		int finx = 4;
		int finy = 4;
		int[] obsx = {0, 2};
		int[] obsy = {1, 0};

		Room r = new Room(row, col, obsx, obsy);
		int[][] room = r.createRoom(row, col, obsx, obsy);

		System.out.println("Start Point: (" + initx + ", " + inity + ")");
		System.out.println("End Point: (" + finx + ", " + finy + ")");

		System.out.println("Room Layout");
		System.out.print("==========================================");
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(room[i][j]);
			}
		}
		

		BFS b = new BFS(room, row, col, initx, inity, finx, finy);

		int[][] dis = b.bfs();
		char[][] bfspath = b.getPath(dis);

		System.out.println("\n\n" + "BFS Path Result");
		System.out.print("==========================================");
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(bfspath[i][j]);
			}
		}
		
		
		DFS d = new DFS(room, row, col, initx, inity, finx, finy);

		char[][] dfspath = d.getPath();


		System.out.println("\n\n" + "DFS Path Result");
		System.out.print("==========================================");
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(dfspath[i][j]);
			}
		}
		
		
		ASTAR astar = new ASTAR(room, row, col, initx, inity, finx, finy);
		char[][] astarpath = astar.astar();
		
		System.out.println("\n\n" + "A* Path Result");
		System.out.print("==========================================");
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(astarpath[i][j]);
			}
		}
		
		
		DIJKSTRA dj = new DIJKSTRA(room, row, col, initx, inity, finx, finy);
		char[][] dijkstrapath = dj.getPath();
		
		System.out.println("\n\n" + "Dijkstra Path Result");
		System.out.print("==========================================");
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(dijkstrapath[i][j]);
			}
		}
		
	}
}
