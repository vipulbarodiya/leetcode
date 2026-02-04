class Solution {
    private class Edge{
        int to;
        int cost;
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private record Pair(int node, int dist){};
    public int networkDelayTime(int[][] times, int n, int k) {
        // create graph
        // create PQ with (node, dist)
        // create dist,
        // push start node in pq (k,0)
        //

        List<List<Edge>> graph = new ArrayList<>();
        // fill graph;
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<times.length; i++) {
            graph.get(times[i][0]).add(new Edge(times[i][1], times[i][2]));
        }
        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.dist, o2.dist);
            }
        });
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        boolean[] visited = new boolean[n+1];
        q.add(new Pair(k,0));
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            visited[curr.node] = true;
            for(Edge edge: graph.get(curr.node)) {
                if(visited[edge.to]) continue;
                int minValue = dist[edge.to];
                if(minValue < (curr.dist + edge.cost )) continue;
                dist[edge.to] = curr.dist + edge.cost;
                q.add(new Pair(edge.to, dist[edge.to]));
            }
        }
        int ans = Integer.MIN_VALUE;
        for(int i=1; i<=n; i++) {
            int distance = dist[i];
            if(distance == Integer.MAX_VALUE)
                return -1;
            ans = Math.max(distance, ans);
        }
        return ans;
    }
}