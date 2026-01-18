class Solution {
    public void dfs(int u, List<List<Integer>> g, boolean[] visited) {
        visited[u] = true;
        for(Integer n: g.get(u)) {
            if(!visited[n]) {
                dfs(n,g, visited);
            }
        }
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        int l = edges.length;

        List<List<Integer>> g = new ArrayList<>();
        boolean[] visited = new boolean[n];


        for(int i=0; i<n; i++) {
            g.add(new ArrayList<>());
            visited[i] = false;
        }
        for(int i=0; i<l; i++) {
            g.get(edges[i][0]).add(edges[i][1]);
            g.get(edges[i][1]).add(edges[i][0]);
        }

        dfs(source, g, visited);
        return visited[destination];
    }
}