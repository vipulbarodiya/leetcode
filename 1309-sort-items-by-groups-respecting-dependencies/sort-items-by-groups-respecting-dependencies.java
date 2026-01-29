class Solution {
    List<Integer> topoSort(int n, List<List<Integer>> graph, int[] indegree) {
        int c = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for(int i =0; i<n; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while(!q.isEmpty()) {
            Integer curr = q.poll();
            c++;
            res.add(curr);
            for(int v: graph.get(curr)) {
                indegree[v] --;
                if(indegree[v] == 0) {
                    q.add(v);
                }
            }
        }
        if(c  < n) return new ArrayList<>();
        return res;
    }
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int [] res = new int[n];
        List<List<Integer>> gi = new ArrayList<>();
        List<List<Integer>> gg = new ArrayList<>();
        int j = m;
        for(int i=0; i<n; i++) {
            gi.add(new ArrayList<>());
            gg.add(new ArrayList<>());
            if(group[i] == -1){
                group[i] = j;
                j++;
            }
        }
        for(int i=0; i<m; i++) {
            gg.add(new ArrayList<>());
        }
        int[] itemInDegree = new int[n];
        int[] groupInDegree = new int[m+n];
        for(int i=0; i<n; i++) {
            for(int v: beforeItems.get(i)) {
                gi.get(v).add(i);
                itemInDegree[i]++; // Track item dependencies

                if(group[v] != group[i]){
                    gg.get(group[v]).add(group[i]);
                    groupInDegree[group[i]]++; // Track group dependencies
                }
            }
        }
        List<Integer> sortedItems = topoSort(n, gi, itemInDegree);
        List<Integer> sortedGroups = topoSort(j, gg, groupInDegree);

        if(sortedGroups.isEmpty() || sortedItems.isEmpty()) return new int[]{};

        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for(Integer item: sortedItems) {
            groupToItems.computeIfAbsent(group[item], k-> new ArrayList<>()).add(item);
        }
        int idx = 0;
        for(Integer groupId: sortedGroups) {
           List<Integer> groupItems = groupToItems.getOrDefault(groupId, new ArrayList<>());
           for(Integer item: groupItems){
               res[idx++] = item;
           }
        }
        return res;
    }
}