class NumArray {

    private int[] presum;
    public NumArray(int[] nums) {
        presum = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            if(i==0) {
                presum[i] = nums[i];
            } else {
                presum[i] = presum[i-1] + nums[i];
            }
        }
    }
    
    public int sumRange(int left, int right) {
        if(left!=0)
        return presum[right] - presum[left-1];
        else return presum[right];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */