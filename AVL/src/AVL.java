public class AVL<E extends Comparable<E>> {

    private AVLNode<E> root;

    public static class AVLNode<E extends Comparable<E>> {
        private E val;
        private int height;
        private AVLNode<E> left;
        private AVLNode<E> right;

        public AVLNode(E data) {
            val = data;
            height = 1;
            left = null;
            right = null;
        }
    }

    private int height(AVLNode<E> node) {
        if (node == null) return 0;
        return node.height;
    }

    private void updateHeight(AVLNode<E> node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    private int getBalance(AVLNode<E> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private AVLNode<E> rightRotate(AVLNode<E> x) {
        AVLNode<E> y = x.left;
        AVLNode<E> T2 = y.right;

        y.right = x;
        x.left = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private AVLNode<E> leftRotate(AVLNode<E> x) {
        AVLNode<E> y = x.right;
        AVLNode<E> T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public void insert(E key) {
        root = insertHelper(root, key);
    }

    private AVLNode<E> insertHelper(AVLNode<E> node, E key) {
        if (node == null) {
            return new AVLNode<E>(key);
        }


        if (key.compareTo(node.val) < 0) {
            node.left = insertHelper(node.left, key);
        } else if (key.compareTo(node.val) > 0) {
            node.right = insertHelper(node.right, key);
        } else {
            return node;
        }

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.left.val) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.val) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && key.compareTo(node.left.val) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.val) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void remove(E key) {
        root = removeHelper(root, key);
    }

    public AVLNode<E> removeHelper(AVLNode<E> node, E key) {
        if (node == null) {
            return null;
        }

        AVLNode<E> temp;
        if (key.compareTo(node.val) < 0) {
            node.left = removeHelper(node.left, key);
        } else if (key.compareTo(node.val) > 0) {
            node.right = removeHelper(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                if (node.left != null) {
                    temp = node.left;
                } else {
                    temp = node.right;
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }

            } else {
                temp = minValueNode(node.right);

                node.val = temp.val;

                node.right = removeHelper(node.right, temp.val);

            }
        }
        if (node == null) {
            return null;
        }

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private AVLNode<E> minValueNode(AVLNode<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void printInLevels() {
        if (root == null) {
            System.out.println("The tree is empty");
            return;
        }
        ArrayQueue<AVLNode<E>> queue = new ArrayQueue<>(30);

        queue.enqueue(root);
        System.out.print("[");

        while (!queue.isEmpty()) {
            AVLNode<E> current = queue.dequeue();
            System.out.print(current.val + ", ");


            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }

        System.out.println("]");
    }

    public void printPreorderIterative() {
        if (root == null) {
            System.out.println("the tree is empty");
            return;
        }

        ArrayStack<AVLNode<E>> stack = new ArrayStack<>();

        stack.push(root);
        System.out.print("[");

        while (!stack.isEmpty()) {
            AVLNode<E> temp = stack.pop();
            System.out.print(temp.val + " ");

            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }

        }
        System.out.println("]");
    }

    public void printPreorder() {
        if (root == null) {
            System.out.println("The tree is empty");
            return;
        }
        System.out.print("[");
        printPreorderHelper(root);
        System.out.println("]");
    }
    private void printPreorderHelper(AVLNode<E> current) {
        if (current == null) {
            return;
        }
        System.out.print(current.val + " ");
        printPreorderHelper(current.left);
        printPreorderHelper(current.right);
    }

    public void printInorderIterative() {
        if (root == null) {
            System.out.println("The tree is empty");
            return;
        }

        ArrayStack<AVLNode<E>> stack = new ArrayStack<>();

        AVLNode<E> temp = root;
        System.out.print("[");

        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            temp = stack.pop();
            System.out.print(temp.val + " ");
            temp = temp.right;
        }
        System.out.println("]");
    }

    public void printPostorderIterative() {
        if (root == null) {
            System.out.println("The tree is empty");
            return;
        }

        ArrayStack<AVLNode<E>> stack1 = new ArrayStack<>();
        ArrayStack<AVLNode<E>> stack2 = new ArrayStack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            AVLNode<E> current = stack1.pop();

            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }

            stack2.push(current);
        }

        System.out.print("[");
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
        System.out.println("]");
    }


    public static void main(String[] args) {
        AVL<Integer> tree = new AVL<>();


        AVLNode<Integer> n1 = new AVLNode<>(50);
        AVLNode<Integer> n2 = new AVLNode<>(60);
        AVLNode<Integer> n3 = new AVLNode<>(70);
        AVLNode<Integer> n4 = new AVLNode<>(80);
        AVLNode<Integer> n5 = new AVLNode<>(100);


        tree.insert(50);
        tree.height(AVLNode<Integer>(70));
//        tree.printInLevels();
//        tree.printPreorderIterative();
//        tree.insert(30);
        tree.insert(70);
//        tree.printInLevels();
        tree.printPreorderIterative();
//        tree.insert(20);
//        tree.insert(40);
        tree.insert(60);
        tree.printPostorderIterative();
//        tree.printInorderIterative();
//        tree.printInLevels();
//        tree.printPreorder();
//        tree.printPreorderIterative();
//        tree.insert(80);
//        tree.printInLevels();
//        tree.printPreorder();
//        tree.insert(100);
//        tree.printInLevels();
//        tree.printPreorder();

//        tree.printInLevels();
    }
}
