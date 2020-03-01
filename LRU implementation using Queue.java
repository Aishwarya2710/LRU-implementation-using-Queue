public class LRU_cache_queue 
{
    int data;
    int capacity = 3;
    LRU_cache_queue prev;
    LRU_cache_queue next;
    LRU_cache_queue(int val)
    {
        this.data = val;
        this.next = null;
    }
    LRU_cache_queue enqueue(int val,int count,LRU_cache_queue root)
    {
        LRU_cache_queue t = root;
        // To count the total number of elements in queue
        while(t.next != null)
        {
            count++;
            t = t.next;
        }
        if(count>=capacity)
            this.dequeue();   // Removes the last element if count exceeds capacity
        LRU_cache_queue temp = new LRU_cache_queue(val);
        temp.prev = null;
        temp.next = root;
        root.prev = temp;
        return temp;
    }
    void dequeue()
    {
        if(this.next.next != null)
            this.next.dequeue();
        else
            this.next = null;
    }
    LRU_cache_queue doesExist(int val)
    {
        LRU_cache_queue exist;
        if(this.data != val)
        {
            if(this.next!=null)
                exist = this.next.doesExist(val);
            else
                return null;  //If element doesn't exist
        }
        else
            return this;      //If element exists, return it's position
        return exist;
    }
    LRU_cache_queue fetch(int val,LRU_cache_queue root)
    {
        LRU_cache_queue exist = root.doesExist(val);
        if(exist != null)                     // If element exists
            exist.prev.next = exist.next;     // Attaching next node of element to the previou node of element
        root = root.enqueue(val, 1, root);    // If element doesn't exist, insert at beginning
        return root;
    }
    void display()
    {
        System.out.println(this.data);
        if(this.next != null)
            this.next.display();
    }
    public static void main(String[] args)
    {
        LRU_cache_queue root = new LRU_cache_queue(10);
        root.prev = null;
        root = root.enqueue(20, 1, root);
        root = root.enqueue(30, 1, root);
        root = root.fetch(40, root);
        root.display();
    }
}
