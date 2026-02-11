class Solution {
    public boolean find132pattern(int[] nums) {
        int s3 = Integer.MIN_VALUE;

        Deque<Integer> st = new ArrayDeque<>();

        for(int i=nums.length-1; i>=0; i--) {
            if(s3 > nums[i]) {
                return true;
            }

            while(!st.isEmpty() && nums[i] > st.peek()) {
                s3 = st.pop();
            }
            st.push(nums[i]);
        }
        return false;
    }
}