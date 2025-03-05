package implementation.gold._5;

import java.io.*;

public class BOJ_5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] commands = br.readLine().split("");
            int arrLength = Integer.parseInt(br.readLine());

            Deque deque = new Deque(arrLength);
            String arrString = br.readLine();

            String content = arrString.replaceAll("[\\[\\]]", "");
            String[] split = content.split(",");

            for (int j = 0; j < arrLength; j++)
                deque.offerLast(Integer.parseInt(split[j]));

            sb.append(function(commands, deque)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static String function(String[] commands, Deque deque) {

        for (String command : commands) {
            if (command.equals("R")) {
                deque.trigger();
            } else {
                int num = deque.poll();
                if (num == -1) return "error";
            }
        }

        return deque.toString();
    }

    private static class Deque {
        private int[] arr;
        private int size;
        private int front;
        private int rear;
        private boolean option = true;

        private Deque(int length) {
            this.arr = new int[length];
            this.size = 0;
            this.front = 0;
            this.rear = 0;
        }

        private void offer(int item) {
            if (option) offerLast(item);
            else offerFirst(item);
        }

        private void offerLast(int item) {
            arr[rear] = item;
            rear = (rear + 1) % arr.length;
            size++;
        }

        private void offerFirst(int item) {
            front = (front - 1 + arr.length) % arr.length;
            arr[front] = item;
            size++;
        }

        private int poll() {
            if (option) return pollFirst();
            else return pollLast();
        }

        private int pollFirst() {
            if (size == 0) return -1;
            int poll = arr[front];
            arr[front] = 0;
            front = (front + 1) % arr.length;
            size--;

            return poll;
        }

        private int pollLast() {
            if (size == 0) return -1;
            rear = (rear - 1 + arr.length) % arr.length;
            int poll = arr[rear];
            size--;

            return poll;
        }

        @Override
        public String toString() {
            if (!option) reverse();

            StringBuilder sb = new StringBuilder();

            int arrCapacity = arr.length;

            sb.append("[");
            for (int i = 0, j = front; i < size; i++, j++) {
                sb.append(arr[j % arrCapacity]);
                if (i != size - 1) sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }

        private void reverse() {
            int temp;
            for (int i = front, j = rear - 1; i < front + (size / 2); i++, j--) {
                temp = arr[i % arr.length];
                arr[i % arr.length] = arr[(j + arr.length) % arr.length];
                arr[(j + arr.length) % arr.length] = temp;
            }
        }

        private void trigger() {
            option = !option;
        }
    }
}

/*
 * 새로운 언어 AC -> 정수 배열에 연산을 하기위한 언어
 * AC 기능 -> R(뒤집기), D(버리기)
 * R -> 배열에 있는 수의 순서를 뒤집는 함수
 * D -> 첫 번째 수를 버리는 함수(배열이 빈 경우 'error' 반환)
 *
 * 배열과 초기값이 수행할 함수가 주어졌을 때, 최종 겨로가를 구하는 프로그램
 */