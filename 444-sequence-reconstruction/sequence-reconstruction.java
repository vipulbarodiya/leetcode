class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        int n = nums.length;
        List<List<Integer>> g = new ArrayList<>(n);
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < sequences.size(); i++) {
            List<Integer> sequence = sequences.get(i);
            for (int j = 0; j < sequence.size() - 1; j++) {
                g.get(sequence.get(j)).add(sequence.get(j + 1));
            }
        }

        List<Integer> topo = new ArrayList<>();
        int[] in = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j : g.get(i)) {
                in[j]++;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            if (q.size() > 1) {
                return false;
            }
            int u = q.poll();
            topo.add(u);
            for (int v : g.get(u)) {
                in[v]--;
                if (in[v] == 0) {
                    q.add(v);
                }
            }
        }
        return topo.equals(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }
}