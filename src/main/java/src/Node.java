//Bismillahirrahmanirrahim
package src;

public class Node <T> {
    public T data; //? generic for blocks

    //? blocks dimensions
    public Node<T> right;
    public Node<T> left;
    public Node<T> up;
    public Node<T> down;
    public int x;

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
