

import java.util.*;
import java.util.stream.Collectors;

public class MatrixAsGraph implements IGraph<Index> {
    private Matrix innerMatrix;
    private Index source;

    public MatrixAsGraph(Matrix matrix, Index source) {
        this.innerMatrix = matrix;
        this.source = source;
    }

    public Index getSource() {
        return source;
    }

    public MatrixAsGraph(Matrix matrix) {
        this(matrix, new Index(0, 0));
    }

    public Matrix getInnerMatrix() {
        return innerMatrix;
    }

    public void setInnerMatrix(Matrix innerMatrix) {
        this.innerMatrix = innerMatrix;
    }

    public void setRoot(Index root) {
        this.source = root;
    }

    @Override
    public Node<Index> getRoot() {
        return new Node<>(this.source);
    }

    public void setSource(Index source) {
        if (source != null)
            if ((source.row >= 0 && source.row < innerMatrix.primitiveMatrix.length) &&
                    /*
                    we know that matrix  is of size n X n
                    innerMatrix.primitiveMatrix[0].length - number of column in row 0 == number
                    of columns of all others rows
                     */
                    (source.column >= 0 && source.column < innerMatrix.primitiveMatrix[0].length)) {
                this.source = source;
            }
    }


    /*
    1 0 1
    1 1 0
    1 1 1

   [1,0,1,1,0,1]
   [1,1,1,1,0,1]
   [T,,T,T,,T]
   [T,T,T,T,,T]
    A reachable node is a node that wrap a neighboring index and whose value is 1
     */
    @Override
    public List<Node<Index>> getReachableNodes(Node<Index> aNode) {
        List<Node<Index>> reachableNodes = new ArrayList<>();

        // for each neighbor if the extracted index
        for (Index index : this.innerMatrix.getNeighbors(aNode.getData())) {
            // getValue can return 0 or 1;
//            index.setVisited(true);
            if (this.innerMatrix.getValue(index) == 1) {
                // found a neighbor whose value is 1. wrap index in a Node, mark aNode as parent
                Node<Index> reachableNode = new Node<>(index, aNode);
//                reachableNode.setVisited(true); // does this help our algorithm?
                reachableNodes.add(reachableNode);
            }
        }
        return reachableNodes;
    }



}


