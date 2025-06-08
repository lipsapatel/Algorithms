package IK.Graphs.MockInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients.
 * The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].
 * Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 *
 * You are also given a string array supplies containing all the ingredients that you initially have, and
 * you have an infinite supply of all of them.
 *
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 *
 * Note that two recipes may contain each other in their ingredients.
 *
 *
 *
 * Example 1:
 *
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 * Output: ["bread"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * Example 2:
 *
 * Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * Example 3:
 *
 * Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich","burger"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 *
 *
 * Constraints:
 *
 * n == recipes.length == ingredients.length
 * 1 <= n <= 100
 * 1 <= ingredients[i].length, supplies.length <= 100
 * 1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 * recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
 * All the values of recipes and supplies combined are unique.
 * Each ingredients[i] does not contain any duplicate values.
 *
 * Approach
 * 1) The problem is DFS with topological sort. However, we don't need
 * to return topological order
 * 2) Ingredient needs to be present in supplies or it could be already
 * cooked recipe
 * 3) We need to create graph with recipe and their prereq which is ingredient
 * in this case.
 * 4) We need to maintain recipe status.
 * 5) The recipe can be in 3 states: NEW, COOKING, COOKED
 * 6) All recipe is in NEW state initially and supplies is in COOKED state
 * 7) While DFS is in progress, it's in COOKING state
 * 8) Do DFS and if recipe is not in recipe status and COOKING state then return false
 * 9) If recipe is COOKED, then return true
 * 10) Set recipe state to COOKING and get all neighbors and if canBeCookedDFS
 * returns false then return false
 * 11) After DFS is completed and while returning back, add recipe to cookedRecipe
 * list and add recipe with state COOKED.
 *
 * TC: O(n + m) - n = vertex and m = edges
 * SC: O(n + m) - Graph, for recipeStatus, DFS recursive call stack, cookedRecipe
 *
 */
public class RecipeFromSupplies {

    private static int NEW = 0;
    private static int COOKING = 1;
    private static int COOKED = 2;

    private static HashMap<String, List<String>> graph;

    private static void initializeGraph() {
        graph = new HashMap<>();
    }

    private static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        initializeGraph();

        for(int i = 0; i < recipes.length; i++) {
            graph.put(recipes[i], ingredients.get(i));
        }

        HashMap<String, Integer> recipeStatus = new HashMap<>();

        //All recipes are in NEW state initially
        for(int i = 0; i < recipes.length; i++) {
            recipeStatus.put(recipes[i], NEW);
        }

        //All supplies are in COOKED state initially
        for(int i = 0; i < supplies.length; i++) {
            recipeStatus.put(supplies[i], COOKED);
        }

        //Do DFS for all recipes
        List<String> cookedRecipe = new ArrayList<>();
        for(int i = 0; i < recipes.length; i++) {
            canBeCookedDFS(recipes[i], recipeStatus, cookedRecipe);
        }
        return cookedRecipe;
    }

    private static boolean canBeCookedDFS(String recipe, HashMap<String, Integer> recipeStatus, List<String> cookedRecipe) {
        if(!recipeStatus.containsKey(recipe)) {
            return false;
        }

        if(recipeStatus.get(recipe) == COOKED) {
            return true;
        }

        if(recipeStatus.get(recipe) == COOKING) {
            return false;
        }

        recipeStatus.put(recipe, COOKING);

        for(String ngb: graph.get(recipe)) {
            if(!canBeCookedDFS(ngb, recipeStatus, cookedRecipe)) {
                return false;
            }
        }

        cookedRecipe.add(recipe);
        recipeStatus.put(recipe, COOKED);
        return true;
    }

    public static void main(String[] args) {
        String[] recipes = {"bread"};
        List<List<String>> ingredients = new ArrayList<>();

        List<String> ingre = new ArrayList<>();
        ingre.add("flour");
        ingre.add("yeast");

        ingredients.add(ingre);

        String[] supplies = {"flour", "yeast", "corn"};

        System.out.println(findAllRecipes(recipes, ingredients, supplies));
    }
}
