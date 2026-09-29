import java.util.Scanner;

public class LinkedList <T extends Comparable>{
    private Node<T> head;
    public LinkedList(){
        this.head = null;
    }
    public Node<T> head(){
        return head;
    }
    public boolean checkIndex(int index) {
        if(index<0 || index>getSize()){
            return false;
        }
        return true;
    }

    public static class Node<T>{
        private T data;
        private Node<T> next;
        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
        public Node(T data){
            this(data,null);
        }
    }

    public void addFirstItem(T element){
        Node<T> elem = new Node<T>(element);
        if(head == null){
            head = elem;
            return;
        }
        elem.next = head;
        head = elem;
    }

    public void addLastItem(T element){
        Node<T> elem = new Node<T>(element);
        if(head == null){
            head = elem;
        }else{
            Node<T>current = head;
            while(current.next!=null){
                current = current.next;
            }
            current.next = elem;
            elem.next = null;
        }
    }
    public void insertAt(int index, T element)throws IndexOutOfBoundsException{
        Node<T> elem = new Node<T>(element);
        if (checkIndex(index)==false){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if(head==null){
            head = elem;
            return;
        }
        if(index==0){
            addFirstItem(element);
            return;
        }
        Node<T> current = head;
        int i = 0;
        while(i<index-1 && current.next!=null){
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
        Node<T> current = head;
        while(current.next.next!=null){
            current= current.next;
        }
        current.next = null;
    }
    public void removeFirst(){
        if(head==null){
            return;
        }
        head = head.next;
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
        while(i<index-1 && current.next.next!=null){
            current= current.next;
            i++;
        }
        current.next = current.next.next;
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
    public void printItems(){
        if(head==null){
            return;
        }
        Node<T> current = head;
        while(current!=null){
            System.out.print(current.data+"-> ");
            current = current.next;
        }
        System.out.print("null ");
    }

    public T middValue(){
        if(head==null){
            return null;
        }
        if(head.next==null){
            return head.data;
        }
        Node<T>current = head;
        int mid = getSize()/2;
        for(int el = 0;el<mid;el++){
            current = current.next;
        }
        return current.data;
    }
    public T middValue2(){
        if(head==null){
            return null;
        }
        if(head.next==null){
            return head.data;
        }
        Node<T>current = head;
        Node<T>mid = head;
        while(current!=null && current.next!=null){
            mid = mid.next;
            current = current.next.next;
        }
        return mid.data;
    }

    public void rotate(int k){
        if(head==null){
            return;
        }
        if(head.next==null){
            return;
        }
        Node<T>current = head;
        Node<T>temp = head;
        Node<T>last = head;
        int i = 0;
        while(i<k%getSize()){
            last = current;
            current = current.next;
            i++;
        }
        head = current;
        while (current==null || current.next!=null){
            current = current.next;
        }
        current.next = temp;
        last.next = null;
    }
    public void reverse() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            return;
        }
        Node<T> current = head;
        Node<T> next;
        Node<T> prev = null;
        while(current!=null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            //head = next;
        }
        head = prev;
    }
    public void insertionByPriority(T elem){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("What is priority here for"+" "+(int)elem+" ");
        String priority = sc.nextLine();
        if (priority.equals("increasing")){
            insertIncOrder(elem);
        }else {
            insertDecOrder(elem);
        }
    }
    public void insertIncOrder(T elem) {
        Node<T> element = new Node<T>(elem);
        Node<T> current = head;
        Node<T> intCheck = head;
        if (head == null) {
            head = element;
        }
        if(getSize()==1){
            if((int)head.data>(int)element.data){
                element.next = head;
                head = element;
                return;
            }
            head.next = element;
            return;
        }
//        while(current.next!=null){
//            System.out.println(1);
//            if((int)current.data>(int)current.next.data){
//                System.out.println("Array is not sorted");
//                return;
//            }
//            current = current.next;
//        }
        while(intCheck.next!=null && (int)intCheck.next.data<(int)elem){
            intCheck = intCheck.next;
        }
        System.out.println((int) intCheck.data);
        element.next = intCheck.next;
        intCheck.next = element;
    }
    public void insertDecOrder(T elem) {
        if (head == null) {
            return;
        }

        Node<T> element = new Node<T>(elem);
        Node<T> current = head;
        Node<T> intCheck = head;
        if((int)head.data<(int)elem){
            element.next = head;
            head = element;
            return;
        }

//        while(current.next!=null){
//            if((int)current.data<(int)current.next.data){
//                System.out.println("Array is not sorted");
//                return;
//            }
//            current = current.next;
//        }
        while(intCheck.next!=null && (int)intCheck.next.data>(int)elem){
            intCheck = intCheck.next;
        }
        element.next = intCheck.next;
        intCheck.next = element;
    }
    public static void merge(Node head1, Node head2) {
        Node curr1 = head1, curr2 = head2;
        Node next1, next2;

        // While both lists have nodes
        while (curr1 != null && curr2 != null) {
            // Save next pointers
            next1 = curr1.next;
            next2 = curr2.next;

            // Insert curr2 between curr1 and next1
            curr1.next = curr2;
            curr2.next = next1;

            // Move ahead
            curr1 = next1;
            curr2 = next2;
        }

        // Update head2 to remaining nodes, if any
        head2 = curr2;

        // Print results for clarity
    }
    public static void average(LinkedList<Integer> list){
        Node<Integer> current = (Node<Integer>) list.head;
        Node<Integer> current2 = (Node<Integer>) list.head;
        int result = 0;
        int size = 0;
        while(current!=null){
            result+=(int)current.data;
            current = current.next;
            size++;
        }
        System.out.println(size);
        Node<Integer> middle = new Node<>(result/size);
        for(int i = 0;i<size/2-1;i++){
            current2 = current2.next;
        }
        middle.next = current2.next;
        current2.next = middle;
    }
    public boolean isPalindrome(Node head) {
        // code here
        T[]arr1 = (T[]) new Object[30];
        T[]arr2 = (T[]) new Object[30];

        Node current = head;
        Node temp = head;
        Node prev = null;
        Node next = head;
        int i = 0;
        while(current!=null){
            arr1[i]=current.data;
            current = current.next;
            i++;
        }
        while(temp!=null){
            next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        head = prev;
        Node list2 = head;
        while(list2!=null){
            arr2[i] = list2.data;
            list2 = list2.next;
        }
        for(int j= 0;i<arr1.length();i++){
            if(arr1[j].equals(arr2[j])==false){
                return false;
            }
        }
        return true;
    }
}
