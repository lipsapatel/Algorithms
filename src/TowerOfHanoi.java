/**
 * The Tower of Hanoi is a mathematical game or puzzle. It consists of 3 rods and a number of disks of different
 * sizes which can slide on to any rod.
 *
 * The object of the puzzle is to move entire stack to another rod by obeying these simple rules:
 *
 * 1) Only one disk is allowed to move at a time
 * 2) Bigger disk cannot be placed on the top of smaller disk,
 *
 * resources/TowerOfHanoi.png
 *
 * 1) Recursively move n - 1 disks from source to auxillary peg
 * 2) Move the last disk from source to destination
 * 3) Recursively move n - 1 disks from auxillary to destination peg.
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

    public static void main(String[] args) {

        int n = 4;
        towerOfHanoi(n, "S", "A", "D");
    }
}
