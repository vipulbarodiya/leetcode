class Solution {
    public String convert(String s, int numRows) {
        int n = s.length();
        if(numRows == 1) {
            return s;
        }
        StringBuilder[] rows= new StringBuilder[numRows];
        for(int i=0; i<numRows; i++) {
            rows[i] = new StringBuilder();
        }
        boolean d = true;
        int row = 0;
        for(int i=0; i<n; i++) {
            (rows[row]).append(s.charAt(i));
            row = row + ((d)?1:-1);
            if(row == numRows-1) {
                d = false;
            } else if(row ==0){
                d = true;
            }

        }
        StringBuilder sb = new StringBuilder();
        for(StringBuilder x: rows) {
            sb.append(x.toString());
        }
        return sb.toString();

    }
}