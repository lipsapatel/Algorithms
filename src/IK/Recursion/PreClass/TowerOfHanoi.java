package IK.Recursion.PreClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * The Tower of Hanoi is a mathematical game or puzzle. It consists of 3 rods and a number of disks of different
 * sizes which can slide on to any rod.
 *
 * The object of the puzzle is to move entire stack to another rod by obeying these simple rules:
 *
 * 1) Only one disk is allowed to move at a time
 * 2) Bigger disk cannot be placed on the top of smaller disk,
 *
 * resources/IK.Recursion.PreClass.TowerOfHanoi.png
 *
 * 1) Recursively move n - 1 disks from source to auxillary peg
 * 2) Move the last disk from source to destination
 * 3) Recursively move n - 1 disks from auxillary to destination peg.
 *
 * Time Complexity: 2 ^ n because 2 branches/degree and height is n
 * Space Complexity: O(n) call stack
 *
 * For 2 disks, 3 steps required.
 For 3 disks, 7 steps required.
 Similarly, for n disks, steps required will be 2^n-1.

 ********************************************************************************************************
 * 1)	Tower of Hanoi
 Tower Of Hanoi
 Problem Statement:
 Tower of Hanoi is a mathematical puzzle where we have three pegs and n disks. The objective of the puzzle is to move the entire stack to another peg, obeying the following simple rules:
 1.	Only one disk can be moved at a time.
 2.	Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack i.e. a disk can only be moved if it is the uppermost disk on a stack.
 3.	No disk may be placed on top of a smaller disk.
 Here, we are given n denoting the number of disks in the first peg, we need to return all the steps taken to move all disks from the first peg to the third peg.
 There can be multiple possibilities to complete the given objective. Every valid possibility to achieve objective will be a valid answer.
 Input/Output Format For The Function:
 Input Format:
 There is only one argument in the input, an integer named n denoting the number of disks in the first peg.
 Output Format:
 Return a 2d integer array denoting steps taken to move disks from the first peg to the third peg. Each row will have two integers denoting from peg and to peg,
 for example, if the ith row is [2, 3], then it means in this step, we moved top disk on peg 2 to peg 3.
 For input n = 4, the output will be a 2d array result = [ [1, 2], [1, 3], [2, 3], [1, 2], [3, 1], [3, 2], [1, 2], [1, 3], [2, 3], [2, 1], [3, 1], [2, 3], [1, 2], [1, 3], [2, 3] ]
 Input/Output Format For The Custom Input:
 Input Format:
 There should be one line for input, containing a single integer n, denoting the number of disks in the first peg.
 If n = 4 then input should be:
 4
 Output Format:
 There will be a 2d array of integers, where ith row of result 2d array will denote ith step taken to reach the objective. Each row will have two integers denoting from peg and to peg, for example, if the ith row is "2 3" then it means, in this step, we moved top disk on peg 2 to peg 3.
 For input n = 4, the output will be as follows:
 1 2
 1 3
 2 3
 1 2
 3 1
 3 2
 1 2
 1 3
 2 3
 2 1
 3 1
 2 3
 1 2
 1 3
 2 3
 Constraints:
 •	1 <= n <= 20
 Sample Test Case:
 Input:
 n = 4
 Output:
 [ [1, 2], [1, 3], [2, 3], [1, 2], [3, 1], [3, 2], [1, 2], [1, 3], [2, 3], [2, 1], [3, 1], [2, 3], [1, 2], [1, 3], [2, 3] ]
 Explaination:
 Following steps:
 [1, 2] = Shift top disk of the first peg to top of the second peg.
 Picture after this step will be:
 First peg: 2 3 4
 Second peg: 1
 Third peg: Empty
 [1, 3] = Shift top disk of the first peg to top of the third peg.
 Picture after this step will be:
 First peg: 3 4
 Second peg: 1
 Third peg: 2
 Similarly after following remaining steps will find that the final configuration will be
 First peg: Empty
 Second peg: Empty
 Third peg: 1 2 3 4
 which is our objective.

 Time Complexity: O(2 ^ n) because 2 ^ n steps
 Space Complexity: Call Stack space: O(n)
 Auxillary space including output 2D array - O(2 ^ n)
 */
public class TowerOfHanoi {

    private static void towerOfHanoi(int n, String source, String auxillary, String destination) {

        if (n == 1) {
            System.out.println("Move disc " + n + " from " + source + " to " + destination);
        } else {

            //Make recursive call to move n - 1 disks from source to auxillary - Notice the order
            towerOfHanoi(n - 1, source, destination, auxillary);

            //Move the last disk from source to destination
            System.out.println("Move disc " + n + " from " + source + " to " + destination);

            //Make recursive call to move n - 1 discs from auxillary to destination - notice the order
            towerOfHanoi(n - 1, auxillary, source, destination);
        }
    }

