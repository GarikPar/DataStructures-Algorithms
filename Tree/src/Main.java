//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(10);
//        BinarySearchTree.TreeNode<Integer> root = new BinarySearchTree.TreeNode<>(6);
//        bst.addRoot(root);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(18);

        bst.printPreOrder();
        System.out.println();
        bst.printInOrder();
        System.out.println();
        bst.printPostOrder();

        bst.remove(15);
        System.out.println();
        bst.printPostOrder();

//        System.out.println();
//        System.out.println(bst.search(7));
//        System.out.println(bst.search(20));
    }
}