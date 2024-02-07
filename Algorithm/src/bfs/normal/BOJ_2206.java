package bfs.normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2206 {
	private static int a;
	private static int b;
	private static int result = -1;
	
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	private static int[][] map;
	private static boolean[][][] visited;
	private static Queue<int[]> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String[] inputSplit = input.split(" ");
		
		a = Integer.parseInt(inputSplit[0]);
		b = Integer.parseInt(inputSplit[1]);
		
		map = new int[a][b];
		visited = new boolean[a][b][2];
		
		for(int i = 0; i < a; i++) {
			String line = br.readLine();
			for(int j = 0; j < b; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
			}
		}
		bfs();
		if(a == 1 && b == 1) System.out.println(1);
		else System.out.println(result);
	}
	
	public static void bfs() {
		q = new LinkedList<>();
		q.offer(new int[] {0, 0, 0, 1});
		
		
		visited[0][0][0] = true;
		while(!q.isEmpty()) {
			int nx   = q.peek()[0];
			int ny   = q.peek()[1];
			int dest = q.peek()[2];
			int cnt  = q.peek()[3];
			
			q.poll();
			for(int i = 0; i < 4; i++) {
				int curx = nx + dx[i];
				int cury = ny + dy[i];
				
				if(curx >= 0 && cury >= 0 && curx < a && cury < b) {
				
					int nCnt = cnt + 1;
					if(curx == a-1 && cury == b-1) {
						result = nCnt;
						return;
					}
					
					//���� ���� ��
					if(map[curx][cury] == 0 && !visited[curx][cury][0] && dest == 0) {
						visited[curx][cury][0] = true;
						System.out.println(curx + " : curx " + cury + " : cury " + cnt);
						q.offer(new int[] {curx, cury, 0, nCnt});
					} else if(map[curx][cury] == 0 && !visited[curx][cury][1] && dest == 1) {
						visited[curx][cury][1] = true;
						q.offer(new int[] {curx, cury, 1, nCnt});
					}
					
					if(map[curx][cury] == 1 && dest == 0) {
						visited[curx][cury][1] = true;
						q.offer(new int[] {curx, cury, 1, nCnt});
						
					}
				}
			}
		}
	}
}
