class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        int n=costs.length;
        Queue<Integer>pq1=new PriorityQueue<>();
        Queue<Integer>pq2=new PriorityQueue<>();
        int i=0;
		// adding candidates for 1st pq
        while(i<candidates && i<n){
            pq1.add(costs[i]);
            i++;
        }
        int j=n-1;
		// adding candidates from last to 2nd pq
        while(j>=i && n-j<=candidates){
            pq2.add(costs[j]);
            j--;
        }
        long ans=0;
        while(k>0){
		// checking if both are non-empty
           if(!pq1.isEmpty() && !pq2.isEmpty()){
		   // checek for minimum
               if(pq1.peek()<=pq2.peek()){
                   ans+=pq1.peek();
                   pq1.poll();
                   if(i<=j){
				   // adding new element after popping
                       pq1.add(costs[i]);
                       i++;
                   }
               }else{
                   ans+=pq2.peek();
                   pq2.poll();
                   if(j>=i){
				   // adding new element after popping
                       pq2.add(costs[j]);
                       j--;
                   }
               }
           }else if(!pq1.isEmpty() && pq2.isEmpty()){
		   // if second becomes empty
               ans+=pq1.peek();
               pq1.poll();
               if(i<=j){
                   pq1.add(costs[i]);
                   i++;
               }
           }else if(pq1.isEmpty() && !pq2.isEmpty()){
		   // if first becomes empty
               ans+=pq2.peek();
               pq2.poll();
               if(j>=i){
                   pq2.add(costs[j]);
                   j--;
               }
           }
            k--;
        }
        return ans;
    }
}
