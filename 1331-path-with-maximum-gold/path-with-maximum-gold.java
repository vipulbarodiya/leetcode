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
    int ans = 0;
    int gold = 0;
    private void dfs(int r, int c, int[][] grid, boolean[][] vis) {
        vis[r][c] = true;
        gold += grid[r][c];
        for(int i=0; i<4; i++) {
            int nx = r+dirs[i][0];
            int ny = c+dirs[i][1];

            if(isValid(nx,ny, grid, vis)) {
                dfs(nx,ny,grid,vis);
            }

        }
        ans = Math.max(ans, gold);
        gold  -= grid[r][c];
        vis[r][c] = false;
    }
    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        for(int i=0; i<m; i++) {
            for(int j = 0; j<n; j++) {

                if(isValid(i,j,grid,vis)) {
                    dfs(i,j, grid, vis);
                    ans = Math.max(ans, gold);
                }
            }
        }
        return ans;
    }
}