class Solution {
    public record Edge(int to, int weight){};
    public record Pair(int r, int c, int cost){};

    public static final int[][] dirs = new int[][]{
            {-1, 0},
            {0,1},
            {1,0},
            {0,-1}
    };

    public static boolean isValid(int r, int c, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(r<0 || c<0 || r==m || c==n ){
            return false;
        }
        return true;
    }
    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        if(m==0) return 0;
        int n = grid[0].length;
        if(m==1 && n==1) return grid[0][0] == 1?1:0;
        List<List<Edge>> g = new ArrayList<>();
        int[][] distance = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });
        distance[0][0] = grid[0][0]==1?1:0;
        q.add(new Pair(0,0,distance[0][0]));
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            int row = curr.r;
            int col = curr.c;
            int cost = curr.cost;
            if(row == m-1 && col == n-1){
                return distance[row][col];
            }
            if(cost > distance[row][col]) continue;
            for(int i=0; i<4; i++) {
                int nextR = row + dirs[i][0];
                int nextC = col + dirs[i][1];

                if(isValid(nextR, nextC, grid)) {
                    int nextCost = cost + ((grid[nextR][nextC] == 1)?1:0);
                    if(nextCost < distance[nextR][nextC]) {
                        distance[nextR][nextC] = nextCost;
                        q.add(new Pair(nextR, nextC, distance[nextR][nextC]));
                    }
                }
            }
        }
        return distance[m-1][n-1];
    }
}