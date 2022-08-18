package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2606 {
	
	private static ArrayList<Integer> list[];
	private static Queue<Integer> q;
	private static int[] visited;
	private static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cumNum  = Integer.parseInt(br.readLine());
		int lineNum = Integer.parseInt(br.readLine());
		
		visited = new int[cumNum + 1];
		q		= new LinkedList<>();
		list    = new ArrayList[cumNum + 1];
		
		for(int i = 0; i < cumNum + 1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < lineNum; i++) {
			String input = br.readLine();
			String[] inputSplit = input.split(" ");
			
			int a = Integer.parseInt(inputSplit[0]);
			int b = Integer.parseInt(inputSplit[1]);
			
			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i = 0; i < cumNum + 1; i++) {
			list[i].sort(null);
		}
		bfs(1);
		System.out.println(cnt-1);
	}
	public static void bfs(int R) {
		cnt = 0;
		visited[R] = ++cnt;
		q.offer(R);
		while(!q.isEmpty()) {
			Integer b = q.poll();
			for(Integer a : list[b]) {
				if(visited[a] > 0) continue;
				q.offer(a);
				visited[a] = ++cnt;
			}
		}
	}
}
 