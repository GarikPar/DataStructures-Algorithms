public class BinarySearchTree<E extends Comparable<E>>{
    private TreeNode<E> root;
    public static class TreeNode<E extends Comparable<E>>{
        E data;
        TreeNode<E> right;
        TreeNode<E> left;
        public TreeNode(E data){
            this.data = data;
            this.right = null;
            this.left = null;
        }
    }
    public BinarySearchTree(E data){
        this.root = new TreeNode<>(data);
    }
    public TreeNode addRoot(TreeNode<E> e){
        root = e;
        return root;
    }
    public void insert(E e){
        root = insertHelper(root,e);
    }
    public TreeNode insertHelper(TreeNode<E> root,E e){
        if(root==null){
            root = new TreeNode<>(e);
            return root;
        }
        if(e.compareTo(root.data)<0){
            root.left = insertHelper(root.left,e);
        }
        if(e.compareTo(root.data)>0){
            root.right = insertHelper(root.right,e);
        }
        return root;
    }


    public void insert2(TreeNode<E> root,E e){
        if(e.compareTo(root.data)<0){
            if(root.left==null){
                root.left = new TreeNode<>(e);
            }else {
                insert2(root.left, e);
            }
        }
        if(e.compareTo(root.data)>0){
            if(root.right==null){
                root.right = new TreeNode<>(e);
            }else {
                insert2(root.right, e);
            }
        }
    }
    public boolean search(E e){
        root = searchHelper(root,e);
        if (root == null){
            return false;
        }
        return true;
    }
    public TreeNode searchHelper(TreeNode<E> root,E e){
        if(root==null){
            return root;
        }
        if(e.compareTo(root.data)==0){
            return root;
        }
        if(e.compareTo(root.data)<0){
            return searchHelper(root.left,e);
        }
        if(e.compareTo(root.data)>0){
            return searchHelper(root.right,e);
        }
        return root;
    }

    public void remove(int key){
        root = removeHelper(root,key);
    }

    public TreeNode removeHelper(TreeNode root, int key){
        if(root==null){
            return root;
        }

        if(key<(int)root.data){
            root.left = removeHelper(root.left, key);
        }
        else if(key>(int)root.data){
            root.right = removeHelper(root.right, key);
        }
        else {
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            } else {

                root.data = minValue(root.right);
//                minRoot(root.right);
//                System.out.println((int) root.data);
//                return root;
                root.right = removeHelper(root.right, (int)root.data);
                //root = removeHelper(root.right,(int) root.data);
            }
        }
        return root;
    }

    public int minValue(TreeNode root){
        int minval = (int) root.data;
        while(root.left!=null){
            minval = (int)root.left.data;
            root = root.left;
        }

        return minval;
    }

    public void minRoot(TreeNode root){
        while(root.left!=null){
            root = root.left;
        }
        root.data = null;
        root = null;
    }

    public void printPreOrder(){
        if(root==null){
            return;
        }
        System.out.print("[");
        printPreOrderHelper(root);
        System.out.print("]");
    }

    public void printPreOrderHelper(TreeNode<E> current){
        if(current==null){
            return;
        }
        System.out.print(current.data+" ");
        printPreOrderHelper(current.left);
        printPreOrderHelper(current.right);
    }

    public void printInOrder(){
        if(root==null){
            return;
        }
        System.out.print("[");
        printInOrderHelper(root);
        System.out.print("]");
    }

    public void printInOrderHelper(TreeNode<E> current){
        if(current==null){
            return;
        }
        printInOrderHelper(current.left);
        System.out.print(current.data+" ");
        printInOrderHelper(current.right);
    }

    public void printPostOrder(){
        if(root==null){
            return;
        }
        System.out.print("[");
        printPostOrderHelper(root);
        System.out.print("]");
    }

    public void printPostOrderHelper(TreeNode<E> current){
        if(current==null){
            return;
        }
        printPostOrderHelper(current.left);
        printPostOrderHelper(current.right);
        System.out.print(current.data +" ");
    }

}
