package bfs.normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1697 {
	
	private static Queue<Integer> q;
	private static int[] visited;
	private static int[] move;
	private static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		String[] inputSplit = input.split(" ");
		
		int a = Integer.parseInt(inputSplit[0]);
		int b = Integer.parseInt(inputSplit[1]);
		max = Math.max(a, b);
		
		visited = new int[2*max + 1];
		
		bfs(a, b);
		if(a == b) {
			System.out.println(0);
		} else {
			System.out.println(visited[b]);
		}
	}
	
	public static void bfs(int a, int b) {
		q = new LinkedList<>();
		q.offer(a);
		move = new int[3];
		visited[a] = 0;
		while(!q.isEmpty()) {
			int nA = q.poll();
			
			int nextA = nA+1;
			int backA = nA-1;
			int doubA = 2*nA;
			
			move[0] = nextA;
			move[1] = backA;
			move[2] = doubA;
			
			
			for(int i = 0; i < 3; i++) {
				int curA = move[i];
				
				if(curA >= 2*max + 1) continue;
				
				if(curA == b) {
					visited[curA] = visited[nA] + 1;
					return;
				}
				
				if(curA >= 0 && visited[curA] == 0) {
					q.offer(curA);
					visited[curA] = visited[nA] + 1;
				}
			}
		}
	}
}
