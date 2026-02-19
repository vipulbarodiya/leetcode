class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int m = firstList.length;
        int n = secondList.length;
        List<int[]> ans = new ArrayList<>();
        int i =0, j=0;
        while(i <m && j<n ) {
            if((firstList[i][1] >= secondList[j][0] )&& (firstList[i][0] <= secondList[j][1])) {
                int start = Math.max(firstList[i][0], secondList[j][0]);
                int end = Math.min(firstList[i][1], secondList[j][1]);
                ans.add(new int[]{start, end});

            }
            if(firstList[i][1] <= secondList[j][1]){
                i++;
            } else {
                j++;
            }


        }
        return ans.stream().toArray(int[][]::new);

    }
}