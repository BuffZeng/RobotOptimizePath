package algorithm;

import java.util.Arrays;

public class Room {
	
	// room row, column, obstacles' positions
	private int row;
	private int col;
	private int[] obsx;
	private int[] obsy;
	
	public Room(int row, int col, int[] obsx, int[] obsy) {
		this.row = row;
		this.col = col;
		this.obsx = obsx;
		this.obsy = obsy;
	}


	// Set up grid graph through users preference.
	public int[][] createRoom(int row, int col, int[] obsx, int[] obsy) {
		int[][] room = new int[row][col];
		// Set all as available path
		for (int[] r: room) {
		    Arrays.fill(r, 1);
		}
		for (int i = 0; i < obsx.length; i++) {
			// Set obstacles
			room[obsx[i]][obsy[i]] = 0;
		}
		return room;
	}
}
