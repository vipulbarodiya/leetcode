class Solution {
    private void dfs(int i, List<List<Integer>> g, boolean[] visited) {
        visited[i] = true;
        for(int u: g.get(i)) {
            if(!visited[u]){
                dfs(u,g,visited);
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        int c = 0;
        List<List<Integer>> g = new ArrayList<>();
        for(int i=0; i<n; i++) {
            g.add(new ArrayList<>());
        }
        for(int[] edge:edges) {
            int u = edge[0];
            int v = edge[1];

            g.get(u).add(v);
            g.get(v).add(u);
        }
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                c++;
                dfs(i, g, visited);
            }
        }
        return c;

    }
}