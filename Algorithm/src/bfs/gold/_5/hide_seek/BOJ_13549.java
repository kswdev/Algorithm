package bfs.gold._5.hide_seek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549 {

    private static int X;
    private static int K;
    private static int MIN = Integer.MAX_VALUE;
    private static Queue<position> queue;
    private static boolean[] visited;
    private static int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        visited = new boolean[MAX + 1];

        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(X, 0);
        System.out.println(MIN);
    }

    private static void bfs(int s, int t) {
        queue = new LinkedList<>();

        queue.offer(new position(s, t));

        while (!queue.isEmpty()) {
            int distant = queue.peek().distant;
            int time    = queue.peek().time;

            queue.poll();

            visited[distant] = true;

            if (time >= MIN) continue;
            if (distant == K) MIN = time;

            if (MAX >= distant*2 && !visited[distant*2]) queue.offer(new position(distant*2, time));
            if (MAX >= distant+1 && !visited[distant+1]) queue.offer(new position(distant+1, time+1));
            if (distant > 0 && !visited[distant-1]) queue.offer(new position(distant-1, time+1));
        }
    }

    private static class position {
        int distant;
        int time;

        public position(int distant, int time) {
            this.distant = distant;
            this.time = time;
        }
    }
}