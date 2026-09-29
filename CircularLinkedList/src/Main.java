public class Main {
    public static void main(String[] args) {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();

        System.out.println("=== Adding First Items ===");
        list.addFirstItem(10);
        list.addFirstItem(20);
        list.addFirstItem(30);
        list.printItems(); // 30 -> 20 -> 10 -> (back to head)

        System.out.println("\n=== Adding Last Items ===");
        list.addLastItem(40);
        list.addLastItem(50);
        list.printItems(); // 30 -> 20 -> 10 -> 40 -> 50 -> (back to head)

        System.out.println("\n=== Inserting at Index ===");
        list.insertAt(2, 99); // insert between 20 and 10
        list.printItems(); // 30 -> 20 -> 99 -> 10 -> 40 -> 50 -> (back to head)

        System.out.println("\n=== Removing First Item ===");
        list.removeFirst();
        list.printItems(); // 20 -> 99 -> 10 -> 40 -> 50 -> (back to head)

        System.out.println("\n=== Removing Last Item ===");
        list.removeLast();
        list.printItems(); // 20 -> 99 -> 10 -> 40 -> (back to head)

        System.out.println("\n=== Removing at Index (2) ===");
        list.removeAt(2);
        list.printItems(); // 20 -> 99 -> 40 -> (back to head)

        System.out.println("\n=== List Size ===");
        System.out.println("Size: " + list.getSize());
    }
}
