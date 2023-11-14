package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * www.acmicpc.net/problem/2644
 */
public class BOJ_2644 {

    private static int N, X, Y, R;
    private static List<Integer>[] lists;
    private static boolean[] checked;
    private static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] inputSplit = br.readLine().split(" ");

        int X = Integer.parseInt(inputSplit[0]);
        int Y = Integer.parseInt(inputSplit[1]);

        int R = Integer.parseInt(br.readLine());
        lists = new ArrayList[N+1];
        checked = new boolean[N+1];

        for(int i = 0; i <= N; i++) {
            lists[i] = new ArrayList<>();
        }

        for(int i = 0; i < R; i++) {
            inputSplit = br.readLine().split(" ");

            int parent = Integer.parseInt(inputSplit[0]);
            int child  = Integer.parseInt(inputSplit[1]);

            lists[parent].add(child);
            lists[child].add(parent);
        }

        dfs(X, Y, 0);

        System.out.println(result);
    }
    public static void dfs(int start, int end, int count) {
        if(start == end) {
            result = count;
            return;
        }

        checked[start] = true;

        for(int i = 0; i < lists[start].size(); i++) {
            if(!checked[lists[start].get(i)]) {
                dfs(lists[start].get(i), end, count+1);
            }
        }
    }
}
