public class MinHeap {
    private int size;
    private int capacity;
    private int[] heap;

    public MinHeap(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    public MinHeap(){
        this.capacity = 10;
        this.size = 0;
        this.heap = new int[10];
    }

    public int parent(int i){
        return (i-1)/2;
    }

    public int leftChild(int i){
        return i*2+1;
    }

    public int rightChild(int i){
        return i*2+2;
    }

    public int peak(){
        if(size!=0){
            return heap[0];
        }
        return -1;
    }

    public void swap(int i, int j){
        if(i>=size || j>=size){
            System.out.println("Indexes are out of bounds");
            return;
        }

        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void BubbleDownR(int i){
        int minIndex = i;
        int leftSide = leftChild(i);
        int rightSide = rightChild(i);

        if(heap[minIndex]>heap[leftSide] && leftSide<size){
            minIndex = leftSide;
        }
        if(heap[minIndex]>heap[rightSide] && rightSide<size){
            minIndex = rightSide;
        }
        if(i!=minIndex){
            swap(i,minIndex);
            BubbleDownR(minIndex);
        }
    }

    public void BubbleUp(int i){
        while(i>0 && heap[parent(i)]>heap[i]){
            swap(i,parent(i));
            i = parent(i);
        }
    }

    public void BD(int i){


        while(true){
            int minIndex = i;
            int leftSide = leftChild(i);
            int rightSide = rightChild(i);
            if(heap[minIndex]>heap[leftSide] && leftSide<size){
                minIndex = leftSide;
            }
            if(heap[minIndex]>heap[rightSide] && rightSide<size){
                minIndex = rightSide;
            }
            if(i!=minIndex){
                swap(i,minIndex);
            }else{
                break;
            }
        }

    }

    public void BUPREC(int i){
        if(heap[parent(i)]>heap[i]){
            swap(i, parent(i));
            BUPREC(parent(i));
        }
    }

    public int removeMin(){
        if(size==0){
            System.out.println("There are not any values");
            return -1;
        }
        int min = heap[0];
        heap[0] = heap[size-1];
        size--;
        BubbleDownR(0);
        return min;
    }

    public void insert(int value){
        if(size==capacity){
            System.out.println("You can add new member");
            return;
        }
        heap[size] = value;
        size++;
        BubbleUp(size-1);
    }

    public void printHeap(){
        for(int num: heap){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static int adder(String word){
        int arr2 = 0;
        for (int i = 0; i<word.length();i++){
            char k = word.charAt(0);
            arr2+=(int)k;
        }
        return arr2;
    }
    //adder("Top")
}
