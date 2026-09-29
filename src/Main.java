public class Main {
    public static void main(String[] args) {
        try {
            SmartArray<String> arr = new SmartArray(5);

            // Initially empty
            System.out.println("Empty? " + arr.isEmpty()); // true
            System.out.println("Size: " + arr.size());     // 0

            // Add elements
            arr.add(0, "10");
            arr.add(1, "20");
            arr.add(2, "30");
            arr.add(1, "15"
            );  // insert in the middle

            System.out.println("Size after adds: " + arr.size()); // 4
            System.out.println("Element at index 1: " + arr.get(1)); // 15

            // Replace element
            String old = arr.set(2, "25");
            System.out.println("Replaced " + old + " with 25");

            // Remove element
            String removed = arr.remove(1);
            System.out.println("Removed element: " + removed);
            System.out.println(arr.compare(0,1));

            // Print all elements
            System.out.print("Final array contents: ");
            for (int i = 0; i < arr.size(); i++) {
                System.out.print(arr.get(i) + " ");
            }
            System.out.println();

        } catch (NoSuchIndex e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
