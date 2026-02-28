class Solution {
        public int hammingWeight(int n) {
        long  c = 0;
        while(n>0){
            c = c + (n&1);
            n = n>>1;
        }
        return (int)c;
    }
}