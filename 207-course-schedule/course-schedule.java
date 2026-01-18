class Solution {
    public boolean dfs(int u, List<List<Integer>> g, boolean[] visited, boolean[] path) {
        visited[u] = true;
        path[u] = true;
        for(Integer x: g.get(u)) {
            if(path[x]) return true;
            if(!visited[x] && dfs(x,g,visited,path)){
                return true;
            }
        }
        path[u] = false;
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length ==0) return true;
        boolean[] visited = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];
        List<List<Integer>> g = new ArrayList<>();
        for(int i=0; i<numCourses; i++) {
            g.add(new ArrayList<>());
            visited[i] = false;
            path[i] = false;
        }
        for(int i = 0; i<prerequisites.length; i++) {
            if(prerequisites[i][0] == prerequisites[i][1]) return false;
            g.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        for(int i=0; i<numCourses; i++) {
            if(!visited[i]) {
                if(dfs(i, g, visited,path)){
                    return false;
                }
            }
        }
        for(boolean v: visited) {
            if(!v) return false;
        }
        return true;

    }
}