public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        System.out.print("Adding elements at the beginning:");
        list.addFirstItem(10);
        list.addFirstItem(5);
        list2.addFirstItem(12);
        list2.addFirstItem(15);
        list.printItems(); // 5 -> 10 -> null
        System.out.println();

        System.out.print("Adding elements at the end: ");
        list.addLastItem(20);
        list.addLastItem(30);
        list.addLastItem(40);
        list.addLastItem(50);
        list.addLastItem(65);
        //list.addLastItem(70);
//        list.printItems(); // 5 -> 10 -> 20 -> 30 -> null
//        System.out.println();
//
//        System.out.print("Inserting elements at specific positions: ");
//        list.insertAt(0, 1); // beginning
//        list.insertAt(3, 15); // middle
//        list.insertAt(list.getSize(), 40); // end
//        list.printItems(); // 1 -> 5 -> 10 -> 15 -> 20 -> 30 -> 40 -> null
//        System.out.println();
//
//        System.out.print("Removing first element: ");
//        list.removeFirst();
//        list.printItems(); // 5 -> 10 -> 15 -> 20 -> 30 -> 40 -> null
//        System.out.println();
//
//        System.out.print("Removing last element: ");
//        list.removeLast();
//        list.printItems(); // 5 -> 10 -> 15 -> 20 -> 30 -> null
//        System.out.println();
//
//        System.out.print("Removing element at index 2: ");
//        list.removeAt(2);
//        list.printItems();// 5 -> 10 -> 20 -> 30 -> null
//        list.addLastItem(40);
//        list.addLastItem(50);
//        list.addLastItem(60);
//        list.removeFirst();
          System.out.println();
          list.printItems();
          list.rotate(4);
          System.out.println();
          list.printItems();
//        list.insertionByPriority(45);
//        list.printItems();
//        list.reverse();
//        list.insertionByPriority(65);
//        System.out.println();
//        //list.sort();
//        list.printItems();
//        System.out.println();
//        System.out.println(list.middValue());
//        System.out.println(list.middValue2());
//
//        System.out.println("Current size of the list: " + list.getSize()); // 4
//        list.printItems();
//        System.out.println();
//        list2.printItems();
//        System.out.println();
//        list.merge(list.head(),list2.head());
//        System.out.println();
//        list.printItems();
////        list.average(list);
//        System.out.println();
//        list2.printItems();
    }

}
