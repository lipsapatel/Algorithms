import java.util.Arrays;

/**
 * Create queue using an array in a circular fashion.
 *
 * A maximum size N is specified.
 * The queue consists of N - 1 elements and two integer variables:
 *
 * 1) Front - index of the front element
 * 2) Rear - index of the element after the rear one.
 *
 * Circular because you keep inserting elements in queue and then you remove and then you
 * insert again. so you wrap around to utilize the space.
 *
 * When front == rear, queue is empty
 * We will add only N - 1 elements and declare queue to be full
 *
 * mod N for wrap around.
 *
 * Time complexity: O(1)
 */
public class QueueUsingCircularArray {

    private static class Queue {

        //Default capacity
        private static final int CAPACITY = 10;
        private int n;

        //Front and rear
        private int front = 0;
        private int rear = 0;
        private int[] queue;

        public Queue() {
            //Initialize queue with the default capacity
            this(CAPACITY);
        }

        public Queue(int capacity) {

            //Initialize queue with the given capacity
            n = capacity;
            queue = new int[n];
        }

        public int size() {
            return (n - front + rear) % n;
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public int peek() {

            if (isEmpty()) {

                System.out.println("Queue is empty");
                return 0;
            } else {
                return queue[front];
            }
        }

        public boolean enqueue(int x) {

            if (size() == n - 1) {

                System.out.println("Queue Overflow");
                return false;
            } else {

                queue[rear] = x;
                rear = (rear + 1) % n;
                return true;
            }
        }

        public int dequeue() {

            if (isEmpty()) {

                System.out.println("Queue Underflow");
                return 0;
            } else {

                int x = queue[front];
                front = (front + 1) % n;
                return x;
            }
        }

        public void printQueue() {
            System.out.println(Arrays.toString(queue));
        }
    }


    public static void main(String[] args) {

        Queue queue = new Queue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);
        queue.enqueue(70);
        queue.enqueue(80);
        queue.enqueue(90);
        queue.enqueue(100);
        queue.dequeue();
        queue.enqueue(100);
        queue.printQueue();
    }
}
