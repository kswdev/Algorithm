package dynamic_programming.gold2.special_node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1272 {

    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(st.nextToken());

        dp = new long[n+1][n+1];

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

            Node firstNode = nodes.get(first);
            Node secondNode = nodes.get(second);

            Node parentNode = firstNode.getWeight() < secondNode.getWeight() ? firstNode : secondNode;
            Node childNode = firstNode.getWeight() > secondNode.getWeight() ? firstNode : secondNode;

            parentNode.getChildren().add(childNode);
        }

        Node rootNode = nodes.get(root);
        System.out.println(dfs(rootNode, nodes.get(0)));
    }

    private static long dfs(Node currentNode, Node previousSpecialNode) {

        if (dp[currentNode.getNum()][previousSpecialNode.getNum()] != 0) {
            return dp[currentNode.getNum()][previousSpecialNode.getNum()];
        }

        long currentSpecial = 0;
        long currentNotSpecial = 0;

        for (Node nextNode : currentNode.getChildren()) {
            currentSpecial += dfs(nextNode, currentNode);
            currentNotSpecial += dfs(nextNode, previousSpecialNode);
        }

        currentNotSpecial += (currentNode.getWeight() - previousSpecialNode.getWeight());
        currentSpecial += currentNode.getWeight();

        long result = Math.min(currentSpecial, currentNotSpecial);

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
