class Solution {
    public boolean dfs(int u, List<List<Integer>> g, boolean[] visited, boolean[] path, Stack<Integer> st) {
        visited[u] = true;
        path[u] = true;
        for(Integer x:g.get(u)) {
            if(path[x]) return true;
            if(!visited[x] && dfs(x,g,visited,path,st)) {
                return true;
            }
        }
        path[u] = false;
        st.push(u);
        return false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int n = numCourses;

        List<List<Integer>> g = new ArrayList<>();
        for(int i=0; i<n; i++) {
            g.add(new ArrayList<>());
        }
        for(int[] pr: prerequisites) {
            g.get(pr[0]).add(pr[1]);
        }

        boolean[] visited = new boolean[n];
        boolean[] path = new boolean[n];
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                if(dfs(i, g, visited, path, st)) {
                    return new int[]{};
                }
            }
        }

        List<Integer>result = new ArrayList<>();
        while(!st.isEmpty()) {
            result.add(st.pop());
        }
        return result.reversed().stream().mapToInt(i->i).toArray();
    }
}