package algorithm;

import java.util.ArrayList;
import java.util.Random;

//import java.io.File;
//import java.io.FileOutputStream;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {

	public static ArrayList<Long> bfstime = new ArrayList<>();
//	public static ArrayList<Long> dfstime = new ArrayList<>();
	public static ArrayList<Long> astartime = new ArrayList<>();
	public static ArrayList<Long> djtime = new ArrayList<>();

	public static ArrayList<Integer> bfsdis = new ArrayList<>();
//	public static ArrayList<Integer> dfsdis = new ArrayList<>();
	public static ArrayList<Integer> astardis = new ArrayList<>();
	public static ArrayList<Integer> djdis = new ArrayList<>();

	public static ArrayList<Integer> bfsspace = new ArrayList<>();
//	public static ArrayList<Integer> dfsspace = new ArrayList<>();
	public static ArrayList<Integer> astarspace = new ArrayList<>();
	public static ArrayList<Integer> djspace = new ArrayList<>();
	
	public static ArrayList<Integer> obsNum = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		// 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 150, 200
		// 250, 300, 350, 400, 450. 500
		int row = 450;
		int col = 450;

		int count = 15;
		while (count > 0) {
			count = roomCreate(row, col, count);
			System.out.println("\n" + (15 - count) + "--------------------------------------");
		}

		System.out.println("~~~~~~~~~ Space ~~~~~~~~~");
		System.out.println(bfsspace.toString());
//		System.out.println(dfsspace.toString());
		System.out.println(astarspace.toString());
		System.out.println(djspace.toString());

		System.out.println("~~~~~~~~~ Distance ~~~~~~~~~");
		System.out.println(bfsdis.toString());
//		System.out.println(dfsdis.toString());
		System.out.println(astardis.toString());
		System.out.println(djdis.toString());

		System.out.println("~~~~~~~~~ Time ~~~~~~~~~");
		System.out.println(bfstime.toString());
//		System.out.println(dfstime.toString());
		System.out.println(astartime.toString());
		System.out.println(djtime.toString());
		
		System.out.println("~~~~~~~~~ Time ~~~~~~~~~");
		System.out.println(obsNum.toString());

