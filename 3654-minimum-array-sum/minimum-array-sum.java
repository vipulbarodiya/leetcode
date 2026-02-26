class Solution {
    Integer[][][] memo;
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        int n = nums.length;
        memo = new Integer[n][op1 + 1][op2 + 1];
        return solve(0, op1, op2, nums, k);
    }

    public int solve(int i, int o1, int o2, int[] nums, int k ){
        if (i == nums.length) {
            return 0;
        }

        if (memo[i][o1][o2] != null) {
            return memo[i][o1][o2];
        }

        int val = nums[i];

        int res = val + solve(i + 1, o1, o2, nums, k);

        if (o1 > 0) {
            int divided = (val + 1) / 2;
            res = Math.min(res, divided + solve(i + 1, o1 - 1, o2, nums, k));
        }

        if (o2 > 0 && val >= k) {
            res = Math.min(res, (val - k) + solve(i + 1, o1, o2 - 1, nums, k));
        }

        if (o1 > 0 && o2 > 0) {
            int divided = (val + 1) / 2;
            if (divided >= k) {
                res = Math.min(res, (divided - k) + solve(i + 1, o1 - 1, o2 - 1, nums, k));
            }

            if (val >= k) {
                int afterSub = val - k;
                int afterBoth = (afterSub + 1) / 2;
                res = Math.min(res, afterBoth + solve(i + 1, o1 - 1, o2 - 1, nums, k));
            }
        }

        return memo[i][o1][o2] = res;
    }
}