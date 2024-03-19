package data_structure.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10845 {
    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Queue queue = new Queue();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push":
                    queue.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    System.out.println(queue.pop());
                    break;
                case "size":
                    System.out.println(queue.getSize());
                    break;
                case "empty":
                    if (!queue.isEmpty()) System.out.println(0);
                    else System.out.println(1);
                    break;
                case "front":
                    System.out.println(queue.front());
                    break;
                case "back":
                    System.out.println(queue.rear());
                    break;
            }
        }
    }

    /*
    push X  : 정수 X를 큐에 넣는 연산이다.
    pop     : 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
    size    : 큐에 들어있는 정수의 개수를 출력한다.
    empty   : 큐가 비어있으면 1, 아니면 0을 출력한다.
    front   : 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
    back    : 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
    */

    private static class Queue {
        private QueueNode front, rear;

        public Queue() {
            front = rear = null;
        }

        private int size = 0;

        public void push(int num) {
            size++;
            QueueNode queueNode = new QueueNode(num);
            if (isEmpty()) front = rear = queueNode;
            else {
                rear.setNextNode(queueNode);
                rear = queueNode;
            }
        }

        public int pop() {
            if (isEmpty()) return -1;
            else {
                size--;
                QueueNode popNode = front;

                if (size == 0) front = rear = null;
                else front = front.getNextNode();

                return popNode.getValue();
            }
        }

        public int getSize() {
            return size;
        }

        public int front() {
            if (front == null) return -1;
            else return front.getValue();
        }

        public int rear() {
            if (rear == null) return -1;
            else return rear.getValue();
        }

        public boolean isEmpty() {
            return front == null && rear == null;
        }
    }

    private static class QueueNode {
        int value; //값을 넣음
        QueueNode queueNode; //다음 노드를 가리킴
        public QueueNode(int value) {
            this.value = value;
            queueNode = null;
        }
        public int getValue() {
            return value;
        }
        public QueueNode getNextNode() {
            return queueNode;
        }
        public void setNextNode(QueueNode queueNode) {
            this.queueNode = queueNode;
        }
    }
}
