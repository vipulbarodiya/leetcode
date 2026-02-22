class Solution {
    public void dfs(String start, Map<String, PriorityQueue<String>> adjList, List<String> result) {
        PriorityQueue<String> arrivals = adjList.get(start);
        while ((arrivals!=null) && !(arrivals.isEmpty())) {
            String dest = arrivals.poll();
            dfs(dest, adjList, result);
        }
        result.addFirst(start);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        if(tickets==null || tickets.isEmpty()) return List.of();
        List<String> result = new LinkedList<>();
        Map<String, PriorityQueue<String>> adjList = new HashMap<>();
        for(List<String> ticket: tickets) {
            adjList.computeIfAbsent(ticket.get(0), x->new PriorityQueue<>()).add(ticket.get(1));
        }

        dfs("JFK", adjList, result);
        return result;
    }
}