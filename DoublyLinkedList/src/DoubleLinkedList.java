public class DoubleLinkedList<T extends Comparable>{
    private Node<T> head;
    public class Node<T extends Comparable>{
        T data;
        private Node<T> next;
        private Node<T> prev;
        public Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    public DoubleLinkedList(){
        this.head = null;
    }

    public boolean checkIndex(int index) {
        if(index<0 || index>getSize()){
            return false;
        }
        return true;
    }
    public int getSize(){
        int i = 1;
        if(head==null){
            return 0;
        }
        Node<T> current = head;
        while(current.next!=null){
            current = current.next;
            i++;
        }
        return i;
    }
    public void addFirstItem(T element){
        Node<T> elem = new Node<>(element);
        if(head == null){
            head = elem;
            return;
        }
        elem.next=head;
        head.prev = elem;
        head = elem;
    }

    public void addLastItem(T element){
        Node<T> elem = new Node<>(element);
        if(head == null){
            head = elem;
            return;
        }
        Node<T>current = head;
        while(current.next!=null){
            current = current.next;
        }
        current.next = elem;
        elem.prev = current;
        //elem.next = null;
    }

    public void insertAt(int index, T element)throws IndexOutOfBoundsException{
        Node<T> elem = new Node<T>(element);
        if (checkIndex(index)==false){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if(head==null){
            head = elem;
            head.next = head;
            return;
        }
        if(index==0){
            addFirstItem(element);
            return;
        }
        if(index==getSize()){
            addLastItem(element);
            return;
        }
        Node<T> current = head;
        int i = 0;
        while(i<index-1 && current.next!=null){
            current = current.next;
            i++;
        }
        elem.next=current.next;
        elem.prev = current;
        current.next.prev = elem;
        current.next = elem;
    }

    public void removeLast(){
        if(head==null){
            return;
        }
        if(getSize()==1){
            head = null;
            return;
        }
        Node<T> current = head;
        while(current.next.next!=null){
            current= current.next;
        }
        current.next.prev = null;
        current.next = null;
    }
    public void removeFirst(){
        if(head==null){
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        head = head.next;
        head.prev = null;

    }
    public void removeAt(int index)throws IndexOutOfBoundsException{
        if(checkIndex(index)==false){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if(head==null){
            return;
        }
        if(index==0){
            removeFirst();
            return;
        }
        if(index==getSize()){
            removeLast();
            return;
        }
        Node<T> current = head;
        int i = 0;
        while(i<index-1 && current.next.next!=null){
            current= current.next;
            i++;
        }
        current.next = current.next.next;
        current.next.prev = current;
    }
    public void printItems(){
        if(head==null){
            return;
        }

        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.print("null");
    }
}
