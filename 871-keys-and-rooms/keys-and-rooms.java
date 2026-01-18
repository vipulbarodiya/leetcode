class Solution {
    public void dfs( int u, List<List<Integer>> g, boolean[] visited) {
        visited[u] = true;
        for(int n: g.get(u)){
            if(!visited[n]) {
                dfs(n, g, visited);
            }
        }
    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n =  rooms.size();
        boolean[] visited = new boolean[n];
        dfs(0, rooms, visited);
        for(boolean v: visited) {
            if(v==false) return false;
        }
        return true;
    }
}