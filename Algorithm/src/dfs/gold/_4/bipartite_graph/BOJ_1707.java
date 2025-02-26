package dfs.gold._4.bipartite_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1707 {

    private static int testCaseCnt;
    private static ArrayList<ArrayList<Integer>> list;
    private static int[] top;
    private static boolean result;
    private static List<Boolean> answers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        testCaseCnt = Integer.parseInt(br.readLine());
        answers = new ArrayList<>();

        for (int i = 0; i < testCaseCnt; i++) {
            result = false;
            list = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            int topNum = Integer.parseInt(st.nextToken());

            top = new int[topNum + 1];

            for (int k = 0; k <= topNum; k++) {
                list.add(new ArrayList<>());
            }

            int relativeNum = Integer.parseInt(st.nextToken());

            for (int j = 0; j < relativeNum; j++) {
                String[] split = br.readLine().split(" ");

                int a = Integer.parseInt(split[0]);
                int b = Integer.parseInt(split[1]);

                System.out.println("a : " + a + " b: " + b);

                list.get(a).add(b);
                list.get(b).add(a);
            }

            for (int k = 1; k < list.size(); k++) {
                if(top[k] == 0) {
                    dfs(k, 1);
                }
            }

            if(result) {
                answers.add(Boolean.FALSE);
            } else {
                answers.add(Boolean.TRUE);
            }
        }

        answers.forEach(answer -> {
            if(Boolean.FALSE.equals(answer)) System.out.println("NO");
            else System.out.println("YES");
        });
    }

    public static void dfs(int parent, int flag) {

        top[parent] = flag;
        List<Integer> childList = list.get(parent);

        for (int child : childList) {
            if (top[child] == flag) {
                result = true;
                return;
            }

            if(top[child] == 0) {
                dfs(child, flag * -1);
            }
        }
    }
}
