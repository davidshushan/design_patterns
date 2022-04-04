import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;

public class Demo {


    public static <T> void main(String[] args) {
        int[][] twoDArray =  {
                {1,0,0},     //q.add(0,0)
                {1,1,0},     //(0,0)(1,0)
                {1,1,1}

        };

        Matrix matrix = new Matrix(twoDArray);
        System.out.println("matrix:");
        System.out.println( matrix);
        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(matrix);
        System.out.println("matrixAsGraph.getInnerMatrix():");
        System.out.println();
        System.out.println(matrixAsGraph.getInnerMatrix().toString());
        System.out.println();
        Index source = new Index(0,0);
        Index destination = new Index(2,0);
        System.out.println("getNeighbors(0,0)");
        System.out.println(matrixAsGraph.getInnerMatrix().getNeighbors(source));


        matrixAsGraph.setRoot(source);
        Collection<Node<Index>> collection = matrixAsGraph.getReachableNodes(matrixAsGraph.getRoot());

        System.out.println();

        System.out.println("getReachableNodes(matrixAsGraph.getRoot():");
        for ( Node <Index> item:collection) {
            System.out.print(item.getData() + ", ");
        }

        DfsVisit dfsVisit = new DfsVisit();

        Collection<Index> collection1 = dfsVisit.traverse(matrixAsGraph);

        System.out.println();
        System.out.println("**********");
        System.out.println("dfsVisit.traverse(matrixAsGraph)");
        for ( Index item:collection1) {
            System.out.print(item.toString() + ", ");
        }

        System.out.println();
        System.out.println("**********");
        System.out.println("dfsVisit.traverseAllMatrix(matrixAsGraph)");
        HashSet<HashSet<Index>> hashSets = dfsVisit.traverseAllMatrix(matrixAsGraph);

        for (HashSet <Index> item:hashSets) {
            System.out.print(item.toString() + " ");
        }
        System.out.println();

        BfsVisit bfsVisit = new BfsVisit();
        bfsVisit.traverse(matrixAsGraph,source, destination);
        System.out.println("**********");
        System.out.println("bfsVisit.traverse(matrixAsGraph, source, destination)");
        System.out.println("source: " + source);
        System.out.println("destination: " + destination);
//        bfsVisit.printQueue(bfsVisit.results);
        System.out.println("Shortest Paths: " + bfsVisit.results.toString());
        System.out.println("**************");

//        System.out.println(bfsVisit.results.get(0).toString());
//        System.out.println(bfsVisit.results.get(1).toString());


    }
}
