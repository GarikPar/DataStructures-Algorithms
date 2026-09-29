public class SmartArray<T extends Comparable<T>> {
    int size = 0;
    int capacity;
    T[] array;

    public SmartArray(int capacity){

        this.capacity = capacity;
        this.array = (T[]) new Comparable[capacity];
    }
    public SmartArray(){
        this(10);
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
    public T get(int index) throws NoSuchIndex{
        if(checkIndex(index)==false){
            throw new NoSuchIndex("Index" + " "+index +" "+ "does not exist");
        }
        return array[index];
    }

    public T set(int index, T newVal) throws NoSuchIndex{
        if(checkIndex(index)==false){
            throw new NoSuchIndex("Index" + " "+index +" "+ "does not exist");
        }
        T old = array[index];
        array[index] = newVal;
        return old;
    }

    public void add(int index, T newVal) throws NoSuchIndex{
        if(checkIndex(index)==false){
            throw new NoSuchIndex("Index" + " "+index +" "+ "does not exist");
        }
        if(size==capacity){
            resize();
        }
        for(int i = size;i>index;i--){
            array[i] = array[i-1];
        }
        array[index] = newVal;
        size++;
    }

    public T remove(int index) throws NoSuchIndex{
        if(checkIndex(index)==false){
            throw new NoSuchIndex("Index" + " "+index +" "+ "does not exist");
        }

        T removed = array[index];
        for (int i = index;i<size-1;i++){
            array[i] = array[i+1];
        }
        size--;
        return removed;

    }
    public void insert(int index, T elem) throws NoSuchIndex{
        if(checkIndex(index)==false){
            throw new NoSuchIndex("Index" + " "+index +" "+ "does not exist");
        }
        if(size==capacity){
            resize();
        }
        for(int i = size-1;i>=index;i--){
            array[i+1] = array[i];
        }
        array[index] = elem;
    }

    public void resize(){
        capacity*=2;
        T[]array1 = (T[]) new Object[capacity];
        for(int i = 0;i<size;i++){
            array1[i] = array[i];
        }
        array = array1;
    }
    public boolean checkIndex(int index) {
        if(index<0 || index>size){
            return false;
        }
        return true;
    }

    public int compare(int index1, int index2) throws NoSuchIndex{
        if(checkIndex(index1)==false || checkIndex(index2)==false){
            throw new NoSuchIndex("One of Indexes do not exist");
        }
        return array[index1].compareTo(array[index2]);
    }
}
