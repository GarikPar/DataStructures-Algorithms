public class LinkedStack<E> implements Stack<E>{
    Node<E>head;
    int size = 0;
    public class Node<E>{
        private E data;
        private Node<E> next;
        public Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }
        public Node(E data){
            this(data,null);
        }
    }
    public LinkedStack(){
        this.head = null;
        this.size = 0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }
    public void push(E element){
        Node<E>elem = new Node<>(element);
        elem.next = head;
        head = elem;
        size++;
    }
    public E top(){
        if(isEmpty()){
            return null;
        }
        return head.data;
    }
    public E pop(){
        if(isEmpty()){
            return null;
        }
        E last = head.data;
        head = head.next;
        size--;
        return last;
    }

    public void revList(){
        LinkedStack<E> result = new LinkedStack<>();
        E[] array = (E[]) new Object[size];
        int size1 = size;
        for(int el  = 0;el<size1;el++){
            array[el] = pop();
        }
        for(int el  = 0;el<array.length;el++){
            push(array[el]);
        }
    }
    public E removeMiddle(){
        LinkedStack<E> result = new LinkedStack<>();
        for (int el = 0;el<size/2+1;el++){
            result.push(pop());
        }
        E finalResult = pop();
        while(!result.isEmpty()){
            push(result.pop());
        }
        return finalResult;
    }
    public void printList(){
        Node<E>current = head;
        E[]array = (E[])new Object[size];
        for(int i = 0;i<size;i++){
            array[size-1-i] = current.data;
            current = current.next;
        }
        for(E elem: array){
            System.out.print(elem+"->");
        }
        System.out.println("null");
    }
}
