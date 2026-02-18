class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> ansList = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for(int i=0; i<n-1; i++) {
            int[] curInterval  = intervals[i];
            if(ansList.isEmpty()) {
                ansList.add(curInterval);
            } else {
                int[] lastInterval = ansList.removeLast();
                if(lastInterval[1] >= curInterval[0]) {
                    int[] mergedInterval = new int[]{ Math.min(lastInterval[0], curInterval[0]), Math.max(lastInterval[1], curInterval[1])};
                    ansList.add(mergedInterval);
                } else {
                    ansList.add(lastInterval);
                    ansList.add(curInterval);
                }
            }

        }
        if(ansList.isEmpty()) {
            return (n==0)?(new int[][]{}): intervals;
        }
        int[] lastInterval = ansList.removeLast();
        int[] curInterval = intervals[n-1];
        if(lastInterval[1] >= curInterval[0]) {
            int[] mergedInterval = new int[]{ Math.min(lastInterval[0], curInterval[0]), Math.max(lastInterval[1], curInterval[1])};
            ansList.add(mergedInterval);
        } else {
            ansList.add(lastInterval);
            ansList.add(curInterval);
        }
        return ansList.toArray(new int[ansList.size()][]);

    }
}