import java.util.Scanner;
class AVLTree{
    static class Node{
        int data;
        Node left, right;
        int height;
        
        Node(int value){
            data=value;
            height=1;
        }
    }
    
    static Node insert(Node root, int key){
        if(root==null){
            return new Node(key);
        }
        if(key<root.data){
            root.left=insert(root.left, key);
        }
        else if (key>root.data){
            root.right=insert(root.right,key);
        }
        root.height=1+Math.max(getHeight(root.left),getHeight(root.right));
        
        int balance = getBalance(root);
        
        if(balance > 1 && key<root.left.data){
            return rightRotate(root);
        }
        
        if(balance < -1 && key>root.right.data){
            return leftRotate(root);
        }
        if(balance > 1 && key > root.left.data){
            root.left=leftRotate(root.left);
            return rightRotate(root);
        }
        
        if(balance <-1 && key<root.right.data){
            root.right =rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    
    static Node deleteNode(Node root, int key){
        if(root==null){
            return root;
        }
        if(key<root.data){
            root.left=deleteNode(root.left, key);
        }
        else if(key>root.data){
            root.right=deleteNode(root.right,key);
        }
        else{
            if(root.left==null||root.right==null){
                Node temp=(root.left!=null)?root.left:root.right;
                
                if(temp==null){
                    temp=root;
                    root=null;
                }else{
                    root=temp;
                }
                temp=null;
            }
            else{
                Node temp=findMin(root.right);
                root.data=temp.data;
                root.right=deleteNode(root.right, temp.data);
            }
        }
        if(root==null){
            return root;
        }
        root.height=1+Math.max(getHeight(root.left),getHeight(root.right));
        int balance = getBalance(root);
        if(balance>1 && getBalance(root.left)>=0){
            return rightRotate(root);
        }
        if(balance > 1 && getBalance(root.left)<0){
            root.left=leftRotate(root.left);
            return rightRotate(root);
        }
        if(balance < -1 && getBalance(root.right)>0){
            root.right=rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    static Node findMin(Node node ){
        Node current= node ;
        while (current.left != null){
            current = current.left;
        }
        return current;
    }
    static int getHeight(Node node ){
        if ( node == null){
            return 0;
        }
        return node.height;
    }
    static int getBalance(Node node ){
        if(node ==null){
            return 0;
        }
        return getHeight(node.left)-getHeight(node.right);
    }
    static Node rightRotate(Node y){
        Node x=y.left;
        Node T2=x.right;
        
        x.right = y;
        y.left=T2;
        
        y.height=1+Math.max(getHeight(y.left), getHeight(y.right));
        x.height=1+Math.max(getHeight(x.left), getHeight(x.right));
        
        return x;
    }
    static Node leftRotate(Node x){
        Node y= x.right;
        Node T2=y.left;
        
        y.left=x;
        x.right=T2;
        
        x.height=1+Math.max(getHeight(x.left),getHeight(x.right));
        y.height = 1+Math.max(getHeight(y.left), getHeight(y.right));
        
        return y;
    }
    static void preorderTraversal(Node root){
        if(root != null){
            System.out.print(root.data+ " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }
    public static void main(String[ ] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[ ] elements= new int[n];
        for(int i=0; i<n;i++){
            elements[i]=scanner.nextInt();
        }
        int deletekey=scanner.nextInt();
        
        Node root=null;
        for(int i=0; i<n;i++){
            root = insert(root, elements[i]);
        }
        System.out.println("Preorder of AVL tree");
        preorderTraversal(root);
        
        root=deleteNode(root, deletekey);
        
        System.out.println("\nPreorder after deletion of the element");
        preorderTraversal(root);
        
        scanner.close();
    }
    
}