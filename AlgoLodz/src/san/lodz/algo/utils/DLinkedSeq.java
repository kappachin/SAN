package san.lodz.algo.utils;

import san.lodz.math.Binary;

public class DLinkedSeq<T> {

    private Node head;

    private Node tail;

    public void addHead(T x) {
        Node node = new Node(null, null, x);
        if (isEmpty()) {
            this.head = this.tail = node;
        } else {
            this.head.left = node;
            node.right = this.head;
            this.head = node;
        }
    }

    public int indexOf(T node) {
        int i = 0;
        Node current = this.head;
        while (current != null) {
            if (current.value == node)
                return i;
            current = current.right;
            i++;
        }
        return -1;
    }


    public void addTail(T x) {
        Node node = new Node(null, null, x);
        if (isEmpty()) {
            this.head = this.tail = node;
        } else {
            this.tail.right = node;
            node.left = this.tail;
            this.tail = node;
        }
    }

    public T head() {
        if (isEmpty())
            throw new UnsupportedOperationException();

        return this.head.value;
    }

    public T tail() {
        if (isEmpty())
            throw new UnsupportedOperationException();

        return this.tail.value;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void forEach(Binary<T, Boolean, Void> body) {
        Node current = this.head;
        while(null != current) {
            body.call(current.value, current == this.tail);
            current = current.right;
        }
    }


    public boolean addBefore(int index, T value) {
        int i = 0;
        Node obecny = this.head;
        Node nowy = new Node(null, null, value);
        while (null != obecny) {
            if (i == index) {
                nowy.right = obecny;
                if ((nowy.left = obecny.left) != null) {
                    obecny.left.right = nowy;
                } else {
                    head = nowy;
                }
                obecny.left = nowy;
                return true;
            }
            obecny = obecny.right;
            i++;
        }
        return false;
    }

    public boolean addAfter(int index, T value) {
        int i = 0;
        Node obecny = this.head;
        Node nowy = new Node(null, null, value);
        while (null != obecny) {
            if (i == index) {
                nowy.left = obecny;
                if ((nowy.right = obecny.right) != null) {
                    obecny.right.left = nowy;
                } else {
                    tail = nowy;
                }
                obecny.right = nowy;
                return true;
            }
            obecny = obecny.right;
            i++;
        }
        return false;
    }

   /* public boolean delete(int index) {
        int i = 0;
        Node current = this.head;
        while(null != current) {
            if(i == index){
                if(current.left==null&&current.right==null)
                {
                    head = tail = null;
                }else if(current.left!=null&&current.right!=null){
                    current.left.right = current.right;
                    current.right.left = current.left;
                }else if(current.left==null&&current.right!=null){
                    current.right.left = null;
                    head = current.right;
                }else if(current.left!=null&&current.right==null){
                    current.left.right = null;
                    tail = current.left;
                }
                return true;
            }
            current = current.right;
            i++;
        }
        return false;
    }
*/
    public void forEachReverse(Binary<T, Boolean, Void> body) {
        Node current = this.tail;
        while(null != current) {
            body.call(current.value, current == this.head);
            current = current.left;
        }
    }

    public String asString() {
        StringBuilder buf = new StringBuilder("(");
        this.forEach((e, isLast) -> {
            buf.append(e);
            if (!isLast) {
                buf.append(",");
            }
            return null;
        });
        return buf.append(")").toString();
    }

    private class Node {
        Node left;
        Node right;
        T value;

        private Node(Node left, Node right, T value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }
}
