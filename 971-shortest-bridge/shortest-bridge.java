class Solution {
    public record Cell(int r, int c) {};
    public static int[][] dirs = new int[][]{
            {-1, 0},
            {0, 1},
            {1, 0},
            {0,-1}
    };

    public boolean isValidCell(int r, int c, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(r>=m || c>=n || r<0 || c<0 || grid[r][c] == 0 || grid[r][c] ==2){
            return false;
        }
        return true;
    }

    public boolean isValidCellBFS(int r, int c, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(r>=m || c>=n || r<0 || c<0 || grid[r][c] ==2){
            return false;
        }
        return true;
    }

    public void dfs(int r, int c, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        grid[r][c] = 2;
        for(int k = 0; k< dirs.length; k++) {
            if (isValidCell(r + dirs[k][0], c + dirs[k][1], grid)) {
                dfs(r + dirs[k][0], c + dirs[k][1], grid);
            }
        }
    }
    public int shortestBridge(int[][] grid) {
        int bridge = 0;
        int m = grid.length;
        if (m == 0) return -1;
        int n = grid[0].length;
        boolean first = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j, grid);
                    first = true;
                    break;
                }
            }
            if(first) break;
        }
        Queue<Cell> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Cell(i, j));
                }
            }
        }


        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                Cell c = q.poll();
                for (int k = 0; k < 4; k++) {
                    if ( isValidCellBFS(c.r + dirs[k][0], c.c + dirs[k][1], grid)) {
                        if (grid[c.r + dirs[k][0]][c.c + dirs[k][1]] == 1) {
                            return bridge;
                        }
                        q.add(new Cell(c.r + dirs[k][0], c.c + dirs[k][1]));
                        grid[c.r + dirs[k][0]][c.c + dirs[k][1]] = 2;
                    }
                }
            }
            bridge++;
        }
        return bridge-1;

    }
}