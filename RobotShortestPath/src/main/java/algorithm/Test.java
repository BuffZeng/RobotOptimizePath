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
		

		System.out.println("\n\n" + "BFS Path Result");
		System.out.print("==========================================");
		
		BFS b = new BFS(room, row, col, initx, inity, finx, finy);

		long startTime=System.nanoTime();
		
		int[][] dis = b.bfs();
		char[][] bfspath = b.getPath(dis);
		
		long endTime=System.nanoTime();
		System.out.println("\nTime: "+(endTime-startTime)+"ns");

		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(bfspath[i][j]);
			}
		}
		
		
		System.out.println("\n\n" + "DFS Path Result");
		System.out.print("==========================================");
		
		DFS d = new DFS(room, row, col, initx, inity, finx, finy);

		startTime=System.nanoTime();
		
		char[][] dfspath = d.getPath();
		
		endTime=System.nanoTime();
		System.out.println("\nTime: "+(endTime-startTime)+"ns");

		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(dfspath[i][j]);
			}
		}
		
		
		System.out.println("\n\n" + "A* Path Result");
		System.out.print("==========================================");
		
		ASTAR astar = new ASTAR(room, row, col, initx, inity, finx, finy);
		
		startTime=System.nanoTime();
		
		char[][] astarpath = astar.astar();
		
		endTime=System.nanoTime();
		System.out.println("\nTime: "+(endTime-startTime)+"ns");
		
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(astarpath[i][j]);
			}
		}
		
		System.out.println("\n\n" + "Dijkstra Path Result");
		System.out.print("==========================================");
		
		DIJKSTRA dj = new DIJKSTRA(room, row, col, initx, inity, finx, finy);
		
		startTime=System.nanoTime();
		
		char[][] dijkstrapath = dj.getPath();
		
		endTime=System.nanoTime();
		System.out.println("\nTime: "+(endTime-startTime)+"ns");
		
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(dijkstrapath[i][j]);
			}
		}
		
	}
}
