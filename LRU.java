class Node{
    
    int key, val;
    Node prev, next;
    
    public Node(int key, int val)
    {
        this.key = key;
        this.val = val;
        prev = null;
        next = null;
    }
}

class DoubleLinkedList{
    
    Node head, tail;
    
    public DoubleLinkedList()
    {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
    }
    
    public void addLast(Node n)
    {
        n.prev = tail.prev;
        n.next = tail;
        tail.prev.next = n;
        tail.prev = n;
    }
    
    public void remove(Node n)
    {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
    
    public Node removeFirst()
    {
        if (head.next==tail)
            return null;
        Node first = head.next;
        remove(first);
        return first;
    }
}

class LRUCache {
    
    int cap;
    HashMap<Integer, Node> map;
    DoubleLinkedList cacheList;

    public LRUCache(int capacity) {
        
      cap = capacity;
      cacheList = new DoubleLinkedList();
      map = new HashMap<Integer, Node>();
        
    }
    
    public void markMostRecently(int key){
        
        Node x = map.get(key);
        cacheList.remove(x);
        cacheList.addLast(x);
    }
    
    public void removeLeastRecently(){
        
        Node x = cacheList.removeFirst();
        int deletedkey = x.key;
        map.remove(deletedkey);   
    
    }
    
    public int get(int key) {
      
        if(!map.containsKey(key))
            return -1;
        markMostRecently(key);
        return map.get(key).val;
            
    }
    
    public void addRecently(int key, int val)
    {
        Node newNode = new Node(key, val);
        cacheList.addLast(newNode);
        map.put(key, newNode);
    }
    
    public void deleteKey(int key)
    {
        Node x = map.get(key);
        cacheList.remove(x);
        map.remove(key);
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key))
        {
            deleteKey(key);
            addRecently(key, value);
            return;
        }
        if (cap == map.size())
            removeLeastRecently();
        addRecently(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
