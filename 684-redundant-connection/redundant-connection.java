class Solution {

    class UnionFind {
        int size;
        int[] parents;

        public UnionFind(int n) {
            size = n;
            parents = new int[n];
            for(int i =0; i<n; i++) {
                parents[i] = i;
            }
        }

        public int find(int a) {
            while(parents[a] !=a) {
                a = parents[a];
            }
            return a;
        }

        public boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);

            if(pa == pb) {
                return true;
            }
            parents[pb] = pa;
            return false;
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length);
        for(int[] edge: edges) {
            if(unionFind.union(edge[0]-1, edge[1]-1)){
                return edge;
            }
        }
        return new int[]{};
    }
}