class Solution {
    private record Cell<R, C>(R row, C col) {};
    public void solve(char[][] board) {

        if(board == null || board.length == 0) return;

        int m = board.length;
        int n = board[0].length;

        Queue<Cell<Integer, Integer>> q = new ArrayDeque<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i==0 || j==0 ||( i==m-1)||(j==n-1)) {
                    if(board[i][j] == 'O') {
                        q.offer(new Cell<>(i,j));
                    }
                }

            }
        }

        int[] dirx = {-1, 0, 1, 0};
        int[] diry = {0, 1, 0, -1};

        while(!q.isEmpty()) {
            Cell cell = q.poll();
            int row = (Integer) cell.row;
            int col = (Integer) cell.col;
            board[row][col] = '#';

            for(int k = 0; k<4; k++) {
                int newx = row + dirx[k];
                int newy = col + diry[k];

                if(newx <= 0 || newy <= 0 || newx >= (m-1) || newy >= (n-1) || board[newx][newy] == 'X' || board[newx][newy] == '#') {
                    continue;
                }

                q.offer(new Cell<>(newx, newy));
            }
        }

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }

    }
}