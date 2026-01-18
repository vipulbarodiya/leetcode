class Solution {

    public void dfs(int u, int[][] graph, boolean[] visited, List<Integer> path, List<List<Integer>> result) {
        path.add(u);

        if(u == (visited.length - 1)) {
            result.add(new ArrayList<>(path));
        }
        for(int n: graph[u]) {
            dfs(n, graph, visited, path, result);
        }
        path.removeLast();
    }
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        int n = graph.length;
        boolean[] visited = new boolean[n];
        List<Integer> path = new ArrayList<>();
        dfs(0, graph, visited, path, result);
        return result;
    }
}