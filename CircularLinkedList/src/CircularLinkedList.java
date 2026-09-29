public class CircularLinkedList<T extends Comparable> {
    private Node<T> head;
    public class Node<T extends Comparable>{
        T data;
        private Node<T> next;
        public Node(T data){
            this.data = data;
            this.next = null;
        }
    }
    public CircularLinkedList(){
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
        while(current.next!=head){
            current = current.next;
            i++;
        }
        return i;
    }
    public void addFirstItem(T element){
        Node<T> elem = new Node<>(element);
        if(head == null){
            head = elem;
            head.next = head;
            return;
        }
        Node<T> current = head;
        while(current.next!=head){
            current = current.next;
        }
        elem.next = head;
        current.next = elem;
        head = elem;
    }

    public void addLastItem(T element){
        Node<T> elem = new Node<>(element);
        if(head == null){
            head = elem;
            head.next = head;
            return;
        }
        Node<T>current = head;
        while(current.next!=head){
            current = current.next;
        }
        current.next = elem;
        elem.next = head;
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
        Node<T> current = head;
        int i = 0;
        while(i<index-1 && current.next!=head){
            current = current.next;
            i++;
        }
        elem.next = current.next;
        current.next = elem;

    }

    public void removeLast(){
        if(head==null){
            return;
        }
        if(getSize()==1){
            head = head.next=null;
            return;
        }
        Node<T> current = head;
        while(current.next.next!=head){
            current= current.next;
        }
        current.next = head;
    }
    public void removeFirst(){
        if(head==null){
            return;
        }
        if(getSize()==1){
            head = head.next = null;
            return;
        }
        Node<T> current = head;
        while(current.next!=head){
            current = current.next;
        }
        head = head.next;
        current.next = head;
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
        Node<T> current = head;
        int i = 0;
        while(i<index-1 && current.next.next!=head){
            current= current.next;
            i++;
        }
        current.next = current.next.next;
    }
    public void printItems(){
        if(head==null){
            return;
        }
        if(getSize()==1){
            System.out.print(head.data+"-> ");
        }else {
            Node<T> current = head.next;
            System.out.print(head.data + "-> ");
            while (current != head) {
                System.out.print(current.data + "-> ");
                current = current.next;
            }
        }
        System.out.print(head.data);
    }
}
