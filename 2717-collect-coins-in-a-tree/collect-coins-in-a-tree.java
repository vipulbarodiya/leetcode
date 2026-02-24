class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        List<Set<Integer>> g = new ArrayList<>();
        int[] degree = new int[n];
        for(int i=0; i<n; i++) {
            g.add(new HashSet<Integer>());
        }
        for(int[] edge: edges) {
            g.get(edge[0]).add(edge[1]);
            g.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++) {
            if(degree[i] == 1 && coins[i]==0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            Integer leaf = q.poll();
            for(Integer neighbor: g.get(leaf) ){
                degree[neighbor]--;
                if(degree[neighbor] == 1 && coins[neighbor]==0) {
                    q.offer(neighbor);
                }
            }
            degree[leaf] = 0;

        }
        for(int j=0; j<2; j++) {
            List<Integer> currentLeaves = new ArrayList<>();
            for(int i=0; i<n; i++) {
                if(degree[i] == 1) {
                    currentLeaves.add(i);
                }
            }
            for(Integer leaf: currentLeaves) {
                for(Integer neighbor: g.get(leaf) ){
                    degree[neighbor]--;
                }
                degree[leaf] = 0;
            }
        }
        int ans = 0;
        for(int[] edge: edges) {
            if((degree[edge[0]] > 0) && (degree[edge[1]]  >0))
                ans++;
        }
        return ans*2;
    }
}