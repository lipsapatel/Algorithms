/**
 * What is Threaded Binary Tree?
 *
 * A binary tree is threaded by making all left child pointers to point to inorder predecessor and all
 * right child pointers to point to inorder successor.
 *
 * 1) We have the pointers which references the next node in inorder traversal called threads.
 * 2) We need to know if the pointer is actual link or thread, so we can keep boolean for each pointer.
 *
 * Why do we need threaded Binary tree?
 *
 * 1) Binary tree has lot of wasted space. The leaf nodes each have 2 null pointers. We can use these
 * pointers to help us in inorder traversals.
 * 2) Threaded Binary Tree makes tree traversal faster since we don't need stack or recursion for traversal.
 *
 * Two Types of Threaded Binary Tree:
 *
 * 1) Single Threaded:
 * Each node is threaded towards inorder successor or predecessor.
 *
 * 2) Double Threaded:
 * Each node is threaded towards inorder successor and predecessor.
 *
 * resources/ThreadedBinaryTree.png
 */
public class ThreadedBinaryTree {
}
