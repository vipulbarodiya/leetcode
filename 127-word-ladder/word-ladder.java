class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(beginWord.length()!=endWord.length()) return 0;
        Set<String> dict = new HashSet<>(wordList);
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        q.offer(beginWord);
        visited.add(beginWord);

        int dist = 1;

        String s = null;
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            for(int sz = q.size(); sz>0; sz--){
                s = q.poll();
                sb.append(s);
                for(int i=0; i<s.length(); i++) {
                    for(int j=0; j<26; j++) {
                        sb.setCharAt(i, (char) ('a' + j));
                        String currWord = sb.toString();
                        if (currWord.equals(endWord) && dict.contains(currWord)) {
                            return dist+1;
                        }
                        if (dict.contains(currWord) && !visited.contains(currWord)) {
                            visited.add(currWord);
                            q.add(currWord);
                        }
                    }
                    sb.setCharAt(i, s.charAt(i));
                }
                sb.setLength(0);
            }
            dist++;
        }
        return 0;
    }
}