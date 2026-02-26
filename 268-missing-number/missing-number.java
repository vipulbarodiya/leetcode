class Solution {
    public int missingNumber(int[] nums) {
        int total = 1;
        int local = 1;
        int n = nums.length;
        for(int i=0; i<n; i++) {
            total = total^i;
            local = local^nums[i];
        }
        total=total^n;
        return local^total;
    }
}