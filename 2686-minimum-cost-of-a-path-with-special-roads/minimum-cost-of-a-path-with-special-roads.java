class Solution {
    public record Point(int x, int y){};

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        Point startP = new Point(start[0], start[1]);
        Point endP = new Point(target[0], target[1]);
        Map<Point, Integer> distances = new HashMap<>();
        distances.put(startP, 0);
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        q.add(new int[]{0,startP.x, startP.y});

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int d = curr[0];
            Point currP = new Point(curr[1], curr[2]);
            if(d >  distances.getOrDefault(currP, Integer.MAX_VALUE)) continue;

            int distToTarget = d + Math.abs(currP.x - endP.x) + Math.abs(currP.y - endP.y);
            if(distToTarget < distances.getOrDefault(endP, Integer.MAX_VALUE)){
                distances.put(endP, distToTarget);
                q.offer(new int[]{ distToTarget, endP.x, endP.y});
            }

            for(int[] road: specialRoads) {
                int x1= road[0], y1 = road[1], x2=road[2], y2 = road[3], cost = road[4];
                int costViaRoad = d + Math.abs(x1-currP.x) + Math.abs(y1-currP.y) + cost;
                Point roadEnd = new Point(x2,y2);
                if(costViaRoad < distances.getOrDefault(roadEnd, Integer.MAX_VALUE)) {
                    distances.put(roadEnd, costViaRoad);
                    q.offer(new int[]{ costViaRoad, x2, y2});
                }
            }

        }
        return distances.get(endP);

    }
}