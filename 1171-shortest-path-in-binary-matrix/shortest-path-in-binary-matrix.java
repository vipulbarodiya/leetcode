

class Solution {

    private record Cell(int r, int c){};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<Cell> q = new ArrayDeque<>();
        if(grid[0][0] == 0) {
            q.offer(new Cell(0,0));
            visited[0][0] = true;
        } else {
            return -1;
        }

        int[][] dirs = { {-1,-1}, {0, -1}, {1,-1}, {-1, 0}, {1,0},{-1,1},{0,1},{1, 1} };
        int dist = 1;
        int ans = 0;
        while(!q.isEmpty()) {
            for(int s = q.size(); s>0; s--) {
                Cell cell = q.poll();
                int row = cell.r;
                int col = cell.c;

                for(int i=0; i< dirs.length; i++) {
                    int x = row+dirs[i][0];
                    int y = col+dirs[i][1];

                    if(x<0 || y<0 || x>=grid.length || y>=grid[0].length || visited[x][y] || grid[x][y] == 1) {
                        continue;
                    }

                    if((x == (grid.length-1)) && (y == (grid[0].length -1))) {
                        ans = dist;
                    }
                    visited[x][y] = true;
                    q.offer(new Cell(x,y));
                }
            }
            dist++;
        }
        return visited[m-1][n-1]==false?-1:(ans+1);
    }
}