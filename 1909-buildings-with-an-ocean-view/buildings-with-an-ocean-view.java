class Solution {
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        int maxH = -1;
        List<Integer> ans = new ArrayList<>();
        for(int i=n-1; i>=0; i--) {
            if(heights[i] > maxH) {
                ans.add(i);
                maxH = heights[i];
            }
        }
        ans = ans.reversed();
        return ans.stream().mapToInt(i->i).toArray();
    }
}