class Solution {
    class UnionFind {
        int size;
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            size = n;
            parents = new int[n];
            ranks = new int[n];
            for(int i =0; i<n; i++) {
                parents[i] = i;
                ranks[i] = 0;
            }
        }

        public int find(int a) {
            if (parents[a] == a) {
                return a;
            }
            // Path Compression: recursively find the root and
            // make it the direct parent of current node 'a'
            return parents[a] = find(parents[a]);
        }

        public boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);

            if(pa == pb) {
                return true;
            }
            if(ranks[a] < ranks[b]) {
                parents[pa] = pb;
            } else if(ranks[b] < ranks[a]){
                parents[pb] = pa;
            } else  {
                ranks[a]++;
                parents[pb] = pa;
            }
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