class Solution {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxGold = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && countNeighbors(grid, i, j) <= 2) {
                    maxGold = Math.max(maxGold, dfs(grid, i, j));
                }
            }
        }
        
        if (maxGold == 0) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] > 0) return dfs(grid, i, j);
                }
            }
        }

        return maxGold;
    }

    private int countNeighbors(int[][] grid, int r, int c) {
        int count = 0;
        if (r > 0 && grid[r - 1][c] > 0) count++;
        if (r < grid.length - 1 && grid[r + 1][c] > 0) count++;
        if (c > 0 && grid[r][c - 1] > 0) count++;
        if (c < grid[0].length - 1 && grid[r][c + 1] > 0) count++;
        return count;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        int val = grid[r][c];
        grid[r][c] = 0; 

        int up = dfs(grid, r - 1, c);
        int down = dfs(grid, r + 1, c);
        int left = dfs(grid, r, c - 1);
        int right = dfs(grid, r, c + 1);

        int max = up;
        if (down > max) max = down;
        if (left > max) max = left;
        if (right > max) max = right;

        grid[r][c] = val; 
        return val + max;
    }
}