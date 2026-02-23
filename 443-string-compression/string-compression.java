class Solution {
    public int compress(char[] chars) {
        int read = 0, write=0;
        int n = chars.length;
        while(read < n) {
            char cur = chars[read];
            int count = 0;
            while(read <n && chars[read]==cur) {
                read++;
                count++;
            }
            chars[write++] = cur;
            if(count>1) {
                for(char ch: String.valueOf(count).toCharArray()) {
                    chars[write++] = ch;
                }
            }
        }
        return write;
    }
}