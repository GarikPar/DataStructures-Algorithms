public class ArrayQueue<E> implements Queue<E>{
    E[]array;
    int first = 0;
    int size = 0;
    int capacity;
    public ArrayQueue(int capacity){
        this.capacity = capacity;
        this.array = (E[])new Object[capacity];
    }
    public int size(){
        return size;
    }
    public E first(){
        return array[first];
    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }
    public void enqueue(E element){
        array[(first+size)%capacity] = element;
        size++;
    }

    public E dequeue(){
        if(isEmpty()){
            return null;
        }
        E last = array[first];
        first = (first +1)%array.length;
        size--;
        return last;
    }
//    public void reverse(){
//        ArrayQueue<Character> result= new ArrayQueue<>(size);
//        E[] arr = (E[]) new Object[size];
//        int size1 = size;
//        int el = 0;
//        while(el<size1){
//            arr[el] = dequeue();
//            el++;
//        }
//        for(int i = arr.length-1;i>=0;i--){
//            enqueue(arr[i]);
//        }
//    }
    public void printList(){
        for(int i = 0;i<size;i++){
            System.out.print(array[i] + "->");
        }
        System.out.print("null");
        System.out.println();
    }
}

