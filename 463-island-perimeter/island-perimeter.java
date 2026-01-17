class Solution {
    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};

    int dfs(int r, int c, int[][] grid, boolean[][] visited, int perimeter) {
        visited[r][c] = true;
        for(int k=0; k<4; k++) {
            int x = r+dx[k];
            int y = c+dy[k];

            if(x<0 || y<0 || x>=grid.length || y>= grid[0].length ) {
                perimeter+=1;
                continue;
            }
            if( visited[x][y]) {
                continue;
            }
            if(grid[x][y] == 0) {
                perimeter+=1;
            }
            if(grid[x][y] == 1) {
                perimeter = dfs(x,y,grid,visited, perimeter);
            }
        }
        return perimeter;
    }


    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int c = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1 && !visited[i][j]){
                    c = dfs(i,j,grid, visited, c);
                }
            }
        }
        return c;
    }
}