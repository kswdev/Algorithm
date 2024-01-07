package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1753 {

    private static int V, E, K;
    private static List<List<Node>> list = new ArrayList<>();
    private static int [] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i <= E; i++) {
            list.add(new ArrayList<>());
        }

        dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end   = Integer.parseInt(st.nextToken());
            int w     = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(end, w));
        }
        dijkstra(K);

        for(int i=1;i<dist.length;i++) {
            if(dist[i]== Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(dist[i]);
        }
    }

    private static void dijkstra(int k) {
        Queue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(k, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (Node next : list.get(now.end)) {
                if (dist[next.end] > now.weight + next.weight) {
                    dist[next.end] = now.weight + next.weight;
                    pq.add(new Node(next.end, dist[next.end]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node>{
        int end, weight;

        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}
