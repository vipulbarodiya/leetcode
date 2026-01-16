class Solution {
    int[] dirx = { -1, 0, 1, 0};
    int[] diry = { 0, 1, 0, -1};
    private int dfs(int row, int col, boolean[][] visited, int[][] grid) {
        visited[row][col] = true;
        int t = 1;
        for(int k = 0; k<4; k++) {
            int x = row+ dirx[k];
            int y = col+ diry[k];

            if(x<0 || y<0 || x>=grid.length || y>= grid[0].length) {
                continue;
            }
            if(!visited[x][y] && grid[x][y] == 1){
                 t = t + dfs(x,y,visited,grid);
            }
        }
        return t;
    }
    public int numEnclaves(int[][] grid) {
        if(grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        int reachable = 0;
        int total = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1) {
                    total++;
                }
            }
        }

        for(int i =0; i<m; i++) {
            if(grid[i][0] == 1 && !visited[i][0]){
                reachable+=dfs(i, 0, visited, grid);
            }
            if(grid[i][n-1] == 1 && !visited[i][n-1]){
                reachable+=dfs(i, n-1, visited, grid);
            }
        }
        for(int i =0; i<n; i++) {
            if(grid[0][i] == 1 && !visited[0][i]){
                reachable+=dfs( 0,i, visited, grid);
            }
            if(grid[m-1][i] == 1 && !visited[m-1][i]){
                reachable+=dfs(m-1,i, visited, grid);
            }
        }

        return total - reachable;

    }
}