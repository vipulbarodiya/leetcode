class Solution {
    public int secondsToRemoveOccurrences(String s) {
        int n = s.length();
        int[] dist = new int[n];
        int prev_Ones =  0;
        int prev_zeroes = 0;
        for(int i=0; i<n; i++) {
            if(i==0) {
                if(s.charAt(i) == '0'){
                    prev_zeroes++;
                } else {
                    prev_Ones = 0;
                }
                continue;
            }
            if(s.charAt(i) == '0'){
                prev_zeroes++;
            } else {
                if(prev_zeroes == 0){
                    dist[i] = 0;
                    prev_Ones = dist[i];
                } else {
                    prev_Ones = Math.max(prev_Ones+1, prev_zeroes);
                    dist[i] = prev_Ones;
                }
            }
        }
        return prev_Ones;
    }
}