class Solution {
    private int dfs(int[][] grid, boolean[][] visited, int i, int j, int rows, int cols) {
        visited[i][j] = true;
        int ans = 0;
        int[] dirX = {-1, 0, 1, 0};
        int[] dirY = {0, 1, 0, -1};
        for(int d=0; d<4; d++) {
            int newX = i + dirX[d];
            int newY = j + dirY[d];
            if(isValid(newX, newY, rows, cols, grid, visited)) {
                int cur = dfs(grid, visited, newX, newY, rows, cols);
                ans = ans + cur;
            }
        }
        return ans + 1 ;
    }

    private boolean isValid(int i, int j, int rows, int cols, int[][] grid, boolean[][] visited) {
        return (i>=0 && i<rows && j>=0 && j<cols && grid[i][j] == 1 && !visited[i][j]);
    }
    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int ma = 0;
        boolean [][] visited = new boolean[rows][cols];

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(isValid(i,j, rows, cols, grid, visited)) {
                    int a = dfs(grid, visited, i,j,rows, cols);
                    ma = Math.max(a, ma);
                }
            }
        }
        return ma;
    }
}