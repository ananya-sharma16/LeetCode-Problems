class Solution {
    public long minCost(int[] nums, int[] cost) {
        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        for(int val : nums){
            lo = Math.min(lo, val);
            hi= Math.max(hi, val);
        }
        //System.out.println(lo + " " + hi);
        long res = (long)(1e18);
        while(lo <= hi){
            int mid = (lo + hi) >> 1;
            res = Math.min(res, change(nums, cost, mid));
            long left = change(nums, cost, mid-1); // this two steps are done to check which side should we go for further answer.
            long right = change(nums, cost, mid+1); // if left is smaller then the possible answer should lie in the left part or vise versa
            if(left < right){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        return res;
    }
    
    public long change(int[] nums, int[] costs, int tar){
        long ans = 0;
        for(int i = 0; i<nums.length; i++){
            if(nums[i] != tar){
                int diff = Math.abs(nums[i] - tar); 
                ans+=(long) diff*costs[i]; // I just forget to typecast long here, which cost me extra 5min
            }
        }
        return ans;
    }
}
