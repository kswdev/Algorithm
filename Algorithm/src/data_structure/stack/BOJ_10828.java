package data_structure.stack;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10828 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        StringTokenizer st;
        String req;

        Stack stack = new Stack();
        stack.arr = new int[num];

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            req = st.nextToken();

            switch (req) {
                case "push":
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    System.out.println(stack.pop());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    System.out.println(stack.empty());
                    break;
                case "top":
                    System.out.println(stack.top());
                    break;
            }
        }
    }

    public static class Stack {
        private int[] arr;
        private int size = 0;

        public void push(int number) {
            arr[size++] = number;
        }

        public int pop() {
            if(size == 0) return -1;
            return arr[--size];
        }

        public int size() {
            return size;
        }

        public int empty() {
            if (size == 0) return 1;
            else return 0;
        }

        public int top() {
            if(size < 1) return -1;
            return arr[size-1];
        }
    }
}
