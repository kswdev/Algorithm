package dynamic_programming.gold2.special_node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1272 {

    private static long[][] dp;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(st.nextToken());

        dp = new long[n+1][n+1];
        visited = new boolean[n+1];

        st = new StringTokenizer(br.readLine());

        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(0,0));

        for (int i = 1; i <= n; i++) {
            nodes.add(new Node(i, Integer.parseInt(st.nextToken())));
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            nodes.get(first).getChildren().add(nodes.get(second));
        }

        Node rootNode = nodes.get(root);
        visited[root] = true;
        System.out.println(dfs(rootNode, nodes.get(0)));
    }

    private static long dfs(Node currentNode, Node previousSpecialNode) {

        if (dp[currentNode.getNum()][previousSpecialNode.getNum()] != 0) {
            return dp[currentNode.getNum()][previousSpecialNode.getNum()];
        }

        long result = 0;
        for (Node nextNode : currentNode.getChildren()) {

            result += Math.min(
                    dfs(nextNode, previousSpecialNode),
                    dfs(nextNode, currentNode) + previousSpecialNode.getWeight()
            );
        }

        result += currentNode.getWeight() - previousSpecialNode.getWeight();

        dp[currentNode.getNum()][previousSpecialNode.getNum()] = result;

        return result;
    }

    private static class Node {
        private int num;
        private int weight;
        private List<Node> children = new ArrayList<>();

        private Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        private List<Node> getChildren() {
            return this.children;
        }

        private int getNum() {
            return this.num;
        }

        private int getWeight() {
            return this.weight;
        }
    }
}
