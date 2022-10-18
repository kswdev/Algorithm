package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1987 {
	private static int[][] map;
	private static int N;
	private static int M;
	private static int ans;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String[] inputSplit = input.split(" ");
		
		N = Integer.parseInt(inputSplit[0]);
		M = Integer.parseInt(inputSplit[1]);
		
		map = new int[N][M];
		visited = new boolean[30];
		
		for(int i = 0; i < N; i++) {
			String input2 = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = input2.charAt(j) - 'A';
			}
		}
		dfs(0, 0, 0);
		System.out.println(ans);
	}
	
	public static void dfs(int x, int y, int count) {
		if(visited[map[x][y]]) {
			ans = Math.max(ans, count);
			return;
		} else {
			visited[map[x][y]] = true;
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
					dfs(nx, ny, count + 1);
				}
			}
			visited[map[x][y]] = false;
		}
	}
}
