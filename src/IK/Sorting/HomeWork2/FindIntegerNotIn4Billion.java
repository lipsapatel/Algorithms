package IK.Sorting.HomeWork2;

import java.util.ArrayList;
import java.util.List;

/**
 * (This is a popular interview problem, from Programming Pearls)

 Given an input file with four billion integers, provide an algorithm to generate an integer which is not contained in the file. Assume you have 1 GiB memory.
 Follow up with what you would do if you have only 10 MiB of memory.

 Four Billion
 Given four billion of 32-bit integers, return any one that’s not among them. Assume you have 1 GiB (1024^3 bytes) of memory.

 Follow up: what if you only have 10 MiB of memory?

 Example One
 Input: [0, 1, 2, 3]
 Output: 4

 Any number in the [4 .. 2^32) range is a correct answer.

 Example Two
 Input: [4294967295, 399999999, 0]
 Output: 1

 Here again 1 is just one of many correct answers.

 Notes
 The function has one argument, an array of 32-bit long unsigned integers. Return a number that’s not among them.

 Constraints:

 1 <= length of input array <= 200000
 0 <= element of input array < 2^32

 Even though there won’t actually be a test with four billion numbers, do design and write a solution for four billion.

 Custom Input
 Input Format:
 First line contains the length of input, n.
 Next n lines each contain a number.
 Example:
 4
 0
 1
 2
 3

 Output Format:
 One line with a number on it. Example:
 4
 **********************************************************************************************************************************************************
 *
 * We provided a solution that uses up to 1 GiB of memory and another that uses up to 10 MiB.
 *
 * 1) one_gib_solution.java
 * Four billion is almost 2^32. With each number occupying 4 bytes, the total is almost 4*2^32 = 4*4*2^30 = 16GiB, that’s more than 1 GiB that we are allowed to use.
 * This means we couldn’t just throw all the numbers into a set (e.g. implemented using a hashtable for constant time add/lookup operations)
 * and then find the smallest absent number by iterating from zero up.
 *
 * The values in the input are in the [0 .. 2^32) range. Can we allocate 2^32 bits and implement a set data structure by using one bit for each possible value?
 * 2^32 bits = (2^32)/8 bytes = 2^(32-3) bytes = 2^29 bytes = 1/2 GiB. So yes, we can do that.
 *
 * one_gib_solution.java allocates an array of bytes and establishes the following mapping between the numbers in the input and the bits in the array:
 *
 *     0-th bit in the array (least significant bit of the 0-th byte) corresponds to number 0,
 *     8-th bit in the array (least significant bit of the 1-st byte) corresponds to number 8, and so on.
 *
 * Initially all bits in the array are initialized to zero. We read all the numbers from the input and set corresponding bits in the array to 1.
 * Then iterate from zero up until we find an unset bit. An unset bit corresponds to a specific number that didn’t occur in the input;
 * such a number is a correct answer and we can return it. An unset bit is guaranteed to be found because four billion is a smaller number than 2^32.
 * Worst case input has four billion unique numbers; even then some numbers between 0 and 2^32 will necessarily be missing.
 *
 * For example, if the input is [0, 2, 3], the 0-th byte of the bytes array will hold binary 00001101 after processing the input;
 * all other bytes will hold zeros. Now looking for an unset bit we first try 0—to find that 0-th bit is set which means 0 occurred in the input
 * and we have to keep looking. We then try 1 and find that 1-th bit is unset, so we return 1.
 *
 * Time Complexity:
 * O(n) where n is the length of the input array.
 * Setting the bit corresponding to an input number takes constant time; and we do that n times.
 * Then we iterate to find an unset bit which takes another O(n).
 *
 * Auxiliary Space Used:
 * O(1), specifically 1/2 GiB, as discussed above.
 *
 * Space Complexity:
 * O(n) because of the size of input.
 *
 * The 1/2 GiB of the auxiliary space used, while significant, is constant. Input size dominates asymptotically.
 *
 * 4 billion int 32 numbers
 * = 4 * 2^30
 * = 2^2 * 2^30
 * = 2^32
 *
 * 1 number = 4 bytes
 * 2^32 numbers = ?
 *
 * = 2^32 * 2^2
 * = 2^34 bytes
 * = 2^4 * 2^30 bytes
 * = 16 Giga bytes
 * = 16 GB
 *
 * Represent each number with one bit
 * 4 billion int-32 numbers
 * = 2^2 * 2^30
 * = 2^32 numbers
 * = 2^32 bits for representation
 *
 * 8 bits = 1 byte
 * 2^32 bits = ?
 *
 * 2^32/8 bytes
 * = 2^32/2^3
 * = 2^32 - 3
 * =2^29
 * = 2^9 * 2^20 bytes
 * = 2^9 Mega bytes
 * = 512 MB
 * = 1/2 GB
 *
 * Approach
 *
 * 1) Iterate the input array
 * 2) Create bytes array of size 2^29
 * 3) ByteIndex = val/8
 * 4) BitIndex = val % 8
 * 5) bytes[byteIndex] = 1<<bitIndex
 * 6) Scan bytes array and check for unset bit
 * 7) bytes[byteIndex] & 1 << bitIndex == 0
 * 8) The bit is unset so return byteIndex*8l + bitIndex number
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) since 2^29 is constant space.
 * **********************************************************************************************************
 *
 * Find missing number in 4 billion Int32 numbers given 10 MB memory
 *
 * 10 MiB doesn’t have enough bits for 1-to-1 mapping to integers in [0 .. 2^32) range. But what if the input values were bound by a narrower range? Can we break the problem into simpler parts of subproblems,
 * possibly doing more than one pass over the input?
 *
 * What if we knew a narrower range (narrow enough for 10 MiB to have enough bits for a 1-to-1 mapping) that isn’t “completely covered” by input values? We could then do what one_gib_solution.java does for
 * that narrower range (instead of the whole [0 .. 2^32) range) and find a correct answer.
 *
 * How do we find such a range? We could break the whole [0 .. 2^32) range into a set of non-overlapping subranges and—at first—simply count the input values that fall into each subrange/bucket.
 * Some buckets may have zero input values fall into them, other bucket/ranges may be completely “covered” by the input values. An input can be such that all four billion values fall into one small subrange,
 * leaving other buckets/subranges “empty” (in such case we could pick any value from any “empty” subrange and return it as a correct result).
 *
 * ten_mib_solution.java breaks the [0 .. 2^32) into 2^16 subranges/buckets, each subrange then has 2^16 distinct values:
 *
 *     0-th subrange: [0 .. 2^16),
 *     1-st subrange: [2^16 .. 2^17),
 *     ...,
 *     the last, 2^16-1-th subrange: [2^32-2^16 .. 2^32).
 *
 * During the first pass over the input we count how many input values fall into each of the buckets. Then we find a bucket/subrange that is guaranteed to include at least
 * one value absent in input (if the total number of input values falling into this bucket is less than 2^16, it means that some values in the subrange are guaranteed to not be “covered”).
 * And then we do for that subrange what one_gib_solution.java does for the whole [0 .. 2^32) range. In this case there is no need to mess with bits, we use a boolean array of size 2^16
 * and easily fit in the allowed 10 MiB.
 *
 * For example, if the input is [0, 2, 3], all these values will fall into the 0-th bucket. 0-th bucket will have the count of 3 and all other buckets will have the count of 0.
 *
 * Looking for a good bucket starting from the 0-th one, we will find that 3 is less than 2^16 and therefore 0-th bucket is guaranteed to contain at least one value that’s missing in input.
 * We now will read the input for the second time, this time only paying attention to values that fall into the 0-th bucket/subrange.
 * By the time that’s done, the boolean array presentInBucket will look like this: [true, false, true, true, false, false, …, false] with truthy values at indices 0, 2 and 3.
 * Iterating from index 0 up, we will find the first falsy value at index 1. We return 1 because we know that 1 is missing in the input.
 *
 * Time Complexity:
 * O(n) where n is the length of the input array.
 *
 * We read input two times and do a constant amount of work per input number.
 *
 * Auxiliary Space Used:
 * O(1). Specifically the largest, dominating data structure takes 2^16 * 8 bytes = 2^19 bytes = 1/2 MiB, as discussed in ten_mib_solution.java comments.
 *
 * Space Complexity:
 * O(n) because of the size of input.
 *
 * Approach
 *
 * 1) Since we are given 10 MB memory we can divide input into 2^16 parts
 * 2) 2^32/2^16 = 2^31 - 16 = 2^16
 * 3) Each bucket with have 2^16 numbers
 * 4) We will have 2^16 such buckets
 * 5) Each bucket will store the count of numbers
 * 6) So we will take long array of size 2^16
 * 7) Space required for that is 2^16 * 8 = 2^19 = 1/2 MB
 * 8) Iterate input array and store the count in each bucket
 * 9) Iterate each bucket and find bucket having count less than 2^16
 * 10) Take boolean array of size 2^16
 * 11) Iterate input array and set boolean value true or false if that value falls in that bucket
 * 12) Iterate boolean array and find entry with false value
 * 13) Return that number
 *
 * To divide value by 2^16 = val >> 16
 * To multiply value by 2^16 = val << 16
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) 1/2 MB is constant space.
 *
 * Always write bit shift operation in brackets
 */
