import java.util.Arrays;

/**
 * Two Operations
 * 1) insertAtRank(int rank, int element
 * 2) removeAtRank(int rank)
 *
 * Shift the array elements when doing insert and remove
 */
public class ArrayBasedVectorImplementation {

    private static class Vector {

        private static final int CAPACITY = 100;
        private int n;
        private int[] vector;
        private int size = 0; //Size of total elements in array

        Vector() {
            //Initialize vector with default capacity
            this(CAPACITY);
        }

        Vector(int capacity) {

            n = capacity;
            vector = new int[n];
        }

        void insertAtRank(int rank, int element) {

            //Shift all elements from rank r to size - 1 to right
            for (int i = size - 1; i >= rank; i--) {

                vector[i + 1] = vector[i];
            }

            //Insert element at rank
            vector[rank] = element;
            size = size + 1;
        }

        void removeAtRank(int rank) {

            //After removing shift left
            int element = vector[rank];

            for (int i = rank; i <= size - 2; i++) {
                vector[i] = vector[i + 1];
            }

            size = size - 1;
        }

        void printVector() {
            for (int i = 0; i < size; i++) {
                System.out.println(vector[i]);
            }
        }
    }

    public static void main(String[] args) {

        Vector vector = new Vector();

        vector.insertAtRank(0, 10);
        vector.insertAtRank(1, 20);
        vector.insertAtRank(2, 30);
        vector.insertAtRank(1, 100);
        vector.removeAtRank(1);
        vector.printVector();
    }
}
