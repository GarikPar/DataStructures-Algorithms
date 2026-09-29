public class Main {
    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        ArrayQueue<Integer> stack1 = new ArrayQueue<>(10);
        ArrayStack<Integer> stack2 = new ArrayStack<>(10);

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack1.enqueue(60);
        stack1.enqueue(70);
        stack1.enqueue(80);
        stack1.enqueue(90);

        System.out.println("Top: " + stack.top());   // ➜ 30
        //System.out.println("Pop: " + stack.pop());   // ➜ 30
        System.out.println("Top after pop: " + stack.top()); // ➜ 20
        System.out.println("Size: " + stack.size()); // ➜ 2
        System.out.println("Is empty: " + stack.isEmpty()); // ➜ false
        System.out.println("Is empty: " + stack2.reverse("lana"));
        stack2.reverseSpace("Hello World Tigran Harutyunyan");
        System.out.println();
        stack.printList();
        System.out.println("Is empty: " + stack.removeMiddle());
        System.out.println();
        stack.printList();
        stack.revList();
        stack.printList();
        //System.out.println("Top: " + stack.pop());
        System.out.println("Top: " + stack1.first());   // ➜ 30
//System.out.println("Pop: " + stack1.pop());   // ➜ 30
        System.out.println("Top after pop: " + stack1.first()); // ➜ 20
        System.out.println("Size: " + stack1.size()); // ➜ 2
        System.out.println("Is empty: " + stack1.isEmpty()); // ➜ false
//System.out.println("Is empty: " + stack1.reverse("result"));
//System.out.println("Is empty: " + stack1.reverseSpace("Hello World Garik"));
        stack1.printList();
        //System.out.println("Is empty: " + stack1.removeMiddle());
        System.out.println();
        stack1.printList();
        //stack1.reverse();
        stack1.printList();
    }
}