    //TC: O(2 ^ n) and SC = O(2 ^ n)
    private static List<List<Integer>> tower_of_hanoi(int n) {

        //For storing the steps
        List<List<Integer>> steps = new ArrayList<>();

        //Peg1 = source
        //peg2 = auxillary
        //peg3 - destination
        towerOfHanoiHelper(n, steps, 1, 2, 3);

        return steps;
    }

    private static void towerOfHanoiHelper(int n, List<List<Integer>> steps, int s, int d, int a) {

        //Base Case
        //When only one disk left
        if (n == 1) {
            //Move from source to destination
            List<Integer> list = new ArrayList<>();
            list.add(s);
            list.add(d);
            steps.add(list);
        } else { //Recursive Case

            //Move n - 1 disks from source to auxillary
            towerOfHanoiHelper(n - 1, steps, s, a, d);

            //Move last disk from source to destination
            List<Integer> list = new ArrayList<>();
            list.add(s);
            list.add(d);
            steps.add(list);

            //Move n - 1 disks from auxillary to destination
            towerOfHanoiHelper(n - 1, steps, a, d, s);
        }
    }

    /**
     * Tower of Hanoi Iterative approach
     * Given n disks, the total number of moves = 2 ^ n - 1
     * If n is even, interchange auxillary and destination
     *
     * for i = 1 to 2 ^ n - 1
     *
     * Do legal moves
     * i % 3 = 1, move between source and destination
     * i % 3 = 2, move between source and auxillary
     * i % 3 = 0, move between auxillary and destination
     *
     * TC = O(2 ^ n)
     * SC = O(2 ^ n) + O(n) = O(2 ^ n)
     */
    public static List<List<Integer>> tower_of_hanoi_iterative(int n) {

        List<List<Integer>> steps = new ArrayList<>();

        int peg1 = 1;
        int peg2 = 2;
        int peg3 = 3;

        //If n is even, swap auxillary and destination
        if (n % 2 == 0) {
            int temp = peg2;
            peg2 = peg3;
            peg3 = temp;
        }

        //Need 3 stacks
        Stack<Integer> src = new Stack<>();
        Stack<Integer> aux = new Stack<>();
        Stack<Integer> dest = new Stack<>();

        //Initially push all disks to src stack
        for (int i = n; i >= 1; i--) {
            src.push(i);
        }

        int totalMoves = (int)(Math.pow(2, n) - 1);

        for (int i = 1; i <= totalMoves; i++) {

            //In first step move disk from source to dest or whatever is legal
            if (i % 3 == 1) {
                moveDisks(src, dest, peg1, peg3, steps);
            }

            //Move disk from source to auxillary in second step or whatever is legal
            else if (i % 3 == 2) {
                moveDisks(src, aux, peg1, peg2, steps);
            }

            //Last steps move from auxillary to dest or whatever is legal
            else if (i % 3 == 0) {
                moveDisks(aux, dest, peg2, peg3, steps);
            }
        }

        return steps;
    }

    //Legal move between 2 pegs
    private static void moveDisks(Stack<Integer> src, Stack<Integer> dest, int srcPeg, int destPeg, List<List<Integer>> steps) {

        //When src peg is empty
        if (src.isEmpty()) {
            src.push(dest.pop());
            addToResult(destPeg, srcPeg, steps);
        }

        //When dest peg is empty
        else if (dest.isEmpty()) {
            dest.push(src.pop());
            addToResult(srcPeg, destPeg, steps);
        }

        //When src > dest
        else if (src.peek() > dest.peek()) {
            src.push(dest.pop());
            addToResult(destPeg, srcPeg, steps);
        }

        //When dest > src
        else {
            dest.push(src.pop());
            addToResult(srcPeg, destPeg, steps);
        }
    }

    //Add to result
    private static void addToResult(int peg1, int peg2, List<List<Integer>> steps) {
        List<Integer> list = new ArrayList<>();
        list.add(peg1);
        list.add(peg2);
        steps.add(list);
    }

    public static void main(String[] args) {

        int n = 4;
        long startTime = System.currentTimeMillis();
        towerOfHanoi(n, "S", "A", "D");
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime)/1000);

        System.out.println("Steps for Tower of Hanoi Recursion: ");
        List<List<Integer>> result = tower_of_hanoi(3);
        for (List<Integer> list: result) {
            System.out.println(Arrays.toString(list.toArray()));
        }

        System.out.println("Steps for Tower of Hanoi: ");
        List<List<Integer>> result1 = tower_of_hanoi_iterative(4);
        for (List<Integer> list: result1) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
}
