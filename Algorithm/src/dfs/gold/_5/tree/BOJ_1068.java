package dfs.gold._5.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1068 {

    private static int nodeNum, removedNum, rootNode;
    private static int cnt = 0;
    private static final List<Node> nodes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nodeNum = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        removedNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < nodeNum; i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0; i < nodeNum; i++) {
            Node node = new Node(i);
            int parentNodeNum = Integer.parseInt(st.nextToken());

            if (parentNodeNum != -1 && i != removedNum)
                nodes.get(parentNodeNum).getChildNodes().add(node);
            else if (parentNodeNum == -1) rootNode = i;
        }

        dfs(rootNode);

        System.out.println(cnt);
    }

    private static void dfs(int currentNodeNum) {
        Node currentNode = nodes.get(currentNodeNum);

        if (currentNode.getNum() == removedNum)
            return;

        List<Node> children = currentNode.getChildNodes();

        if (children.isEmpty()) {
            cnt++;
            return;
        }

        for (Node child : children) {
            //지워진 노드인 경우 탐색하지 않는다.
            dfs(child.getNum());
        }

    }

    private static class Node {
        private int num;
        private final List<Node> childNodes = new ArrayList<>();

        public Node(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<Node> getChildNodes() {
            return childNodes;
        }
    }
}
