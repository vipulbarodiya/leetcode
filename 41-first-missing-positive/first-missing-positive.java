class Solution {
    private void swap(int i, int j, int[] nums) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i=1; i<n;) {
            if((nums[i] <= n) && (nums[i] >=1)){
                if(nums[i]!=(i+1) && nums[i]!=nums[nums[i]-1]) {
                    swap(i, nums[i]-1, nums);
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
        for(int i=0; i<n; i++) {
            if(nums[i]!=(i+1)) {
                return i+1;
            }
        }
        return n+1;
    }
}