class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        if(colors.isEmpty()) return -1;
        int n = colors.length();


//        graph of n nodes, add, edges, check cycle and apply topological sorting
//        once you have topo, find the max occuring color

        int k = 0;
        Map<Integer, Character> nodeColors = new HashMap<>();
        int [] colorScores = new int[26];
        for(char c: colors.toCharArray()) {
            nodeColors.put(k,c);
            k++;
        }
        List<List<Integer>> g = new ArrayList<>();
        for(int i=0; i<n; i++) {
            g.add(new ArrayList<>());
        }

        int [] in =  new int[n];
        for(int[] e: edges) {
            g.get(e[0]).add(e[1]);
            in[e[1]]++;
        }
        Queue<Integer> q = new ArrayDeque<>();
        boolean [] visited = new boolean[n];
        int processed = 0;
        for(int i=0; i<n; i++){
            if(in[i] ==0){
                q.add(i);
                visited[i] = true;
            }
        }

        int[][] dp = new int[n][26];
        int ans = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            processed++;

            // 1. Add this node's own color to its path count
            dp[u][colors.charAt(u) - 'a']++;
            ans = Math.max(ans, dp[u][colors.charAt(u) - 'a']);

            for (int v : g.get(u)) {
                // 2. Pass the maximum color frequencies to the neighbor
                for (int i = 0; i < 26; i++) {
                    dp[v][i] = Math.max(dp[v][i], dp[u][i]);
                }

                if (--in[v] == 0) {
                    q.add(v);
                }
            }
        }
        return processed < n ? -1 : ans;
    }
}