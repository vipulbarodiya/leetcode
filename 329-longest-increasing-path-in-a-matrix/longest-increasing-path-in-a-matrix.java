class Solution {
    int ans = 0;


    private final static int[][] dirs = new int[][]{
            {-1,0},
            {0,-1},
            {1,0},
            {0,1}
    };
    public int dfs(int i, int j, int[][] matrix, int[][] memo) {
        int path = 0;
        int ans = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        if (memo[i][j]!=0) {
            return memo[i][j];
        }
        for (int k = 0; k < 4; k++) {
            int nx = i + dirs[k][0];
            int ny = j + dirs[k][1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                path  = 0;
                continue;
            } else if (matrix[i][j] < matrix[nx][ny]) {
                if (memo[nx][ny] != 0) {
                    path = Math.max(path, memo[nx][ny]);
                } else  {
                    memo[nx][ny] = dfs(nx, ny, matrix, memo);
                    path = Math.max(path, memo[nx][ny]);
                }
            }
            ans = Math.max(path+1,ans);

        }
        return ans;
    }

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if(m==0) return 0;
        int n = matrix[0].length;
        int ans = 1;
        int [][] memo = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(memo[i][j]!=0) {
                    ans = Math.max(ans, memo[i][j]);
                } else {
                    ans = Math.max(ans,dfs(i,j,matrix,memo));
                }
            }
        }
        return ans;
    }
}