class Solution {

    boolean cycle(int i, int[][] graph, boolean[] visited, boolean[] path) {
        visited[i] = true;
        path[i] = true;
        for(int n:graph[i]){
            if(path[n]) return true;
            if(!visited[n] && cycle(n,graph, visited, path))
                return true;
        }
        path[i] = false;
        return false;
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] path = new boolean[n];
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(!cycle(i, graph, visited, path)) {
                ans.add(i);
            }
        }
        return ans;
    }
}