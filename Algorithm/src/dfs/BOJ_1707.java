package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1707 {

    private static int testCaseCnt;
    private static List<ArrayList<ArrayList<Integer>>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        testCaseCnt = Integer.parseInt(br.readLine());

        list = new ArrayList<>();

        for (int i = 0; i < testCaseCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            list.add(new ArrayList<>());

            int topNum = Integer.parseInt(st.nextToken());

            for (int k = 0; k <= topNum; k++) {
                list.get(i).add(new ArrayList<>());
            }

            int relativeNum = Integer.parseInt(st.nextToken());

            for (int j = 0; j < relativeNum; j++) {
                String[] split = br.readLine().split(" ");

                int a = Integer.parseInt(split[0]);
                int b = Integer.parseInt(split[1]);

                System.out.println("a : " + a + " b: " + b);

                list.get(i).get(a).add(b);
                list.get(i).get(b).add(a);
            }
        }

        System.out.println(list.get(0));
        System.out.println(list.get(1));

        dfs(list.get(0), 0);
    }

    public static void dfs(ArrayList<ArrayList<Integer>> arrayLists, int d) {
        if (arrayLists.size() == d) {

            return;
        }
    }
}
