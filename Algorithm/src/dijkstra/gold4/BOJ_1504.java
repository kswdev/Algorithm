package dijkstra.gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Comparator.comparingInt;

public class BOJ_1504 {

    private static int INF = 2_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeNum = Integer.parseInt(st.nextToken());
        int edgeNum = Integer.parseInt(st.nextToken());

        int[][] graph = new int[nodeNum+1][nodeNum+1];

        for (int i = 1; i <= nodeNum; i++) {
            for (int j = 1; j <= nodeNum; j++) {
                if (i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }

        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start][end] = weight;
            graph[end][start] = weight;
        }

        st = new StringTokenizer(br.readLine());

        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        int essentialDistance = shortestDistance(graph, startNode, endNode);

        long remnantDistance1 = shortestDistance(graph, 1, startNode) + shortestDistance(graph, endNode, nodeNum);
        long remnantDistance2 = shortestDistance(graph, 1, endNode) + shortestDistance(graph, startNode, nodeNum);

        long remnantDistance = Math.min(remnantDistance1, remnantDistance2);

        if (essentialDistance >= INF) System.out.println(-1);
        else if (remnantDistance >= INF) System.out.println(-1);
        else System.out.println(essentialDistance + remnantDistance);
    }

    private static int shortestDistance(int[][] graph, int startNode, int endNode) {
        int[] dist = new int[graph.length+1];
        Arrays.fill(dist, INF);
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparingInt(a -> a[1]));

        pq.offer(new int[] {startNode, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currentEndNode = curr[0];
            int currentWeight = curr[1];

            for (int i = 1; i < graph.length; i++) {
                // 간선으로 연결 되어 있을 때
                if (graph[currentEndNode][i] != INF) {
                    int startToINodeWeight = graph[currentEndNode][i] + currentWeight;
                    // 방문 전 && 거리가 더 짧을 때
                    if (dist[i] > startToINodeWeight) {
                        dist[i] = startToINodeWeight;
                        pq.offer(new int[]{i, startToINodeWeight});
                    }
                }
            }
        }
        return dist[endNode];
    }
}
