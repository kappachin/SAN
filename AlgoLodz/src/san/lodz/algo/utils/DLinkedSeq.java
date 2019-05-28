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

    public int indexOf(T v) {
        int i = 0;
        Node current = this.head;
        while(current != null)
        {
            if(current.value == v)
                return i;
            current = current.right;
            i++;
        }
        return -1;
    }

    public boolean addBefore(int index,T value) {
        int i = 0;
        Node current = this.head;
        Node newOne = new Node(null,null, value);
        while(null != current) {
            if(i == index){
                newOne.right = current;
                if((newOne.left = current.left) != null){
                    current.left.right = newOne;
                }else {
                    head = newOne;
                }
                current.left = newOne;
                return true;
            }
            current = current.right;
            i++;
        }
        return false;
    }

    public boolean addAfter(int index,T value) {
        int i = 0;
        Node current = this.head;
        Node newOne = new Node(null,null, value);
        while(null != current) {
            if(i == index){
                newOne.left = current;
                if((newOne.right = current.right) != null){
                    current.right.left = newOne;
                }else{
                    tail = newOne;
                }
                current.right = newOne;
                return true;
            }
            current = current.right;
            i++;
        }
        return false;
    }

    public boolean delete(int index) {
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
