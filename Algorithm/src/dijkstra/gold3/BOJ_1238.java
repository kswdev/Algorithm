package dijkstra.gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1238 {

    private static int N, M, X;
    private static List<List<Node>> nodes = new ArrayList<>();
    private static int[] go;
    private static int[] back;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //사람, 마을 수
        N = Integer.parseInt(st.nextToken());
        //도로 수
        M = Integer.parseInt(st.nextToken());
        //모입 위치
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            nodes.get(start).add(new Node(end, time));
        }

        back = new int[N + 1];
        Arrays.fill(back, Integer.MAX_VALUE);

        foundShortestBackTime();

        int max = 0;
        for (int i = 1; i <= N; i++) {
            go = new int[N + 1];
            Arrays.fill(go, Integer.MAX_VALUE);

            visited = new boolean[N + 1];
            foundShortestGoTime(i);
            if (max < back[i] + go[X])
                max = back[i] + go[X];
        }

        System.out.println(max);
    }

    private static void foundShortestBackTime() {
        Queue<Node> queue = new PriorityQueue<>();
        back[X] = 0;
        queue.add(new Node(X, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node next : nodes.get(current.getVillage())) {
                if (back[next.getVillage()] > back[current.getVillage()] + next.getTime()) {
                    back[next.getVillage()] = back[current.getVillage()] + next.getTime();
                    queue.add(new Node(next.getVillage(), back[next.getVillage()]));
                }
            }
        }
    }

    private static void foundShortestGoTime(int i) {
        Queue<Node> queue = new PriorityQueue<>();

        go[i] = 0;

        queue.add(new Node(i, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (visited[current.getVillage()])
                continue;

            visited[current.getVillage()] = true;

            for (Node next : nodes.get(current.getVillage())) {
                if (go[next.getVillage()] > go[current.getVillage()] + next.getTime()) {
                    go[next.getVillage()] = go[current.getVillage()] + next.getTime();
                    queue.add(new Node(next.getVillage(), go[next.getVillage()]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int village;
        int time;

        public int getVillage() {
            return village;
        }

        public int getTime() {
            return time;
        }

        public Node(int village, int time) {
            this.village = village;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return time - o.time;
        }
    }
}
