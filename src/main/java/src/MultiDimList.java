//Bimillahirrahmanirrahim
package src;

public class MultiDimList<T> {
    private Node<Integer> head;

    void addProces(int data, int x) {
        Node<Integer> n_node = new Node<Integer>(data, x);
        Node<Integer> tmp = head;

        if (head == null) {
            head = n_node;
        } else {
            // tmp.x < x
            // 0 3
            while (tmp.left != null && tmp.left.x <= x) {
                tmp = tmp.left;
            }
            if (tmp.x == x) {
                while (tmp.up != null) {
                    tmp = tmp.up;
                }
                addBlockH(tmp, n_node);
            } else {
                addBlockV(tmp, n_node);
            }
            controllBlock(n_node);
        }
        print();
    }

    void addBlockH(Node<Integer> tmp, Node<Integer> n_node) {

        tmp.up = n_node;
        n_node.down = tmp;
        if (tmp.left != null && tmp.x == n_node.x)
            if (tmp.left.up != null) {
                tmp.left.up.right = n_node;
                n_node.left = tmp.left.up;
            }
        if (tmp.right != null && tmp.x == n_node.x)
            if (tmp.right.up != null) {
                tmp.right.up.left = n_node;
                n_node.right = tmp.right.up;
            }
    }

    void addBlockV(Node<Integer> tmp, Node<Integer> n_node) {
        if (head.x > n_node.x) {
            n_node.left = head;
            head.right = n_node;
            head = n_node;
        } else {
            n_node.left = tmp.left;
            tmp.left = n_node;
            n_node.right = tmp;
        }
    }

    void removeBlockAbove(Node<Integer> n_node) {
        if (n_node.left != null)
            n_node.left.right = null;
        if (n_node.right != null)
            n_node.right.left = null;
        if (n_node.down != null)
            n_node.down.up = null;
    }

    void controllBlock(Node<Integer> n_node) {
        //!!!!! eğer n_node.down.data.equals(n_node.data) demeyip "==" kullanınca 
        //!!!!! nedense 128 ve sonrasını karşılaştırmıyor
        while (n_node.down != null && n_node.down.data.equals(n_node.data)) {
            n_node.down.data *= 2;
            removeBlockAbove(n_node);
            n_node = n_node.down;
        }
    }

    void print() {
        Node<Integer> tmp = head;
        Node<Integer> tmpH = head;
        while (tmpH != null) {
            while (tmp != null) {
                System.out.print(tmp.data + " ");
                tmp = tmp.up;
            }
            tmpH = tmpH.left;
            tmp = tmpH;
            System.out.println();
        }
        System.out.println("------------------");
    }
}
