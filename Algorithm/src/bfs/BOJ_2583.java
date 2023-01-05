package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2583 {
	public static int M;
	public static int N;
	public static int K;
	public static int Num = 0;
	public static int count;
	
	public static int[] dx = {0, 0, 1, -1};
	public static int[] dy = {1, -1, 0, 0};
	
	public static int	 [][] map;
	public static boolean[][] visited;
	public static ArrayList<Integer> width;
	
	private static Queue<int[]> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String MNK = br.readLine();
		String[] MNKsplit = MNK.split(" ");
		
		M = Integer.parseInt(MNKsplit[0]);
		N = Integer.parseInt(MNKsplit[1]);
		K = Integer.parseInt(MNKsplit[2]);
		
		q		= new LinkedList<>();
		map 	= new int[M][N];
		width	= new ArrayList<>();
		visited = new boolean[M][N];
		
		for(int i = 0; i < K; i++) {
			String loc = br.readLine();
			String[] locSplit = loc.split(" ");
			
			int x1 = Integer.parseInt(locSplit[0]);
			int y1 = Integer.parseInt(locSplit[1]);
			int x2 = Integer.parseInt(locSplit[2]);
			int y2 = Integer.parseInt(locSplit[3]);
			
			for(int y = y1; y < y2; y++) {
				for(int x = x1; x < x2; x++) {
					map[y][x] = 1;
				}
			}
		}
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != 1 && !visited[i][j]) {
					count = 1;
					Num++;
					bfs(i, j);
					width.add(count);
				}
			}
		}
		
		System.out.println(width.size());
		width.sort(Comparator.naturalOrder());
		for (Integer integer : width) {
			System.out.print(integer+ " ");
		}
	}
	
	public static void bfs(int y, int x) {
		
		q.offer(new int[] {y, x});
		while(!q.isEmpty()) {
			int ny = q.peek()[0];
			int nx = q.peek()[1];
			
			visited[ny][nx] = true;
			
			q.poll();
			for(int i = 0; i < 4; i++) {
				int curx = nx + dx[i];
				int cury = ny + dy[i];
				
				if(curx >= 0 && cury >= 0 && curx < N && cury < M) {
					if(!visited[cury][curx] && map[cury][curx] != 1) {
						visited[cury][curx] = true;
						count++;
						q.offer(new int[] {cury, curx});
					}
				}
			}
		}
	}
}
