//Bismillahirrahmanirrahim
package src;

public class Node <T> {
    T data; //? generic for blocks

    //? blocks dimensions
    Node<T> right;
    Node<T> left;
    Node<T> up;
    Node<T> down;
    int x;

    public Node(T data,int x){
        //? defining vals
        this.data=data;
        this.right=null;
        this.left=null;
        this.up=null;
        this.down=null;
        this.x=x;
    }
}
