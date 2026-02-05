class Solution {
    private record Edge(int to, int cost){};
    private record Pair(int node, int cost, int stops){};
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Edge>> g = new ArrayList<>();
        int[] stops = new int[n+1];
        int [] dist = new int[n+1];
        for(int i=0; i<=n; i++) {
            g.add(new ArrayList<>());
            stops[i] = Integer.MAX_VALUE;
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<flights.length; i++) {
            g.get(flights[i][0]).add(new Edge(flights[i][1], flights[i][2]));
        }
        if(k==0) {
            int ans = -1;
            for(Edge e: g.get(src)){
                if(e.to == dst){
                    ans = e.cost;
                }
            }
            return ans;
        }
        dist[src] = 0;
        stops[src] = 0;
        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>(){
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });
        q.add(new Pair(src, 0,0));
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            int cost = curr.cost;
            int stop = curr.stops;
            if(curr.node == dst) return cost;

            if(stop >k) continue;

            for(Edge e: g.get(curr.node)) {
                int v = e.to;
                int nextCost = e.cost + cost;
                int nextStops = stop+1;
                if( nextCost < dist[v] || nextStops < stops[v] ) {
                        dist[v] = nextCost;
                        stops[v] = nextStops;
                        q.add(new Pair(v, nextCost, nextStops));
                }
            }
        }
        return (dist[dst] != Integer.MAX_VALUE) ? dist[dst] :-1  ;
    }
}