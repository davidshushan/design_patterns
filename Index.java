

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Index{
    public Queue <Index> queue = new LinkedList();
    int row, column;
    boolean isVisited;
     Index parent;
     int distance = -1;
     int value;



    public Index(final int row, final int column) { // final in constructor
        this.row=row;
        this.column=column;
        this.isVisited = false;
    }
    public Index(final int row, final int column, Index parent){
        this.row=row;
        this.column=column;
        this.parent = parent;
        this.isVisited = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return row == index.row &&
                column == index.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "("+row +
                "," + column +
                ')';
    }

    public static void main(String[] args) {
        Index index = new Index(2,3);
        System.out.println(index);
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}
