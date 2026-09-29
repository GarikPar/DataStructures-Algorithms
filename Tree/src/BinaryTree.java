//public class BinaryTree<E> {
//    private TreeNode<E> root;
//    public static class TreeNode<E>{
//        E data;
//        TreeNode<E> right;
//        TreeNode<E> left;
//        public TreeNode(E data){
//            this.data = data;
//            this.right = null;
//            this.left = null;
//        }
//    }
//    public BinaryTree(E data){
//        this.root = new TreeNode<>(data);
//    }
//    public TreeNode addRoot(TreeNode<E> e){
//        root = e;
//        return root;
//    }
//
//    public TreeNode<E> getRoot(){
//        return root;
//    }
//    public boolean isLeaf(TreeNode<E> e){
//        if(e.right == null && e.left==null){
//            return true;
//        }
//        return false;
//    }
//
//    public TreeNode addLeft(TreeNode<E> p,TreeNode<E> e){
//        if(p.left==null){
//            p.left = e;
//            return p.left;
//        }
//        System.out.println("Left child already exists");
//        return null;
//    }
//
//    public TreeNode addRight(TreeNode<E> p,TreeNode<E> e){
//        if(p.right==null){
//            p.right = e;
//            return p.right;
//        }
//        System.out.println("Left child already exists");
//        return null;
//    }
//
//    public E set(TreeNode<E> p,TreeNode<E> e){
//        E temp = p.data;
//        p.data = e.data;
//        return temp;
//    }
//
//    public void attach(TreeNode<E>p, TreeNode<E>T1, TreeNode<E>T2){
//        if(isLeaf(p)){
//            p.left = T1;
//            p.right = T2;
//        }
//        return;
//    }
//
//    public TreeNode<E> remove(TreeNode<E> p){
//        TreeNode<E> temp = p;
//        if(p.right!=null && p.left!=null){
//            return null;
//        } else if (p.left==null) {
//            p.data = p.right.data;
//        }else if (p.right==null) {
//            p.data = p.left.data;
//        }
//        return temp;
//    }
//
//    public void printInOrder(){
//        if(root==null){
//            return;
//        }
//        System.out.print("[");
//        printInOrderHelper(root);
//        System.out.print("]");
//    }
//
//    public void printInOrderHelper(TreeNode<E> current){
//        if(current==null){
//            return;
//        }
//        System.out.println(current.data);
//        printInOrderHelper(current.left);
//        printInOrderHelper(current.right);
//
//    }
//
////    public void printPreOrder(TreeNode<E> current){
////        if(current.left!=null){
////            System.out.println(current.data);
////            printInOrderHelper(current.left);
////        }
////        current = current.right
////        else if(current.right!=null){
////            System.out.println(current.data);
////            printInOrderHelper(current.left);
////        }
////    }
///  if hasNExt(): E dat = cur.dat; cur = cur.next; return dat;
//}
