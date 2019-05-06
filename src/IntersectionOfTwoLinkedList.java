import Node.SinglyLinkedListNode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.

 For example, the following two linked lists:


 begin to intersect at node c1.



 Example 1:


 Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 Output: Reference of the node with value = 8
 Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.


 Example 2:


 Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 Output: Reference of the node with value = 2
 Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.


 Example 3:


 Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 Output: null
 Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 Explanation: The two lists do not intersect, so return null.


 Notes:

 If the two linked lists have no intersection at all, return null.
 The linked lists must retain their original structure after the function returns.
 You may assume there are no cycles anywhere in the entire linked structure.
 Your code should preferably run in O(n) time and use only O(1) memory.

 Approach 1: Brute Force
 For each node ai in list A, traverse the entire list B and check if any node in list B coincides with ai.

 Complexity Analysis

 Time complexity : O(mn)O(mn).

 Space complexity : O(1)O(1).


 Approach 2: Hash Table
 Traverse list A and store the address / reference to each node in a hash set. Then check every node bi in list B: if bi appears in the hash set, then bi is the intersection node.

 Complexity Analysis

 Time complexity : O(m+n)O(m+n).

 Space complexity : O(m)O(m) or O(n)O(n).


 Approach 3: Two Pointers
 Maintain two pointers pApA and pBpB initialized at the head of A and B, respectively. Then let them both traverse through the lists, one node at a time.
 When pApA reaches the end of a list, then redirect it to the head of B (yes, B, that's right.); similarly when pBpB reaches the end of a list, redirect it the head of A.
 If at any point pApA meets pBpB, then pApA/pBpB is the intersection node.
 To see why the above trick would work, consider the following two lists: A = {1,3,5,7,9,11} and B = {2,4,9,11}, which are intersected at node '9'. Since B.length (=4) < A.length (=6), pBpB would reach the end of the merged list first, because pBpB traverses exactly 2 nodes less than pApA does. By redirecting pBpB to head A, and pApA to head B, we now ask pBpB to travel exactly 2 more nodes than pApA would. So in the second iteration, they are guaranteed to reach the intersection node at the same time.
 If two lists have intersection, then their last nodes must be the same one. So when pApA/pBpB reaches the end of a list, record the last element of A/B respectively. If the two last elements are not the same one, then the two lists have no intersections.
 Complexity Analysis

 Time complexity : O(m+n)O(m+n).

 Space complexity : O(1)O(1).



 */
public class IntersectionOfTwoLinkedList {

    private static SinglyLinkedListNode getIntersectionNode(SinglyLinkedListNode headA, SinglyLinkedListNode headB) {

        //Calculate length
        int lenA = 0;
        int lenB = 0;

        SinglyLinkedListNode A = headA;
        SinglyLinkedListNode B = headB;

        while (A != null) {
            lenA++;
            A = A.next;
        }

        while (B != null) {
            lenB++;
            B = B.next;
        }

        //Reset to head
        A = headA;
        B = headB;

        //Position the longer list forward
        if(lenA > lenB) {
            for(int i=0; i<lenA-lenB; i++)
                A = A.next;
        } else {
            for(int i=0; i<lenB-lenA; i++)
                B = B.next;
        }

        while(A != B) {
            A = A.next;
            B = B.next;
        }

        return A;
    }

    private static SinglyLinkedListNode getIntersectionNode1(SinglyLinkedListNode headA, SinglyLinkedListNode headB) {

        SinglyLinkedListNode currentA = headA;
        SinglyLinkedListNode currentB = headB;
        int count = 0;

        if (currentA == null || currentB == null) {
            return null;
        }

        while (currentA != currentB) {
            currentA = currentA.next;
            currentB = currentB.next;

            //Point to headB
            if (currentA == null) {
                currentA = headB;
                count++;
            }

            //Point to headA
            if (currentB == null) {
                currentB = headA;
            }

            //This should happen once
            if (count == 2) {
                return null;
            }
        }
        return currentA;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(8);
        node.next = new SinglyLinkedListNode(4);
        node.next.next = new SinglyLinkedListNode(5);

        SinglyLinkedListNode headA = new SinglyLinkedListNode(4);
        headA.next = new SinglyLinkedListNode(1);
        headA.next.next = node;

        SinglyLinkedListNode headB = new SinglyLinkedListNode(5);
        headB.next = new SinglyLinkedListNode(0);
        headB.next.next = new SinglyLinkedListNode(1);
        headB.next.next.next = node;

        System.out.println("Intersection point using length and placing forward: " + (getIntersectionNode(headA, headB)).data);
        System.out.println("Intersection point by pointing to head: " + (getIntersectionNode1(headA, headB)).data);

    }
}
