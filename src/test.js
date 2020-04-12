
/*
 * Complete the function below.
 */
static Map<Integer, DLLNode> cache;
static int maxCap;
static int currCap;
static DLLNode head;
static DLLNode tail;

static int[] implement_LRU_cache(int capacity, int[] query_types, int[] keys, int[] values) {

    init(capacity);
    List<Integer> list = new ArrayList<>();

    for(int i=0; i<query_types.length; i++) {
        if(query_types[i] == 0) {
            list.add(get(keys[i]));
        } else {
            put(keys[i], values[i]);
        }
    }

    int[] result = new int[list.size()];
    for(int j=0; j<list.size(); j++) {
        result[j] = list.get(j);
    }
    return result;
}

static void init(int capacity) {
    cache = new HashMap<>();
    maxCap = capacity;
    currCap = 0;
}

static int get(int key) {
    if(cache.containsKey(key)) {
        DLLNode node = cache.get(key);
        unlink(node);
        moveFront(node);
        return node.value;
    }
    return -1;
}

static void put(int key, int value) {
    if(cache.containsKey(key)) {
        DLLNode node = cache.get(key);
        unlink(node);
        moveFront(node);
        node.value = value;
        return;
    }
    if(currCap == maxCap) {
        evict();
    }
    currCap++;
    DLLNode node = new DLLNode(key, value);
    moveFront(node);
    cache.put(key, node);
}

static void evict() {
    if(tail == null)
        return;
    cache.remove(tail.key);
    if(head == tail) {
        head = null;
        tail = null;
        currCap--;
        return;
    }
    DLLNode newTail = tail.prev;
    tail.prev = null;
    if(newTail != null) {
        newTail.next = null;
    }
    tail = newTail;
    currCap--;
}

static void unlink(DLLNode node) {
    DLLNode p = node.prev;
    DLLNode n = node.next;

    if(p != null) {
        p.next = n;
    }
    if(n != null) {
        n.prev = p;
    }

    if(node == tail) {
        tail = tail.prev;
    }
    if(node == head) {
        head = head.next;
    }

    node.next = null;
    node.prev = null;

}

static void moveFront(DLLNode node) {
    if(head == null) {
        head = node;
        tail = node;
        return;
    }
    node.next = head;
    head.prev = node;
    head = node;
}

//MAP DLL
static class DLLNode {
    int key;
    int value;
    DLLNode prev;
    DLLNode next;
    DLLNode(int k, int v) {
    key = k;
    value = v;
}
}





