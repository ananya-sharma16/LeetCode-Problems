class Solution {
    public int largestVariance(String s) {
        int var=0;
        char[] ch=s.toCharArray();
        int[] lastChar=new int[26];
        for(int k=0;k<ch.length;k++) lastChar[ch[k]-'a']=k;
        for(int i='a';i<='z';i++) {
            for(int j='a';j<='z';j++) {
                int c1=0,c2=0,c1n=0,c2n=0;
                for(int k=0;k<ch.length;k++) {
                    char c=ch[k];
                    if(c==i) c1++;
                    else if(c==j&&++c2>c1) {
                        if(lastChar[c-'a']>k) c2=0;
                        else c2=1;
                        c1=0;
                    }
                    if(var<c1-c2&&c2>0) var=c1-c2;
                }
            }
            
            
        }
        return var;
    }
}