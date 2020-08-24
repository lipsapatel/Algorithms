
class Result {

    /*
     * Complete the solver function below.
     *
     * The function accepts STRING_ARRAY dictionary as parameter. and string array
     * mat as input matrix. The function returns the list of all possible words from
     * dictionary found in the matrix mat
     */

    public static List<String> boggle_solver(List<String> dictionary, List<String> mat) {
    int rows = mat.size();
    //assuming all strings of same length in mat
    int cols = mat.get(0).length();
    char[][] arr = new char[rows][cols];
    Set<String> set = new HashSet<>();

    TrieNode root = buildTrie(dictionary);

    buildMatrix(arr, mat);

    for(int i=0; i<rows; i++) {
    for(int j=0; j<cols; j++) {
    //check if we have a word starting this char
    if(root.children.containsKey(arr[i][j])) {
    boolean[][] visited = new boolean[rows][cols];
    visited[i][j] = true;
    explore(i, j, arr, root.children.get(arr[i][j]), visited, set, "" + arr[i][j]);
}
}
}
return new ArrayList<>(set);
}

private static void buildMatrix(char[][] arr, List<String> mat) {
    for(int i=0; i<mat.size(); i++) {
        String word = mat.get(i);
        for(int j=0; j<word.length(); j++) {
            arr[i][j] = word.charAt(j);
        }
    }
}

static void explore(int row, int col, char[][] arr, TrieNode root, boolean[][] visited, Set<String> set, String sofar) {


    if(root.children.containsKey('$')) {
        set.add(sofar);
    }

    int[] nextRows = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    int[] nextCols = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

    for(int k=0; k<8; k++) {
        int nextRow = row + nextRows[k];
        int nextCol = col + nextCols[k];

        if(isSafe(arr, nextRow, nextCol) && !visited[nextRow][nextCol] && root.children.containsKey(arr[nextRow][nextCol])) {
            visited[nextRow][nextCol] = true;
            explore(nextRow, nextCol, arr, root.children.get(arr[nextRow][nextCol]), visited, set, sofar + arr[nextRow][nextCol]);
            visited[nextRow][nextCol] = false;
        }
    }
}

static TrieNode buildTrie(List<String> words) {
    TrieNode root = new TrieNode();

    for(String word : words) {
        int i = 0;
        TrieNode curr = root;
        while(i < word.length()) {
            if(curr.children.containsKey(word.charAt(i))) {
                curr = curr.children.get(word.charAt(i));
            } else {
                TrieNode node = new TrieNode();
                curr.children.put(word.charAt(i), node);
                curr = node;
            }
            i++;
        }
        curr.children.put('$', null);
    }
    return root;
}

static boolean isSafe(char[][] arr, int i, int j) {
    if(i<0 || i>=arr.length || j<0 || j>=arr[0].length) {
        return false;
    }
    return true;
}

static class TrieNode {
    Map<Character, TrieNode> children;

    TrieNode() {
        children = new HashMap<>();
    }
}


}

