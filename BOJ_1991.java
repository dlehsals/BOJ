import java.util.Scanner;

class Node{
    char data;
    Node left, right;
    Node(char data){
        this.data = data;
    }
}
class Tree{
    Node root;

    public void add(char data, char leftData, char rightData){
        if(root == null){
            if(data != '.') root = new Node(data);
            if(leftData != '.')root.left = new Node(leftData);
            if(rightData != '.')root.right = new Node(rightData);
        }else{
            search(root, data, leftData, rightData);
        }
    }

    public void search(Node root, char data, char leftData, char rightData){
        if(root == null) return;
        else if(root.data == data){
            if(leftData != '.') root.left = new Node(leftData);
            if(rightData != '.') root.right = new Node(rightData);
        }
        else{
            search(root.left, data, leftData, rightData);
            search(root.right, data, leftData, rightData);
        }
    }

    public void preOrder(Node root){
        System.out.print(root.data);
        if(root.left != null) preOrder(root.left);
        if(root.right != null) preOrder(root.right);
    }

    public void inOrder(Node root){
        if(root.left != null) inOrder(root.left);
        System.out.print(root.data);
        if(root.right != null) inOrder(root.right);
    }

    public void postOrder(Node root){
        if(root.left != null) postOrder(root.left);
        if(root.right != null) postOrder(root.right);
        System.out.print(root.data);
    }

}
public class BOJ_1991 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Tree tree = new Tree();
        for(int i=0;i<n;i++){
            tree.add(sc.next().charAt(0), sc.next().charAt(0), sc.next().charAt(0));
        }

        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);
    }
}
