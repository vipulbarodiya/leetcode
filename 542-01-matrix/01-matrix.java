class Solution {
    private record Cell<E, I >(E row, I col) {};

    public int[][] updateMatrix(int[][] mat) {
        if(mat == null || mat.length ==0) return mat;

        int m = mat.length;
        int n = mat[0].length;

        Queue<Cell<Integer, Integer>> q = new ArrayDeque<>();

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(mat[i][j] == 0) {
                    q.offer(new Cell<>(i,j));
                } else if (mat[i][j] == 1) {
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int dist = 1;
        int[] dirx = {-1, 0, 1, 0};
        int[] diry = {0, 1, 0, -1};
        boolean [][] visited = new boolean[m][n];
        while(!q.isEmpty()) {
            for(int s= q.size(); s>0; s--) {
                Cell<Integer, Integer> cell = q.poll();
                int row = cell.row;
                int col = cell.col;
                visited[row][col] = true;

                for(int k = 0; k<4; k++) {
                    int newx = row + dirx[k];
                    int newy = col + diry[k];

                    if(newx<0 || newy<0 || newx>=m || newy>=n || visited[newx][newy] || mat[newx][newy] == 0) {
                        continue;
                    }

                    if(mat[newx][newy] != 0) {
                        mat[newx][newy] = Math.min(mat[newx][newy], dist);
                        q.offer(new Cell<>(newx, newy));
                    }
                }

            }
            dist++;
        }

        return mat;
    }
}