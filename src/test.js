// Complete the maxWin function below.
static int maxWin(int[] v) {
    // return maxWinRec(v, 0, v.length-1);
    return maxWinDP(v);
}

static int maxWinDP(int[] v) {
    int length = v.length;
    int[][] dp = new int[length][length];
    //init dp table filled lower bottom left with zero and diagonals with the corresponding value in v i.e. dp(1,1) --> v[1] etc
    /**
     *      8 -  - -
     *      0 15 - -
     *      0 0  3 -
     *      0 0  0 7
     */
    for(int start=0; start<length; start++) {
        for(int end=0; end<=start; end++) {
            if(start == end) {
                dp[start][end] = v[start];
            } else {
                dp[start][end] = 0;
            }
        }
    }

    //upper right diagonal in diagonal order i.e. first round [0,1] [1,2] [2,3]
    for(int i=1; i<length; i++) {
        for (int start=0, end=i; start<length-1 && end<length; start++, end++) {
            int includeStartSum = v[start] + Math.min(getFromDPTable(dp, start+2, end), getFromDPTable(dp,start+1,end-1));
            int includeEndSum = v[end] + Math.min(getFromDPTable(dp, start,end-2), getFromDPTable(dp, start+1, end-1));
            dp[start][end] = Math.max(includeStartSum, includeEndSum);
        }
    }
    return dp[0][length-1];
}

static int getFromDPTable(int[][] dp, int start, int end) {
    int length = dp.length;
    if(start < 0 || start>=length || end < 0 || end >=length)
        return 0;
    return dp[start][end];
}

static int maxWinRec(int[] v, int start, int end) {
    if(start > end)
        return 0;

    if(start == end)
        return v[start];

    // If I choose start then opponent can select start+1 or end --> and I pick min from both in worst case
    int includeStartSum = v[start] + Math.min(maxWinRec(v, start+2, end), maxWinRec(v, start+1, end-1));
    // If I choose end then opponent can select start or end-1 --> and I pick min from both in worst case
    int includeEndSum = v[end] + Math.min(maxWinRec(v, start, end-2), maxWinRec(v, start+1, end-1));

    return Math.max(includeStartSum, includeEndSum);
}


