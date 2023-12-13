package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1167 {

    private static int V;
    private static List<ArrayList<Integer>> list;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i < V; i++) {

            list.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());

            int child;

            while ((child = Integer.parseInt(st.nextToken())) != -1) {
                list.get(i).add(child);
            }
        }

        System.out.println(list);
    }
}
