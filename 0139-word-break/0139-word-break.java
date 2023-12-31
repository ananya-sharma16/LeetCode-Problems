class Solution {
    Map<String, Boolean> map;
    public boolean wordBreak(String s, List<String> wordDict) {
        map = new HashMap<>();
        map.put("", true);
        return solve(s, wordDict);
    }

    private boolean solve(String s, List<String> wordDict) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        boolean val = false;
        for (String next: wordDict) {
            if(s.indexOf(next) == 0 && solve(s.substring(next.length()), wordDict)) {
                val = true;
                break;
            }
        }

        map.put(s, val);
        return val;
    }
}