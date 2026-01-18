class Solution {

    public record Cell(int r, int c, int g, int f) implements Comparable<Cell>{
        @Override
        public int compareTo(Cell other) {
            return Integer.compare(this.f, other.f);
        }
    };
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(n==0) return -1;
        if(n==1) {
            if(grid[0][0] == 0) return 1;
            else if(grid[0][0] == 1) return -1;
        }

        if(grid[0][0] == 1) return -1;
        int[][] bestG = new int[n][n];
        for(int [] row: bestG) {
            Arrays.fill(row,Integer.MAX_VALUE);
        }

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        bestG[0][0] = 1;
        int startH = n-1; //. 0,0 -> n-1,n-1 // max delta x, delta y returns n-1.
        pq.offer(new Cell (0,0,1,1+startH));


        int[][] dirs = {
                {-1,-1},  {-1,0}, {-1,1},
                {0, -1},          {0,1},
                {1, -1},  {1,0},  {1,1}
        };
        while(!pq.isEmpty()) {
            Cell curr = pq.poll();

            if(curr.g > bestG[curr.r][curr.c]) continue;
            if(curr.r == (n - 1) && curr.c == (n - 1)) return curr.g;



            for(int i=0; i<dirs.length; i++) {
                int x = curr.r+dirs[i][0];
                int y = curr.c+dirs[i][1];

                if(x<0 || y<0 || x>=n || y>=n || grid[x][y]==1) {
                    continue;
                }

                int nextG = curr.g+1;
                if(bestG[x][y] > nextG) {
                    bestG[x][y] = nextG;
                    int h = Math.max((n-1-x), (n-1-y));
                    pq.offer(new Cell(x,y,bestG[x][y], bestG[x][y]+h));
                }

            }
        }
        return -1;

    }
}