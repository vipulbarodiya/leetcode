class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(beginWord.length()!=endWord.length()) return 0;
        int l = beginWord.length();
        List<List<String>> g = new ArrayList<>();
        Map<String,Integer> dictionary = new HashMap<>(wordList.stream().collect(Collectors.toMap(Function.identity(),s->1)));
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.offer(beginWord);
        visited.add(beginWord);
        int dist = 1;
        StringBuilder curr = new StringBuilder();
        String currWord = null;
        while(!q.isEmpty()) {
            for(int s = q.size(); s>0; s--) {
                currWord = q.poll();
                curr.append(currWord);
                for(int i=0; i<currWord.length(); i++) {
                    for(int j = 0; j<26; j++) {
                        curr.setCharAt(i, (char)('a'+j));
                        String newWord = curr.toString();
                        if(newWord.equals(endWord) && dictionary.containsKey(newWord)) {
                            return dist+1;
                        }
                        if( dictionary.containsKey(newWord) && !visited.contains(newWord) ) {
                            // add node;
                            q.offer(newWord);
                            visited.add(newWord);
                        }

                    }
                    curr.setCharAt(i, currWord.charAt(i));
                }
                curr.setLength(0);
            }
                dist++;

        }

        return 0;
    }
}