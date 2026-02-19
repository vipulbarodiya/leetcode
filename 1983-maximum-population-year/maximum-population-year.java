class Solution {
    public int maximumPopulation(int[][] logs) {
        Map<Integer, Integer> map = new TreeMap<>();
        for(int i=0; i<logs.length; i++) {
            map.put(logs[i][0], map.getOrDefault(logs[i][0], 0)+1);
            map.put(logs[i][1], map.getOrDefault(logs[i][1], 0)-1);

        }
        int ans = 0, c=0; int y = 0;
        for(Integer x: map.keySet()){
            c += map.get(x);
            if(c > ans) {
                ans = c;
                y = x;
            }
        }
        return y;
    }
}