package algorithm;

public class temp {

	public static void main(String[] args) {
//		int row = 8;
//		int col = 9;
//
		String string = "[[1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1],[1,1,1,0,1,1,1,1,1],[1,1,0,1,1,1,1,0,1],[1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1]]";

		System.out.println(string);
		
		
		char[][] path = {{'1','1','1','1'},{'1','1','1','1'},{'1','1','1','1'}};

		StringBuilder sb = new StringBuilder();

		sb.append("[");

		for (int i = 0; i < path.length; i++) {
			sb.append("[");
			for (int j = 0; j < path[0].length; j++) {
				sb.append(path[i][j]);
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
		
		System.out.println(sb.toString());
	}

}
