class Solution {
    boolean cycle = false;

    public void dfs(int u, List<List<Integer>> g, boolean[] visited, boolean[] path, boolean cycle) {
        visited[u] = true;
        path[u] = true;
        for(Integer n: g.get(u)) {
            if(path[n] == true) cycle = true;
            if(!visited[n]) {
                dfs(n,g, visited, path, cycle);
            }
        }
        path[u] = false;
    }
    public boolean validTree(int n, int[][] edges) {
        int l = edges.length;
        if(l!=n-1) return false;

        List<List<Integer>> g = new ArrayList<>();
        boolean[] path = new boolean[n];
        boolean[] visited = new boolean[n];
    

        for(int i=0; i<n; i++) {
            g.add(new ArrayList<>());
            path[i] = false;
            visited[i] = false;
        }
        int root = 0;
        for(int i=0; i<l; i++) {
            g.get(edges[i][0]).add(edges[i][1]);
            g.get(edges[i][1]).add(edges[i][0]);
            root = root^edges[i][0];
            root = root^edges[i][1];
        }

        dfs(0, g, visited, path, cycle);
        boolean connected = true;
        for(int i=0; i<n; i++) {
            if(visited[i] == false) {
                connected = false;
                break;
            }
        }
        
        return (cycle == false) && (connected == true);


    }
}