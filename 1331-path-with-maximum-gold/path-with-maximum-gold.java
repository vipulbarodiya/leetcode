class Solution {
    public final static int[][] dirs = new int[][] {
            {-1,0},
            {0,1},
            {1,0},
            {0,-1}
    };

    private boolean isValid(int r, int c, int[][] grid, boolean [][] vis) {
        int m = grid.length;
        int n = grid[0].length;

        if(r>=m || c>=n || r<0 || c<0 || vis[r][c] || grid[r][c] == 0) {
            return false;
        }
        return true;
    }
    private int dfs(int r, int c, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(r>=m || c>=n || r<0 || c<0 || grid[r][c] == 0) {
            return 0;
        }
        int origin = grid[r][c];
        grid[r][c] = 0;
        int localMax = 0;
        for(int i=0; i<4; i++) {
            int nx = r+dirs[i][0];
            int ny = c+dirs[i][1];

            localMax = Math.max(localMax,dfs(nx,ny,grid));

        }
        grid[r][c] = origin;
        return origin+localMax;
    }
    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxGold = 0;
        for(int i=0; i<m; i++) {
            for(int j = 0; j<n; j++) {
                if(grid[i][j] > 0) {
                    maxGold = Math.max(maxGold, dfs(i,j, grid));
                }
            }
        }
        return maxGold;
    }
}