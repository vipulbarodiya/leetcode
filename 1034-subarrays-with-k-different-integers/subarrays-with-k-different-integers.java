class Solution {
    public int atMostK(int [] nums, int k) {
        Map<Integer, Integer> f = new HashMap<>();
        int left = 0;
        int right = 0;
        int ans = 0;
        int n = nums.length;
        while((right < n)) {
            if (f.getOrDefault(nums[right], 0) == 0) k--;
            f.put(nums[right], f.getOrDefault(nums[right], 0)+1);
            while(k<0) {
                f.put(nums[left], f.get(nums[left])-1);
                if(f.get(nums[left]) == 0) k++;
                left++;
            }
            ans+= (right - left +1);

            right++;
        }
        return ans;
    }
    public int subarraysWithKDistinct(int[] nums, int k) {
        int atMostK = atMostK(nums, k);
        int atMostK1 = atMostK(nums, k-1);
        return atMostK - atMostK1;
    }
}