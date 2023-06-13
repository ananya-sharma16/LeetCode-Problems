class Solution {
    public int equalPairs(int[][] grid) {
        Map<List<Integer>,Integer> m = new HashMap<>();
        
        for(int i=0;i<grid.length;i++)
        {
            List<Integer> l =new ArrayList<>();
            for(int j=0;j<grid.length;j++){
                l.add(grid[i][j]);
            }
               m.put(l,m.getOrDefault(l,0)+1);
        }
        int count=0;
        
        for(int j=0;j<grid.length;j++)
        
        
        {
            List<Integer> l = new ArrayList<>();
            for(int i=0;i<grid.length;i++){
                l.add(grid[i][j]);
            }
            
            if(m.containsKey(l)) count+=m.get(l);
        }
        return count;
    }
}
