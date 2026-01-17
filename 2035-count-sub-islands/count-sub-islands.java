class Solution {

    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};
    public boolean dfs(int r, int c, int[][] grid1, int[][] grid2, boolean[][] visited) {
        visited[r][c] = true;
        boolean isSub = true;
        if(grid1[r][c] == 0) isSub = false;
        
        for(int i=0; i<4; i++) {
            int x= r+dx[i];
            int y = c+dy[i];

            if(x<0 || y<0 || x>=grid2.length || y>=grid2[0].length  || grid2[x][y]!=1 || visited[x][y]){
                continue;
            }

            isSub = isSub & dfs(x,y,grid1, grid2,visited);

        }

        return isSub;

    }
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int c = 0;
        boolean[][] visited = new boolean[m][n];


        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if((grid2[i][j] == 1) && !visited[i][j]) {
                    if(dfs(i,j,grid1, grid2,visited)){
                        c++;
                    }
                }
            }
        }
        return c;
    }
}