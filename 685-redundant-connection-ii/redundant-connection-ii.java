class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] edge1 = null;
        int[] edge2 = null;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (parent[v] == 0) {
                parent[v] = u;
            } else {
                edge1 = new int[]{parent[v], v};
                edge2 = new int[]{u, v};
                break;
            }
        }

        if (edge1 == null) {
            return findCycle(edges, null, n);
        } else {
            int[] cycleEdge = findCycle(edges, edge2, n);
            if (cycleEdge == null) {
                return edge2;
            } else {
                return edge1;
            }
        }
    }

    private int[] findCycle(int[][] edges, int[] skipEdge, int n) {
        UnionFind uf = new UnionFind(n + 1);
        for (int[] edge : edges) {
            if (skipEdge != null && edge[0] == skipEdge[0] && edge[1] == skipEdge[1]) {
                continue;
            }
            if (!uf.union(edge[0], edge[1])) {
                return edge; 
            }
        }
        return null;
    }

    class UnionFind {
        int[] root;
        public UnionFind(int size) {
            root = new int[size];
            for (int i = 0; i < size; i++) root[i] = i;
        }

        public int find(int i) {
            if (root[i] == i) return i;
            return root[i] = find(root[i]); // Path compression
        }

        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                root[rootI] = rootJ;
                return true;
            }
            return false;
        }
    }
}