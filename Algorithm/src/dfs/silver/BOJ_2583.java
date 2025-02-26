package dfs.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2583 {

	public static int M;
	public static int N;
	public static int K;
	public static int count;
	
	public static int[] dx = {0, 0, 1, -1};
	public static int[] dy = {1, -1, 0, 0};
	
	public static int[][] map;
	public static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String[] strSplit = str.split(" ");
		
		N = Integer.parseInt(strSplit[0]);
		M = Integer.parseInt(strSplit[1]);
		K = Integer.parseInt(strSplit[2]);
		
		map 	= new int[M+1][N+1];
		visited = new boolean[M+1][N+1];
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < K; i++) {
			String xyCoord = br.readLine();
			String[] xyCoordSplit = xyCoord.split(" ");
			
			int x1 = Integer.parseInt(xyCoordSplit[0]);
			int y1 = Integer.parseInt(xyCoordSplit[1]);
			int x2 = Integer.parseInt(xyCoordSplit[2]);
			int y2 = Integer.parseInt(xyCoordSplit[3]);
			
			for(int j = x1; j < x2; j++) {
				for(int k = y1; k < y2; k++) {
					visited[j][k] = true;
					map[j][k] = 1;
				}
			}
		}
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != 1) {
					count = 1;
					visited[i][j] = true;
					dfs(i, j);
					list.add(count);
				}
			}
		}
		
		System.out.println(list.size());
		for (Integer integer : list) {
			System.out.println(integer+ " ");
		}
	}
	public static void dfs(int i, int j) {
		map[i][j] = 1;
		for(int k = 0; k < 4; k++) {
			int curx = i + dx[k];
			int cury = j + dy[k];
			
			if(curx >= 0 && cury >= 0 && curx < M && cury < N) {
				if(map[curx][cury] != 1) {
					count++;
					dfs(curx, cury);
				}
			}
		}
	}
}