public class FindIntegerNotIn4Billion {

    private static long findNumberNotContained1GB(List<Long> a) {
        int size = (int)((Math.pow(2,32))/8);

        byte[] bytes = new byte[size];

        //Iterate array and set the bit
        for(int i = 0; i < a.size(); i++) {
            int byteIndex = (int) (a.get(i)/8);
            int bitIndex = (int)(a.get(i) % 8);

            bytes[byteIndex] = (byte) (bytes[byteIndex] | 1 << bitIndex);
        }

        //check for unset bit
        for(int byteIdx = 0; byteIdx < size; byteIdx++) {

            //Iterate 0-7 bit
            for(int bitIdx = 0; bitIdx < 8; bitIdx++) {
                if((bytes[byteIdx] & 1<<bitIdx) == 0) {

                    //This bit is unset return the number
                    return byteIdx * 8 + bitIdx;
                }
            }
        }

        throw new IllegalStateException("Must have found an unset bit and returned from the loop");
    }

    private static final int TWO_POWER_SIXTEEN = (int) (Math.pow(2,16));

    private static long findNumberNoContained10MB(List<Long> a) {

        long[] countBucket = new long[TWO_POWER_SIXTEEN];

        //Update count
        for(int i = 0; i < a.size(); i++) {
            int bucketIdx = (int)(a.get(i) >> 16);

            countBucket[bucketIdx]++;
        }

        //Iterate count bucket and find bucket with count less than 2^16
        for(int bucketIdx = 0; bucketIdx < TWO_POWER_SIXTEEN; bucketIdx++) {

            if(countBucket[bucketIdx] >= TWO_POWER_SIXTEEN) {
                continue;
            }

            //Count is less than 2^16
            boolean[] present = new boolean[TWO_POWER_SIXTEEN];

            for(int i = 0; i < a.size(); i++) {
                int bucketIndex = (int)(a.get(i)>>16);

                if(bucketIndex == bucketIdx) {
                    int presentIdx = (int)(a.get(i) % TWO_POWER_SIXTEEN);

                    present[presentIdx] = true;
                }
            }

            //Iterate boolean array and find missing number
            for(int i = 0; i < TWO_POWER_SIXTEEN; i++) {
                if(!present[i]) {
                    return (bucketIdx<<16) + i;
                }
            }
            throw new IllegalStateException("Missing number not found");
        }
        throw new IllegalStateException("Missing number not found");
    }

    public static void main(String[] args) {
        List<Long> a = new ArrayList<>();

        //a.add(0l);
        //a.add(1l);
        //a.add(3l);
        a.add(3l);
        a.add(4294967295l);
        a.add(399999999l);
        a.add(0l);

        System.out.println(findNumberNotContained1GB(a));

        System.out.println("Find missing number using 10 MB space " + findNumberNoContained10MB(a));
    }
}
