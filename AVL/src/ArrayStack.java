public class ArrayStack<E> implements Stack<E>{
    E[]array;
    int capacity;
    int size;
    public ArrayStack(int capacity){
        this.array = (E[])new Object[capacity];
    }
    public ArrayStack(){
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
    public void push(E element){
        array[size] = element;
        size++;
    }
    public E top(){
        if(isEmpty()){
            return null;
        }
        return array[size-1];
    }
    public E pop(){
        if(isEmpty()){
            return null;
        }
        E last = array[size-1];
        array[size-1] = null;
        size--;
        return last;
    }
    public String reverse(String text){
        ArrayStack<Character> result= new ArrayStack<>(text.length());
        for(int el = 0;el<text.length();el++){
            result.push(text.charAt(el));
        }
        StringBuilder reversed = new StringBuilder();
        for(int el = text.length()-1;el>=0;el--){
            reversed.append(result.pop());
        }
        return reversed.toString();
    }
    public static String reverseSentenceByWords(String sentence){
        ArrayStack<Character> stack = new ArrayStack<>();

        String result = "";

        int length = sentence.length();

        for(int i = 0; i<length;i++) {

            if (sentence.charAt(i) == ' ' || i == length-1) {
                String add = "";

                if(i!=length-1){
                    add = " ";
                }else{
                    stack.push(sentence.charAt(i));
                }
                while (!stack.isEmpty()) {
                    result += stack.pop();
                }
                result += add;
            } else {
                stack.push(sentence.charAt(i));
            }
        }
        return result;
    }
}

//    public void reverseSpace(String text){
//        ArrayStack<Character> result= new ArrayStack<>(text.length());
//        //StringBuilder reversed = new StringBuilder();
//        Character [] res = new Character[text.length()];
//        int space = 0;
//        for(int el = 0;el<text.length();el++){
//            result.push(text.charAt(el));
//            //res[el] = text.charAt(el);
//            if(text.charAt(el)==' '|| el==text.length()-1){
//                for(int i = 0;i<=el;i++){
//                    res[i] = result.pop();
//                }
//                //res[el] = ' ';
//                for(int j = 0; j<=el-space;j++) {
//                    System.out.print(res[j]);
//                }
//                System.out.print(" ");
//                space = el+1;
//                res = new Character[text.length()];
//            }
//            //result.push(text.charAt(el));
//        }
//    }
//    public void rightOrder(){
//        int count1 = 0;
//        int count2 = 0;
//        int count3 = 0;
//        int size1 = size;
//        E[] container = (E[])new Object[size];
//
//        for(int i = 0;i<size1;i++){
//            container[i] = pop();
//        }
//        for(int i = 0;i< container.length;i++){
//            if(container[i]=="("){
//                count1+=1;
//            }
//            if(container[i]=="{"){
//                count3+=1;
//            }
//            if(container[i]=="["){
//                count2+=1;
//            }
//            if(container[i]==")"){
//                count1-=1;
//            }
//            if(container[i]=="]"){
//                count2-=1;
//            }
//            if(container[i]=="}"){
//                count3-=1;
//            }
//            push(container[array.length-1-i]);
//        }
//        if(count1==0 && count2==0 && count3==0){
//            return;
//        }
//        System.out.println("There are unnecessary symbols");
//    }
//}

