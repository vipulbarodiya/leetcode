class Solution {
    private record Cell<E, I >(E row, I col) {};

    public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        int INF = Integer.MAX_VALUE;

        boolean[][] visited = new boolean[m][n];
        Queue<Cell<Integer, Integer>> q = new ArrayDeque<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(rooms[i][j] == 0) {
                    q.offer(new Cell<>(i,j));
                }
            }
        }

        int[] dirx = {-1, 0, 1, 0};
        int[] diry = {0, 1, 0, -1};

        int dist = 0;
        while(!q.isEmpty()) {

            for(int s = q.size(); s>0; s--) {
                Cell<Integer, Integer> cell = q.poll();
                int row = cell.row;
                int col = cell.col;
                rooms[row][col] = Math.min(rooms[row][col], dist);
                visited[row][col] = true;

                for(int k=0; k<4; k++) {
                    int newx = row+dirx[k];
                    int newy = col+diry[k];

                    if(newx<0 || newy<0 || newx >= m || newy >= n || rooms[newx][newy] == -1) {
                        continue;
                    }
                    if(!visited[newx][newy])    {
                        q.offer(new Cell<>(newx, newy));
                    }
                }
            }
            dist++;


        }
        return;
    }
}