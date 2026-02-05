class Solution {
    public boolean isValidCell(int i, int j, int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        if (i>=m || j>=n || i<0 || j<0){
            return false;
        }
        return true;
    }

    private static int[][] dir = new int[][]{
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    public record Pair(int i, int j, int cost) {};
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] visited = new boolean[m][n];
        int [][] dist = new int[m][n];

        for(int i=0; i<m; i++) {
            for(int j = 0; j<n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });
        q.add(new Pair(0,0, 0));
        dist[0][0] = 0;
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            visited[curr.i][curr.j] = true;
            int cost = curr.cost;
            for(int i=0; i<4; i++) {
                int nx = curr.i+dir[i][0];
                int ny = curr.j + dir[i][1];
                if(isValidCell(nx, ny, heights)) {
                    if(visited[nx][ny]) continue;
                    int minCost = Math.max(cost , Math.abs( heights[nx][ny] - heights[curr.i][curr.j]));
                    if(dist[nx][ny] > minCost  ){
                        dist[nx][ny] = minCost;
                        q.add(new Pair(nx, ny, minCost));
                    }
                }
            }
        }
        return dist[m-1][n-1] == Integer.MAX_VALUE?-1:dist[m-1][n-1];
    }
}