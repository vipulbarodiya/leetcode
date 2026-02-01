
class Solution {

    int maxPath = 1;

    public int dfs(int u, List<List<Integer>> g, String s) {
        int x=0,y=0;
        for(int v: g.get(u)) {
            int l = dfs(v,g,s);

            if(s.charAt(u)!=s.charAt(v)) {
                if(l>= x) {
                    y = x;
                    x = l;
                }else if(l>y) {
                    y = l;
                }
            }
        }
        maxPath = Math.max(maxPath, x+y+1);

        return x+1;
    }

    public int longestPath(int[] parent, String s) {
        maxPath = 1;
        int n = parent.length;
        if(n==1) return 1;
       List<List<Integer>> g = new ArrayList<>();
       for(int i = 0; i<n; i++) {
           g.add(new ArrayList<>());
       }
       for(int i=0; i<n; i++){
           if(parent[i]!=-1) {
               g.get(parent[i]).add(i);
           }
       }
       dfs(0, g,s);
       return maxPath;

    }
}