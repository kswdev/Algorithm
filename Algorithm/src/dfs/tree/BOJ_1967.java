package dfs.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1967 {
    private static int V;
    private static List<List<Node>> list;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());


        list    = new ArrayList<>();

        StringTokenizer st;

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i <= V; i++) {
            st = new StringTokenizer(br.readLine());

            int start  = Integer.parseInt(st.nextToken());
            int end    = Integer.parseInt(st.nextToken());
            int cost   = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(end, cost));
            list.get(end).add(new Node(start, cost));
        }

        visited = new boolean[V+1];
        dfs(1, 0);
    }

    private static void dfs(int start, int cost) {

    }

    private static class Node {
        private int end;
        private int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int getEnd() {
            return end;
        }

        public int getCost() {
            return cost;
        }
    }
}
