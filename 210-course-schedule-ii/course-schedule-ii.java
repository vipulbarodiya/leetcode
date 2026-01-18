class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];

        List<List<Integer>> g = new ArrayList<>();
        for(int i=0; i<numCourses; i++) {
            g.add(new ArrayList<>());
        }

        for(int[] pr: prerequisites) {
            in[pr[1]]++;
            g.get(pr[0]).add(pr[1]);
        }
        Queue<Integer> q =  new ArrayDeque<>();
        for(int i=0; i<numCourses; i++) {
            if(in[i] == 0) {
                q.offer(i);
            }
        }
        int processed  = 0;
        List<Integer> result = new ArrayList<>();
        while(!q.isEmpty()) {
            Integer c = q.poll();
            processed++;
            for(int n:g.get(c)) {
                in[n]--;
                if(in[n] == 0) {
                    q.offer(n);
                }
            }
            result.add(c);
        }
        if(processed!=numCourses) return new int[]{};
        result = result.reversed();
        return result.stream().mapToInt(i->i).toArray();
    }
}