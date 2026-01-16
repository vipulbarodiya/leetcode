class Solution {
    private record Cell(int row, int col) {};

    public int maxDistance(int[][] grid) {
        if(grid == null || grid.length == 0)
            return -1;
        int m = grid.length;
        int n = grid[0].length;

        Queue<Cell> q = new ArrayDeque<>();
        int ones = 0;
        int zeroes = 0;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 0) {
                    grid[i][j] = Integer.MIN_VALUE;
                    zeroes++;
                } else  {
                    q.offer(new Cell(i,j));
                    ones++;
                }
            }
        }

        if(zeroes == m*n || ones == m*n) return -1;

        int[] dirx = {-1, 0, 1, 0};
        int[] diry = {0, 1, 0, -1};

        int dist = -1;
        boolean[][] visited = new boolean[m][n];

        while(!q.isEmpty()) {
            for(int s=q.size(); s>0; s--) {
                Cell cell = q.poll();
                int row = cell.row;
                int col = cell.col;

                for(int k = 0; k<4; k++) {
                    int newx = row+dirx[k];
                    int newy = col+diry[k];

                    if(newx < 0 || newy < 0 || newx >= m || newy >= n || visited[newx][newy] || grid[newx][newy] == 1){
                        continue;
                    }
                    q.offer(new Cell(newx, newy));
                    visited[newx][newy] = true;
                }
            }
            dist++;
        }

        return dist;

    }
}