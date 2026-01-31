class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        if (k == 1) return new int[][]{{1}};

        int[][] matrix = new int[k][k];

        List<List<Integer>> rows = new ArrayList<>();
        List<List<Integer>> cols = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            rows.add(new ArrayList<>());
            cols.add(new ArrayList<>());
        }

        int[] rowInDegree = new int[k + 1];
        int[] colInDegree = new int[k + 1];

        for (int[] e : rowConditions) {
            rows.get(e[0]).add(e[1]);
            rowInDegree[e[1]]++;
        }
        for (int[] e : colConditions) {
            cols.get(e[0]).add(e[1]);
            colInDegree[e[1]]++;
        }

        Queue<Integer> rowQ = new ArrayDeque<>();
        Queue<Integer> colQ = new ArrayDeque<>();
        for (int i = 1; i <= k; i++) {
            if (rowInDegree[i] == 0) rowQ.offer(i);
            if (colInDegree[i] == 0) colQ.offer(i);
        }

        List<Integer> rowOrder = new ArrayList<>();
        List<Integer> colOrder = new ArrayList<>();

        while (!rowQ.isEmpty()) {
            int u = rowQ.poll();
            rowOrder.add(u);
            for (int v : rows.get(u)) {
                if (--rowInDegree[v] == 0) rowQ.add(v);
            }
        }

        while (!colQ.isEmpty()) {
            int u = colQ.poll();
            colOrder.add(u);
            for (int v : cols.get(u)) {
                if (--colInDegree[v] == 0) colQ.add(v);
            }
        }

        if (rowOrder.size() != k || colOrder.size() != k) {
            return new int[0][0];
        }

        int[] rowPos = new int[k + 1];
        int[] colPos = new int[k + 1];
        for (int i = 0; i < k; i++) {
            rowPos[rowOrder.get(i)] = i;
            colPos[colOrder.get(i)] = i;
        }


        for (int val = 1; val <= k; val++) {
            matrix[rowPos[val]][colPos[val]] = val;
        }

        return matrix;
    }
}