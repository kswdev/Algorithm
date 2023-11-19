package bfs;

import java.io.*;
import java.util.*;

public class BOJ_2644 {

    static int n, m, x, y;
    static int[][] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new int[n+1][n+1];
        dist = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++){
            st =  new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = graph[y][x] = 1;
        }

        bfs(x);

        System.out.println(dist[y] == 0 ? -1 : dist[y]);
    }

    public static void bfs(int index){
        Queue<Integer> que = new LinkedList<>();
        que.add(index);

        while(!que.isEmpty()){
            int temp = que.poll();

            if(temp == y) {
                break;
            }

            for(int i=1; i<=n; i++){
                if(graph[temp][i]==1 && dist[i]==0){
                    que.add(i);
                    dist[i] = dist[temp]+1;
                }
            }
        }
    }
}
