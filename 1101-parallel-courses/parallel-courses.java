class Solution {

    public int minimumSemesters(int n, int[][] relations) {
        if(n == 1) return 1;
        List<List<Integer>> g = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            g.add(new ArrayList<>());
        }
        int [] in = new int[n+1];
        for(int[] relation: relations) {
            g.get(relation[1]).add(relation[0]);
            in[relation[0]]++;
        }
        Set<Integer> v = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();

        for (int i=1; i<=n; i++) {
            if(in[i] == 0) {
                q.offer(i);
            }
        }
        int dist = 0;
        while(!q.isEmpty()) {
            int s = q.size();
            for(int i = 0; i<s; i++) {
                Integer curr = q.poll();
                v.add(curr);
                for(int x: g.get(curr)) {
                    in[x] --;
                    if(in[x] == 0){
                        q.add(x);
                    }
                }
            }
            dist++;
        }
        if(v.size()  < n) return -1;

        return dist;
    }
}