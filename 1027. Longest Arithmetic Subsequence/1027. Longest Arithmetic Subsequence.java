class Solution {
    public int longestArithSeqLength(int[] A) {
        int hash[][] = new int[A.length][20000];
        int max = 0;
        for(int i = 1; i < A.length; i++)
            for(int j = i-1; j >=0; j--){
                int diff = A[i] - A[j] + 10000;
                int counttillnow = hash[j][diff];
                if(hash[i][diff] > counttillnow) continue;
                else {
                    hash[i][diff] = counttillnow + 1;
                    max = Math.max(max, counttillnow + 1);
                }
            }
        return max + 1;
    }
}
