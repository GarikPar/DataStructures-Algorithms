public class Main {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

        System.out.println("=== Adding First Items ===");
        list.addFirstItem(10);
        list.addFirstItem(20);
        list.addFirstItem(30);
        list.printItems(); // 30 <-> 20 <-> 10 <-> null

        System.out.println("\n=== Adding Last Items ===");
        list.addLastItem(40);
        list.addLastItem(50);
        list.printItems(); // 30 <-> 20 <-> 10 <-> 40 <-> 50 <-> null

        System.out.println("\n=== Inserting at Index ===");
        list.insertAt(2, 99); // between 20 and 10
        list.printItems(); // 30 <-> 20 <-> 99 <-> 10 <-> 40 <-> 50 <-> null

        System.out.println("\n=== Removing First Item ===");
        list.removeFirst();
        list.printItems(); // 20 <-> 99 <-> 10 <-> 40 <-> 50 <-> null

        System.out.println("\n=== Removing Last Item ===");
        list.removeLast();
        list.printItems(); // 20 <-> 99 <-> 10 <-> 40 <-> null

        System.out.println("\n=== Removing at Index (1) ===");
        list.removeAt(1); // remove 99
        list.printItems(); // 20 <-> 10 <-> 40 <-> null

        System.out.println("\n=== Adding and Removing Edge Cases ===");
        list.addFirstItem(5);
        list.addLastItem(60);
        list.printItems(); // 5 <-> 20 <-> 10 <-> 40 <-> 60 <-> null
        System.out.println();
        System.out.println("Size: " + list.getSize());

        list.removeFirst();
        list.removeLast();
        list.printItems(); // 20 <-> 10 <-> 40 <-> null

        System.out.println("\n=== Final Size ===");
        System.out.println("Size: " + list.getSize());
    }
}
