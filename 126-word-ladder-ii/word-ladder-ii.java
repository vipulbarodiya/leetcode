class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;

        // Step 1: BFS from endWord to find distances to the goal
        Map<String, Integer> distanceMap = new HashMap<>();
        bfs(endWord, beginWord, dict, distanceMap);

        // Step 2: DFS from beginWord to collect paths
        if (distanceMap.containsKey(beginWord)) {
            dfs(beginWord, endWord, distanceMap, new ArrayList<>(Arrays.asList(beginWord)), res, dict);
        }

        return res;
    }

    private void bfs(String start, String target, Set<String> dict, Map<String, Integer> distanceMap) {
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        distanceMap.put(start, 0);

        while (!q.isEmpty()) {
            String word = q.poll();
            int dist = distanceMap.get(word);
            
            // Optimization: If we found beginWord, we don't need to look further than its level
            if (word.equals(target)) return;

            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char old = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String next = new String(chars);
                    if (dict.contains(next) || next.equals(target)) {
                        if (!distanceMap.containsKey(next)) {
                            distanceMap.put(next, dist + 1);
                            q.offer(next);
                        }
                    }
                }
                chars[i] = old;
            }
        }
    }

    private void dfs(String curr, String target, Map<String, Integer> distanceMap, 
                     List<String> path, List<List<String>> res, Set<String> dict) {
        if (curr.equals(target)) {
            res.add(new ArrayList<>(path));
            return;
        }

        int currDist = distanceMap.get(curr);
        char[] chars = curr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char old = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String next = new String(chars);
                // ONLY follow the path if it leads closer to the target
                if (distanceMap.containsKey(next) && distanceMap.get(next) == currDist - 1) {
                    path.add(next);
                    dfs(next, target, distanceMap, path, res, dict);
                    path.remove(path.size() - 1);
                }
            }
            chars[i] = old;
        }
    }
}