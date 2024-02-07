package bfs.normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1260 {
	private static int[][] map;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		String[] inputSplit = input.split(" ");
		
		int areaNum = Integer.parseInt(inputSplit[0]);
		int lineNum = Integer.parseInt(inputSplit[1]);
		int start   = Integer.parseInt(inputSplit[2]);
		
		visited = new boolean[areaNum + 1];
		map		= new int[areaNum+1][areaNum+1];
		
		for(int i = 0; i < lineNum; i++) {
			String str 		  = br.readLine();
			String[] strSplit = str.split(" ");
			
			int a = Integer.parseInt(strSplit[0]);
			int b = Integer.parseInt(strSplit[1]);
			
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		dfs(start, areaNum);
		System.out.println("");
		bfs(start, areaNum);
	}
	public static void dfs(int n, int num) {
		
		System.out.print(n + " ");
		visited[n] = true;
		for(int i = 1; i <= num; i++) {
			if(visited[i] || map[n][i] == 0) {
				continue;
			}
			dfs(i, num);
		}
	}
	
	public static void bfs(int n, int num) {
		
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(n);
		visited[n] = false;
		while(!q.isEmpty()) {
			n = q.poll();
			System.out.print(n + " ");
			for(int i = 1; i <= num; i++) {
				if(!visited[i] || map[n][i] == 0) continue;
				q.offer(i);
				visited[i] = false;
			}
		}
	}
}
