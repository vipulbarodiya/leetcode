class Solution {
    private record Pair(int r, int c, int time){};
    private static final int[][] dir = new int[][] {
            {0,1},
            {1,0},
            {0,-1},
            {-1,0},
    };

    private boolean isValidCell(int r, int c, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if(r>=m || c>=n || r<0 || c<0) return false;
        return true;
    }
    public int swimInWater(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int [][] t = new int[m][n];

        for(int i=0; i<m; i++) {
            Arrays.fill(t[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.time, o2.time);
            }
        });
        q.add(new Pair(0,0, grid[0][0]));
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            visited[curr.r][curr.c] = true;
            for(int i=0; i<4; i++) {
                int nx = curr.r + dir[i][0];
                int ny = curr.c + dir[i][1];
                int time = curr.time;
                if(isValidCell(nx, ny, grid)){
                    int minTime = Math.max(time, grid[nx][ny]);
                    if(visited[nx][ny]) continue;
                    if(minTime < t[nx][ny] ){
                        t[nx][ny] = minTime;
                        q.add(new Pair(nx, ny, t[nx][ny]));
                    }
                }
            }
        }
        return t[m-1][n-1]!=Integer.MAX_VALUE?t[m-1][n-1]:grid[m-1][n-1];
    }
}