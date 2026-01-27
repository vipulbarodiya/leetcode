class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n ==1) return List.of(0);
        List<List<Integer>> g = new ArrayList<>();
        for(int i=0; i<n; i++) {
            g.add(new ArrayList<>());
        }
        int [] in = new int[n];
        for(int[] edge: edges) {
            g.get(edge[0]).add(edge[1]);
            g.get(edge[1]).add(edge[0]);
            in[edge[0]]++;
            in[edge[1]]++;
        }

        Queue<Integer> q =  new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<n; i++) {
                if(in[i] == 1){
                    q.add(i);
                }
        }
        int remaining = n;
        while(remaining > 2) {
            int currLeaves = q.size();
            remaining -= currLeaves;
            for(int s =0; s<currLeaves; s++) {
                int node = q.poll();
                for(int v: g.get(node)){
                    in[v]--;
                    if(in[v] == 1){
                        q.add(v);
                    }
                }
            }
        }

        return new ArrayList<Integer>(q);
    }
}