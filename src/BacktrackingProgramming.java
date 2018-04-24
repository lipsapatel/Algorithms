/**
 * Recursion is the key in backtracking programming.
 * As the name suggests we backtrack to find the solution.
 *
 * We start with one possible move out of many moves available.
 * And try to solve the problem.
 *
 * If we are able to solve the problem with the selected move then we will
 * print the solution.
 *
 * Else we will backtrack and select some other move and try to solve it.
 *
 * If none of the moves work out then we will claim there is no solution for the problem.
 *
 * Pick a starting point
 * while(problem is not solved) {
 *     For each path from starting point
 *          Check if selected path is safe, if yes select it
 *          and make recursive call to the rest of the problem
 *
 *          If recursive calls returns true then return true
 *          else undo the current move and return false
 *     End for
 *
 *     If none of the move works out, return false, No Solution.
 * }
 */
public class BacktrackingProgramming {
}
