class Solution {
    public int minOperations(int n) {
        int operationCount = 0;
        int consecutiveOnes = 0;

        while (n > 0) {
            if ((n & 1) == 1) {
                consecutiveOnes++;
            } else if (consecutiveOnes > 0) {
                operationCount++;
                consecutiveOnes = (consecutiveOnes == 1) ? 0 : 1;
            }
            n >>= 1;
        }

        if (consecutiveOnes == 1) {
            operationCount += 1;
        } else if (consecutiveOnes > 1) {
            operationCount += 2;
        }

        return operationCount;
    }
}