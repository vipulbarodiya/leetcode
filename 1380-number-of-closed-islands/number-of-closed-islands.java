class Solution {
    int[] dirx = {-1, 0, 1, 0};
    int[] diry = {0, 1, 0, -1};
    private boolean dfs(int row, int col, boolean[][] visited, int[][] grid) {
        visited[row][col] = true;
        boolean isClosed = true;
        if(row == 0 || col == 0 || row == grid.length-1 || col == grid[0].length-1) {
            isClosed =  false;
        }
        for(int i=0; i<4; i++) {
            int x = row + dirx[i];
            int y = col + diry[i];

            if(x>=0 && y>=0 && x<grid.length && y< grid[0].length && !visited[x][y] && grid[x][y] == 0 ){
                isClosed = isClosed & dfs(x,y,visited,grid);
            }
        }
        return isClosed;
    }
    public int closedIsland(int[][] grid) {
        if(grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        int c = 0;
        for(int i=0; i<m; i++) {
            for(int j = 0; j<n; j++) {
                if(!visited[i][j] && (grid[i][j] == 0) && dfs(i, j,visited, grid) ) {
                    c++;
                }
            }
        }
        return c;
    }
}