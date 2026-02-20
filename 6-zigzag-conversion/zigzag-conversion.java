class Solution {
    public String convert(String s, int numRows) {
        int n = s.length();
        if(numRows == 1) {
            return s;
        }
        List<List<Character>> rows= new ArrayList<>();
        for(int i=0; i<numRows; i++) {
            rows.add(new ArrayList<>());
        }
        boolean d = true;
        int row = 0;
        for(int i=0; i<n; i++) {
            rows.get(row).add(s.charAt(i));
            row = row + ((d)?1:-1);
            if(row == numRows-1) {
                d = false;
            } else if(row ==0){
                d = true;
            }

        }
        StringBuilder sb = new StringBuilder();
        for(List<Character> x: rows) {
            for(char c: x) {
                sb.append(c);
            }
        }
        return sb.toString();

    }
}