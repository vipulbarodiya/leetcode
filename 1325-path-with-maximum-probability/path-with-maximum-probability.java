class Solution {
    private record Pair(int node, double prob){};
    private record Edge(int to, double prob){};

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<List<Edge>> graph = new ArrayList<>();
        double[] probs = new double[n];
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
            probs[i] = 0.0;
        }
        for(int i=0; i<edges.length; i++) {
            graph.get(edges[i][0]).add(new Edge(edges[i][1], succProb[i]));
            graph.get(edges[i][1]).add(new Edge(edges[i][0], succProb[i]));

        }
        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>(){
            @Override
            public int compare(Pair o1, Pair o2) {
                return Double.compare(o2.prob, o1.prob);
            }
        });
        q.add(new Pair(start_node, 1.0));
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            visited[curr.node] = true;
            double prob = curr.prob;
            for(Edge e: graph.get(curr.node)) {
                if(visited[e.to]) continue;
                double maxProb = probs[e.to];
                if(maxProb > (e.prob*prob)) continue;
                probs[e.to] = e.prob*prob;
                q.add(new Pair(e.to, probs[e.to]));
            }
        }

        return probs[end_node] == Double.NEGATIVE_INFINITY? -1 : probs[end_node];
    }
}