//		//Create blank workbook
//		XSSFWorkbook workbook = new XSSFWorkbook();
//
//		//Create a blank sheet
//		XSSFSheet spreadsheet = workbook.createSheet( "Algorithm Record");
//
//		//Create row object
//		XSSFRow xssrow;
//
//		Cell cell;
//		int rowid = 0;
//		int i = 0;
//		while (rowid < bfsdis.size()) {
//			xssrow = spreadsheet.createRow(rowid++);
//			cell = xssrow.createCell(0);
//			cell.setCellValue(obsNum.get(i));
//			
//			cell = xssrow.createCell(1);
//			cell.setCellValue(bfsspace.get(i));
//			cell = xssrow.createCell(2);
//			cell.setCellValue(bfsdis.get(i));
//			cell = xssrow.createCell(3);
//			cell.setCellValue(bfstime.get(i));
//
//			cell = xssrow.createCell(4);
//			cell.setCellValue(dfsspace.get(i));
//			cell = xssrow.createCell(5);
//			cell.setCellValue(dfsdis.get(i));
//			cell = xssrow.createCell(6);
//			cell.setCellValue(dfstime.get(i));
//
//			cell = xssrow.createCell(4);
//			cell.setCellValue(astarspace.get(i));
//			cell = xssrow.createCell(5);
//			cell.setCellValue(astardis.get(i));
//			cell = xssrow.createCell(6);
//			cell.setCellValue(astartime.get(i));
//
//			cell = xssrow.createCell(7);
//			cell.setCellValue(djspace.get(i));
//			cell = xssrow.createCell(8);
//			cell.setCellValue(djdis.get(i));
//			cell = xssrow.createCell(9);
//			cell.setCellValue(djtime.get(i));
//
//			i++;
//		}
//
//		//Write the workbook in file system
//		FileOutputStream out = new FileOutputStream(
//				new File("/Users/zengyi/Desktop/毕设/Writesheet.xlsx"));
//
//		workbook.write(out);
//		out.close();
//		System.out.println("Writesheet.xlsx written successfully");
	}

	public static int roomCreate(int row, int col, int count) {
		ArrayList<int[]> obs = new ArrayList<>();

		int[] tmp;
		Random random = new Random();

		int initx = random.nextInt(row);
		int inity = random.nextInt(col);
		int finx = random.nextInt(row);
		int finy = random.nextInt(col);

		int rnd = random.nextInt(5, row * col);
		
		System.out.println("Number of Obstacles: " + rnd);
		for(int i = 0; i < rnd; i++) {
			tmp = new int[2];
			tmp[0] = random.nextInt(row);
			tmp[1] = random.nextInt(col);
			obs.add(tmp);
		}

		int[] obsx = new int[obs.size()];
		int[] obsy = new int[obs.size()];
		for(int i = 0; i < obs.size(); i++) {
			obsx[i] = obs.get(i)[0];
			obsy[i] = obs.get(i)[1];
		}

		Room r = new Room(row, col, obsx, obsy, initx, inity, finx, finy);
		int[][] room = r.createRoom();
		if (r.checkPass(room)) {
			
			obsNum.add(rnd);
			
			System.out.println("Start Point: (" + initx + ", " + inity + ")");
			System.out.println("End Point: (" + finx + ", " + finy + ")");
			System.out.println("Obstacles: ");
			for(int i = 0; i < obs.size(); i++) {
				System.out.println("(" + obsx[i] + ", " + obsy[i] + ")");
			}

			System.out.println("Room Layout");
			System.out.print("==========================================");
			for(int i = 0; i < row; i++) {
				System.out.println();
				for(int j = 0; j < col; j++) {
					System.out.print(room[i][j]);
				}
			}

			System.out.println("\n\nRoom Validation: " + r.checkPass(room));

			System.out.println("\n\n" + "BFS Path Result");
			System.out.print("==========================================");

			BFS b = new BFS(room, row, col, initx, inity, finx, finy);

			long startTime=System.nanoTime();

			char[][] bfspath = b.getPath();

			long endTime=System.nanoTime();
			System.out.println("\nTime: "+(endTime-startTime)+"ns");

			for(int i = 0; i < row; i++) {
				System.out.println();
				for(int j = 0; j < col; j++) {
					System.out.print(bfspath[i][j]);
				}
			}

			bfstime.add(endTime-startTime);
			bfsdis.add(b.getDiscnt());
			bfsspace.add(b.getSpacecnt());


//			System.out.println("\n\n" + "DFS Path Result");
//			System.out.print("==========================================");
//
//			DFS d = new DFS(room, row, col, initx, inity, finx, finy);
//
//			startTime=System.nanoTime();
//
//			char[][] dfspath = d.initdfs();
//
//			endTime=System.nanoTime();
//			System.out.println("\nTime: "+(endTime-startTime)+"ns");
//
//			for(int i = 0; i < row; i++) {
//				System.out.println();
//				for(int j = 0; j < col; j++) {
//					System.out.print(dfspath[i][j]);
//				}
//			}
//
//			dfstime.add(endTime-startTime);
//			dfsdis.add(d.getDiscnt());
//			dfsspace.add(d.getSpacecnt());


			System.out.println("\n\n" + "A* Path Result");
			System.out.print("==========================================");

			ASTAR astar = new ASTAR(room, row, col, initx, inity, finx, finy);

			startTime=System.nanoTime();

			char[][] astarpath = astar.getPath();

			endTime=System.nanoTime();
			System.out.println("\nTime: "+(endTime-startTime)+"ns");

			for(int i = 0; i < row; i++) {
				System.out.println();
				for(int j = 0; j < col; j++) {
					System.out.print(astarpath[i][j]);
				}
			}

			astartime.add(endTime-startTime);
			astardis.add(astar.getDiscnt());
			astarspace.add(astar.getSpacecnt());


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

			djtime.add(endTime-startTime);
			djdis.add(dj.getDiscnt());
			djspace.add(dj.getSpacecnt());

			if (b.getSpacecnt() != -1) {
				count--;
			}
		} else {
			System.out.println("There might not have a way!");
		}

		return count;
	}
}

