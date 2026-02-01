class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int[] entrytime =  new int[n];
        int currentTime = 1;
        int ans = -1;
        for(int i=0; i<n; i++) {
            int startTime = currentTime;
            int u = i;

            if(entrytime[u] > 0) {
                continue;
            }
            while(u!=-1 && entrytime[u]  == 0) {
                entrytime[u] = currentTime++;
                u = edges[u];
            }
            if(u!=-1 && entrytime[u] >= startTime){
                ans = Math.max(ans, currentTime - entrytime[u]);
            }

        }
        return ans;
    }
}