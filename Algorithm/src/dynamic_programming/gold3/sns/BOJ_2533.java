package dynamic_programming.gold3.sns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2533 {

    private static int N;
    private static List<Friend> friends = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            friends.add(new Friend(i));
        }

        Friend firstFriend = friends.get(1);
        firstFriend.setDepth(1);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child  = Integer.parseInt(st.nextToken());

        }


    }

    private static class Friend {
        private int depth;
        private int num;
        private int child;

        public Friend(int num) {
            this.num = num;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public void setChild(int child) {
            this.child = child;
        }

        public int getNum() {
            return num;
        }
    }
}
/**
 * 사고
 * 뎁스가 가장 깊은 노드부터 뎁스 크기를 홀/짝으로 구분
 * 홀수 뎁스들의 갯수와 짝수 뎁스들의 갯수를 비교해서 작은 뎁스의 갯수를 구함
 */