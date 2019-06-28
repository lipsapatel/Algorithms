import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * (This is a popular interview problem, from Programming Pearls)


 Given an input file with four billion integers, provide an algorithm to generate an integer which is not contained in the file. Assume you have 1 GiB memory.
 Follow up with what you would do if you have only 10 MiB of memory.


 Note:


 This problem is under development according to IK standards. If you'd like to contribute, then please feel free to email soham@interviewkickstart.com


 Till we finish developing this problem, you can look at:

 http://stackoverflow.com/questions/7153659/find-an-integer-not-among-four-billion-given-ones?rq=1

4 billion numbers and 1GB memory

 2^32 distinct numbers = 4 * 2^30
                       = 4 * 10 ^ 9
                       = 4 billion distinct numbers

 1 GB = 1 * 10 ^ 9 * 8 = 8 billion bits memory

 So we can map 4 billion distinct numbers = to our memory which will use 500 MB or 0.5 GB

 If we are given 10 MB of memory

 Here it’s possible to find a missing integer with just two passes of the data set. We can divide up the integers into blocks of some size (we’ll discuss how to decide on a size later). Let’s just assume that we divide up the integers into blocks of 1000. So, block 0 represents the numbers 0 through 999, block 1 represents blocks 1000 - 1999, etc. Since the range of ints is finite, we know that the number of blocks needed is finite.
 In the first pass, we count how many ints are in each block. That is, if we see 552, we know that that is in block 0, we increment counter[0]. If we see 1425, we know that that is in block 1, so we increment counter[1].

 At the end of the first pass, we’ll be able to quickly spot a block that is missing a number. If our block size is 1000, then any block which has fewer than 1000 numbers must be missing a number. Pick any one of those blocks.
 In the second pass, we’ll actually look for which number is missing. We can do this by creating a simple bit vector of size 1000. We iterate through the file, and for each number that should be in our block, we set the appropriate bit in the bit vector. By the end, we’ll know which number (or numbers) is missing.

 10 MB = 10 * 10 ^ 6 * 8 = 80 million bits

 we have 4 billion distinct numbers if we take one block of size 2 ^ 20 then we need 2 ^ 32/2 ^ 20 = 2 ^ 12 blocks needed

 Each block will have counter which is 4 Bytes. So no of block * counter = 2 ^12 * 4 * 8 bits  which will fit in 10 MB memory.
 And after that you need 2 ^ 20 bits for finding missing number in one block which will also fit in 10 MB memory
 */
public class FindIntegerNotIn4Billion {

    public static int findNumberNotContained(String filename) throws FileNotFoundException {

        //One byte can represent 8 bits
        //We can divide 4 billion/8 to get the size of byte array
        //0xFFFFFFF = 4 billion
        byte[] bitfield = new byte[0xFFFFFFF/8];

        Scanner in = new Scanner(new FileReader(filename));

        while(in.hasNextInt()) {

            int n = in.nextInt();

            //0-7 numbers will be in byte[0]
            //8-15 numbers will be in byte[1]
            //number 10 will be in byte[1] at 3rd position from right
            //Left shift and do OR
            bitfield[n / 8] |= 1 << (n % 8);
        }

        //Check if particular bit in byte array is 0 if it is then return that
        for (int i = 0; i < bitfield.length; i++) {

            for (int j = 0; j < 8; j++) {

                if ((bitfield[i] & (1 << j)) == 0) { //Do end so that we will know the set bit
                    return i*8 + j;
                }
            }
        }
        return -1; //not found
    }

    public static int findNumberNotContainedUsing10MB(String filename) throws FileNotFoundException {

        int blockSize = 1048576; // 2 ^ 20 bits
        int noOfBlocks = 4096; // 2 ^32/2 ^ 20 = 2 ^ 12
        byte[] bitfield = new byte[blockSize/8];
        int[] blocks = new int[noOfBlocks];

        Scanner in = new Scanner(new FileReader(filename));

        while(in.hasNextInt()) {
            int n = in.nextInt();
            blocks[n / blockSize]++;
        }

        int startNumber = 0;

        for (int i = 0; i < noOfBlocks; i++) {
            if (blocks[i] < blockSize) {
                startNumber = i * blockSize; //Found the block which has missing number
                break;
            }
        }

        in = new Scanner(new FileReader(filename));

        while(in.hasNextInt()) {
            int n = in.nextInt();

            if (n >= startNumber && n < startNumber + blockSize) {

                bitfield[(n - startNumber)/8] |= (1 << (n - startNumber) % 8);
            }
        }

        for (int i = 0; i < bitfield.length; i++) {
            for (int j = 0; j < 8; j++) {

                if ((bitfield[i] & (1 << j)) == 0) {
                    return i * 8 + j;
                }
            }
        }

        return -1; //not found
    }
}
