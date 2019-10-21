/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

 Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

 You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

 Example:

 Given n = 5, and version = 4 is the first bad version.

 call isBadVersion(3) -> false
 call isBadVersion(5) -> true
 call isBadVersion(4) -> true

 Then 4 is the first bad version.

**********************************************************************************************************************
 *
 * This is a very simple problem. There is a subtle trap that you may fall into if you are not careful. Other than that, it is a direct application of a very famous algorithm.
 Solution
 Approach #1 (Linear Scan) [Time Limit Exceeded]

 The straight forward way is to brute force it by doing a linear scan.

 Complexity analysis

 Time complexity : O(n)O(n)O(n). Assume that isBadVersion(version)isBadVersion(version)isBadVersion(version) takes constant time to check if a version is bad. It takes at most n−1n - 1n−1 checks, therefore the overall time complexity is O(n)O(n)O(n).

 Space complexity : O(1)O(1)O(1).

 Approach #2 (Binary Search) [Accepted]

 It is not difficult to see that this could be solved using a classic algorithm - Binary search. Let us see how the search space could be halved each time below.

 Scenario #1: isBadVersion(mid) => false

 1 2 3 4 5 6 7 8 9
 G G G G G G B B B       G = Good, B = Bad
 |       |       |
 left    mid    right

 Let us look at the first scenario above where isBadVersion(mid)⇒falseisBadVersion(mid) \Rightarrow falseisBadVersion(mid)⇒false. We know that all versions preceding and including midmidmid are all good. So we set left=mid+1left = mid + 1left=mid+1 to indicate that the new search space is the interval [mid+1,right][mid + 1, right][mid+1,right] (inclusive).

 Scenario #2: isBadVersion(mid) => true

 1 2 3 4 5 6 7 8 9
 G G G B B B B B B       G = Good, B = Bad
 |       |       |
 left    mid    right

 The only scenario left is where isBadVersion(mid)⇒trueisBadVersion(mid) \Rightarrow trueisBadVersion(mid)⇒true. This tells us that midmidmid may or may not be the first bad version, but we can tell for sure that all versions after midmidmid can be discarded. Therefore we set right=midright = midright=mid as the new search space of interval [left,mid][left,mid][left,mid] (inclusive).

 In our case, we indicate leftleftleft and rightrightright as the boundary of our search space (both inclusive). This is why we initialize left=1left = 1left=1 and right=nright = n right=n. How about the terminating condition? We could guess that leftleftleft and rightrightright eventually both meet and it must be the first bad version, but how could you tell for sure?

 The formal way is to prove by induction, which you can read up yourself if you are interested. Here is a helpful tip to quickly prove the correctness of your binary search algorithm during an interview. We just need to test an input of size 2. Check if it reduces the search space to a single element (which must be the answer) for both of the scenarios above. If not, your algorithm will never terminate.

 If you are setting mid=left+right2mid = \frac{left + right}{2}mid=2left+right​, you have to be very careful. Unless you are using a language that does not overflow such as Python, left+rightleft + rightleft+right could overflow. One way to fix this is to use left+right−left2left + \frac{right - left}{2}left+2right−left​ instead.

 If you fall into this subtle overflow bug, you are not alone. Even Jon Bentley's own implementation of binary search had this overflow bug and remained undetected for over twenty years.

 Complexity analysis

 Time complexity : O(logn). The search space is halved each time, so the time complexity is O(logn)

 Space complexity : O(1)

 */
public class FirstBadVersion {

    private static boolean isBadVersion(int version) {
        if(version >= 4) {
            return true;
        }
        return false;
    }

    private static int firstBadVersion(int n) {

        int left = 1;
        int right = n;

        while (left < right) {

            int mid = left + (right - left)/2;

            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println("The first bad version is: " + firstBadVersion(9));
    }
}
