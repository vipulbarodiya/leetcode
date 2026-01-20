class Solution {
    public String alienOrder(String[] words) {
        if(words == null || words.length == 0) return "";
        Map<Character, List<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        for(String word: words) {
            for(Character c: word.toCharArray()) {
                adj.putIfAbsent(c, new ArrayList<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        for(int i=0; i<words.length-1; i++) {
            String first = words[i];
            String second = words[i+1];

            if(first.length() >  second.length() && first.startsWith(second)) {
                return "";
            }

            int l = Math.min(first.length(), second.length());
            for(int j=0; j<l; j++) {
                Character c1 = first.charAt(j);
                Character c2 = second.charAt(j);

                if(c1!=c2) {
                    adj.get(c1).add(c2);
                    indegree.put(c2, indegree.get(c2)+1);
                    break;
                }
            }
        }

        Queue<Character> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for(Character c: indegree.keySet()) {
            if(indegree.get(c) == 0){
                q.offer(c);
            }
        }

        while(!q.isEmpty()) {
            Character c = q.poll();
            for(Character k: adj.get(c)) {
                indegree.put(k, indegree.get(k)-1);
                if(indegree.get(k) == 0) {
                    q.offer(k);
                }
            }
            sb.append((char)(c));
        }
        return sb.length() < indegree.size() ?"": sb.toString();
    }
}