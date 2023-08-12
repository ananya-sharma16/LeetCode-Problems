class Solution {
    public int uniquePathsWithObstacles(int[][] A) {
        int[] dp = new int[A[0].length+1];
        dp[1] = 1; 

        for (int r = 0; r < A.length; r++)
            for (int c = 1; c <= A[0].length; c++)
                dp[c] = A[r][c-1] == 1 ? 0 : dp[c] + dp[c-1];

        return dp[A[0].length];
    }
}