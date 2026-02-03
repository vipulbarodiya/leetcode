class Solution {
    private record Cell(int x, int y){};
    public int minKnightMoves(int x, int y) {
        if(x==0 && y==0) return 0;
        // get positive equivalent
        x = Math.abs(x);
        y = Math.abs(y);

        Queue<Cell> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[305][305];
        int offset = 2;
        q.add(new Cell(0,0));
        visited[offset][offset] = true;
        int moves = 0;

        // keep moves only for postive direction
        List<List<Integer>> dirs = List.of(List.of(2,1), List.of(2,-1), List.of(1,2), List.of(-1, 2), List.of(-2,1), List.of(-2,1), List.of(1,-2), List.of(-1,-2));


        while(!q.isEmpty()) {
            int s = q.size();
            for(int i=0; i<s; i++) {
                Cell c = q.poll();
                if(c.x == x && c.y==y) return moves;

                for(int j=0; j<dirs.size(); j++) {
                    int newx = c.x + dirs.get(j).get(0);
                    int newy = c.y + dirs.get(j).get(1);
                    if (newx >= -2 && newy >= -2 && newx <= 302 && newy <= 302) {
                        if(!visited[newx+offset][newy+offset]) {
                            q.add(new Cell(newx, newy));
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