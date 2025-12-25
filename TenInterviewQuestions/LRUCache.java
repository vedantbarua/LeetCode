import java.util.*;

/**
 * LRU Cache
 * DS: HashMap key->node + doubly linked list ordered by recency.
 * Algo: move accessed/updated node to head; evict tail on overflow.
 * Thought: list gives O(1) reordering/eviction; map gives O(1) lookup.
 * Time: O(1) get/put. Space: O(capacity).
 */
public class LRUCache {
    private static class Node {
        int key, val;
        Node prev, next;
        Node(int k, int v){ key = k; val = v; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0,0); // pseudo-head
    private final Node tail = new Node(0,0); // pseudo-tail

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail; tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            moveToHead(node);
        } else {
            Node fresh = new Node(key, value);
            map.put(key, fresh);
            addAfterHead(fresh);
            if (map.size() > capacity) {
                Node lru = popTail();
                map.remove(lru.key);
            }
        }
    }

    private void moveToHead(Node node) {
        remove(node);
        addAfterHead(node);
    }
    private void addAfterHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private Node popTail() {
        Node lru = tail.prev;
        remove(lru);
        return lru;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1
        cache.put(3, 3); // evicts key 2
        System.out.println(cache.get(2)); // -1
        cache.put(4, 4); // evicts key 1
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4
    }
}
