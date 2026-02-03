class Solution {

    public static final int[][] dirs = new int[][]{{2,1},{2,-1},{1,2},{-1,2},{-2,1},{1,-2},{-1,-2}};
    public int minKnightMoves(int x, int y) {
        if(x==0 && y==0) return 0;
        x = Math.abs(x);
        y = Math.abs(y);

        Queue<Integer> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[305][305];
        int offset = 2;
        q.add(0|0);
        visited[offset][offset] = true;
        int moves = 0;


        while(!q.isEmpty()) {
            int s = q.size();
            for(int i=0; i<s; i++) {
                Integer c = q.poll();
                int ux = c>>10;
                int uy = c&1023;
                if(ux == x && uy==y) return moves;

                for(int j=0; j<dirs.length; j++) {
                    int newx = ux + dirs[j][0];
                    int newy = uy + dirs[j][1];
                    if (newx >= -2 && newy >= -2 && newx <= 302 && newy <= 302) {
                        if(!visited[newx+offset][newy+offset]) {
                            q.add((newx<<10) | (newy &1023));
                            visited[newx+offset][newy+offset] = true;
                        }
                    }


                }
            }
            moves++;
        }

        return -1;
    }
}