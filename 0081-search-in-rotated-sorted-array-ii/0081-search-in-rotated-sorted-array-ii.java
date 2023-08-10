class Solution {
    public boolean search(int[] nums, int target) {
     
        int i=0;
        int j=nums.length-1;
        return find(nums,target,i,j);
        
       
    }
    
    public boolean find(int nums[],int t,int i,int j)
    {
        if(i>j)
            return false;
        
            int m=(i+j)/2;
        
            if(nums[m]==t)
                return true;
        
        if((nums[m]<=nums[i] && nums[m]<=nums[j])||(nums[m]<=nums[i] && nums[m]<=nums[j]))
            return find(nums,t,i,m-1)||find(nums,t,m+1,j);
        
        if(nums[i]<=t && nums[m]>=t)
           return find(nums,t,i,m-1);
           
           return find(nums,t,m+1,j);

    }
}