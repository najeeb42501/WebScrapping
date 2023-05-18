package webscrap;

public class Node <T,E>{
    T key;
    E value;
    Node<T,E> next;

    public Node(T key, E value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +"Repeat: " + key + " Word :"+ value+" }";
    }
}
