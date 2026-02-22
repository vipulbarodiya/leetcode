class LFUCache {
    class Node{
        int key;
        int value;
        int frequency;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    HashMap<Integer, LinkedHashSet<Node>> fmap;
    HashMap<Integer, Node> cacheMap;
    int capacity;
    int minFrequency = 1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.fmap = new HashMap<>();
    }

    public int get(int key) {
        if(!cacheMap.containsKey(key)){
            return -1;
        }
        Node node = cacheMap.get(key);
        int value = node.value;
        updateFrequency(node);
        return value;
    }

    public void put(int key, int value) {
        if(capacity == 0) {
            return;
        }

        // evict min freqNode

        if(cacheMap.containsKey(key)) {
            Node node = cacheMap.get(key);
            node.value = value;
            updateFrequency(node);
            return;
        } else if(cacheMap.size() >= capacity) {
            LinkedHashSet minFNodes = this.fmap.get(minFrequency);
            Node lruNode = (Node) minFNodes.iterator().next();
            minFNodes.remove(lruNode);
            cacheMap.remove(lruNode.key);
            if(minFNodes.isEmpty()) {
                this.minFrequency++;
            }
        }
        Node node = new Node(key, value);
        this.cacheMap.put(key, node);
        this.fmap.computeIfAbsent(node.frequency, x->new LinkedHashSet<Node>()).add(node);
        this.minFrequency = 1;

    }

    public void updateFrequency(Node node) {
        LinkedHashSet<Node> fNodes = fmap.get(node.frequency);
        int oldFrequency = node.frequency;
        fNodes.remove(node);
        if(oldFrequency == minFrequency && fNodes.isEmpty()) {
            this.minFrequency++;
        }
        node.frequency++;
        fmap.computeIfAbsent(node.frequency, x->new LinkedHashSet<>()).add(node);
    }
}