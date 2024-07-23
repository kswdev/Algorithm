package bfs.hide_and_seak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12851 {

    private static int N, K, C, T;
    private static Queue<int[]> queue;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100000+1];

        find();
        System.out.println(C);
        System.out.println(T);
    }

    private static void find() {
        queue = new LinkedList<>();
        queue.add(new int[] {N, 0});
        int max = 100000;
        while (!queue.isEmpty()) {

            int[] arr = queue.poll();
            int current = arr[0];
            int count   = arr[1];

            visited[current] = true;

            if (max < count) return;

            if (K == current) {
                T++;
                C = max = count;
            }

            if (current+1 <= 100000 && !visited[current+1])
                queue.add(new int[] {current+1, count+1});

            if (current-1 >= 0      && !visited[current-1])
                queue.add(new int[] {current-1, count+1});

            if (current*2 <= 100000 && !visited[current*2])
                queue.add(new int[] {current*2, count+1});
        }
    }
}