class Solution {
    public int maxPalindromesAfterOperations(String[] words) {
        int n = words.length;
        int[] a = new int[n];
        Map<Character, Integer> fmap = new HashMap<>();
        for(String word: words) {
            for(char c: word.toCharArray()) {
                fmap.put(c, fmap.getOrDefault(c,0)+1);
            }
        }
        for(int i=0; i<n; i++) {
            a[i] = words[i].length();
        }
        int pairs = 0;
        Arrays.sort(a);
        for(Map.Entry<Character, Integer> entry: fmap.entrySet()) {
            pairs += entry.getValue()/2;
        }
        for(int i=0; i<n; i++) {
            pairs = pairs - a[i]/2;
            if(pairs < 0) return i;
        }
        return n;
    }
}