package implementation.gold._5;

import java.io.*;
import java.util.Arrays;

public class BOJ_5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] commands = br.readLine().split("");
            int arrLength = Integer.parseInt(br.readLine());

            Deque deque = new Deque();
            String arrString = br.readLine();

            String content = arrString.replaceAll("[\\[\\]]", "");
            String[] split = content.split(",");

            for (int j = 0; j < arrLength; j++)
                deque.offer(Integer.parseInt(split[j]));

            sb.append(function(commands, deque, arrLength)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static String function(String[] commands, Deque deque, int arrLength) {
        Deque temp = new Deque();

        for (String command : commands) {
            if (command.equals("R")) {
                for (int i = 0; i < arrLength; i++)
                    temp.offer(deque.poll());
            } else {
                int num = temp.pollFirst();
                if (num == -1) return "error";
            }
        }

        return Arrays.toString(temp.array());
    }

    private static class Deque {
        private static final int DEFAULT_CAPACITY = 1000;
        private int[] arr;
        private int size;
        private int front;
        private int rear;

        private Deque() {
            this.arr = new int[DEFAULT_CAPACITY];
            this.size = 0;
            this.front = 0;
            this.rear = 0;
        }

        private void resize(int newCapacity) {
            int arrCapacity = arr.length;
            int[] newArr = new int[newCapacity];

            for (int i = 0, j = front + 1; i < size; i++, j++) {
                //원형 큐
                newArr[i] = arr[j % arrCapacity];
            }

            this.arr = newArr;
            front = 0;
            rear = size;
        }

        private boolean offer(int item) {
            return offerLast(item);
        }

        private boolean offerLast(int item) {
            //용적이 찬 경우
            if ((rear + 1) % arr.length == front) {
                resize(arr.length * 2);
            }

            arr[rear] = item;
            rear = (rear + 1) % arr.length;
            size++;

            return true;
        }

        private boolean offerFirst(int item) {
            //용적이 찬 경우
            if (((front - 1) + arr.length) % arr.length == rear) {
                resize(arr.length * 2);
            }

            front = ((front - 1) + arr.length) % arr.length;
            arr[front] = item;
            size++;

            return true;
        }

        private int poll() {
            return pollLast();
        }

        private int pollLast() {
            if (size == 0) return -1;
            int poll = arr[rear];
            arr[rear] = 0;
            rear = ((rear - 1) + arr.length) % arr.length;
            size--;
            return poll;
        }

        private int pollFirst() {
            if (size == 0) return -1;
            int poll = arr[front];
            arr[front] = 0;
            front = (front + 1) % arr.length;
            size--;
            return poll;
        }

        private int[] array() {
            int arrCapacity = arr.length;
            int[] newArr = new int[size];
            for (int i = 0, j = front; i < size; i++, j++) {
                //원형 큐
                newArr[i] = arr[j % arrCapacity];
            }
            return newArr;
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