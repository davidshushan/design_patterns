
import org.jetbrains.annotations.NotNull;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class Node<T> implements Serializable {
    private static final Long serialVersionUID = 1L;

    private T data;
    private Node<T> parent;
    private int distance;
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    private boolean isVisited;

    public Node(T data, Node<T> parent){
        this.data = data;
        this.parent = parent;
        this.isVisited = false;
        this.distance = -1; //like null
    }

    public Node(T data){
        this(data,null);
    }

    public Node(){

    }

    public T getData() {
        return data;
    }

    public void setData(@NotNull T data) {
        this.data = data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(@NotNull Node<T> parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> node = (Node<?>) o;
            return data.equals(node.data) &&
                    parent.equals(node.parent);
    }

    /*
    question ? ifTrue : ifFalse
     */
    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
}

