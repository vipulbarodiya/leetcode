class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();
        for(int[] building: buildings) {
            events.add(new int[]{building[0], 0 - building[2]});
            events.add(new int[]{building[1], building[2]});
        }
        events.sort((a,b) -> {
            if(a[0]!=b[0]) return a[0]-b[0];
            return a[1]-b[1];
        });

        int lastMax  = -1;
        List<List<Integer>> ans = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);

        for(int[] event: events) {
            int key = event[0];
            int h = Math.abs(event[1]);

            if(event[1] < 0) {
                map.put(h,map.getOrDefault(h,0)+1);
            } else {
                int cf = map.get(h);
                if(cf ==1){
                    map.remove(h);
                } else {
                    map.put(h, cf-1);
                }
            }

            int curMax = map.lastKey();

            if(curMax!=lastMax) {
                ans.add(List.of(key,curMax));
                lastMax = curMax;
            }
        }

        return ans;
    }
}