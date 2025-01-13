package dynamic_programming.gold5.treeAndQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15681 {

    private static int N, R, Q;
    private static List<List<Integer>> tree = new ArrayList<>();
    private static List<Node> nodes = new ArrayList<>();
    private static int[] size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        size = new int[N+1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
            nodes.add(new Node());
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child  = Integer.parseInt(st.nextToken());

            tree.get(parent).add(child);
            tree.get(child).add(parent);
        }

        makeTree(R, -1);

        for (int i = 0; i < Q; i++) {
            int node = Integer.parseInt(br.readLine());
            countSubtreeNodes(node);
            sb.append(size[node]).append("\n");
        }

        System.out.println(sb);
    }

    private static void countSubtreeNodes(int currentNode) {
        if (size[currentNode] == 0) {
            size[currentNode] = 1;
            for (int child : nodes.get(currentNode).getChildNode()) {
                countSubtreeNodes(child);
                size[currentNode] += size[child];
            }
        }
    }

    private static void makeTree(int currentNode, int parent) {
        for (int node : tree.get(currentNode)) {
            if (node != parent) {
                nodes.get(currentNode).getChildNode().add(node);
                makeTree(node, currentNode);
            }
        }
    }

    private static class Node {
        private List<Integer> childNode = new ArrayList<>();

        public List<Integer> getChildNode() {
            return childNode;
        }
    }
}