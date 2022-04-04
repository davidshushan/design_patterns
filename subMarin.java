import java.util.*;

public class subMarin<T> {


    // is a valid cell or not.
    static int ROW = 0;
    static int COL = 0;


    static boolean isValid(Index index) // check if index is in valid position in matrix. MAKE SURE! - that row and col have value;
    {
        // return true if row number and
        // column number is in range
        return (index.row >= 0) && (index.row < ROW) &&
                (index.column >= 0) && (index.column < COL);
    }

    public Collection<Index> traverse(IGraph<T> someGraph){

        Stack<Index> MyWorkingStack = new Stack<>();
        Set<Index> finished = new LinkedHashSet<>();

        MyWorkingStack.push((Index) someGraph.getRoot().getData());
        while(!MyWorkingStack.isEmpty()){
            Index removed = MyWorkingStack.pop();

            finished.add(removed);
            Node<T> node = new Node<>();
            node.setData((T)removed);
            Collection<Node<T>> reachableNodes = someGraph.getReachableNodes(node);
            for(Node<T> reachableNode: reachableNodes){
                if(!finished.contains(reachableNode.getData())
                        && !MyWorkingStack.contains(reachableNode.getData())){
                    MyWorkingStack.push((Index) reachableNode.getData());
                }
            }
        }
        Set<Index> blackSet = new LinkedHashSet<>();
        for(Index index: finished){ //לא רוצים להחזיר את הנודים אלא את האינדקסים שלהם ויוצרים רשימה חדשה
            blackSet.add(index);
        }
        ArrayList <Index> listOfIndexes = new ArrayList<>();
        for (Index index: finished) {

//            index.setVisited(true); // before we retutn the result we set is visited to be true for allSubMarines function
            listOfIndexes.add(index);
        }
        // we only have the finished set. finished:
        finished.clear();
        return listOfIndexes;
    }

    public static int allSubMarines(int[][] twoDArray){
        Matrix matrix = new Matrix(twoDArray);
        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(matrix);
        ArrayList<ArrayList <Index>> listOfArrayList = new ArrayList<>();
        ArrayList <Index> listOfIndexesWithValue = new ArrayList<>();
        Queue<Index> workingQueue = new LinkedList<>();

        listOfIndexesWithValue = (ArrayList<Index>)matrixAsGraph.getInnerMatrix().getAllIndexesWithValue();

         subMarin subMarinType = new subMarin<>();
        for (Index index:listOfIndexesWithValue) {
            if (!workingQueue.contains(index)) {
                matrixAsGraph.setRoot(index);

                ArrayList<Index> traverseList = new ArrayList<>();
                traverseList = (ArrayList<Index>) subMarinType.traverse(matrixAsGraph);
                workingQueue.addAll(traverseList);
                listOfArrayList.add(traverseList);
                System.out.println("current traverseList: " + traverseList.toString());

            }
        }
        System.out.println("listOfArrayList: " + listOfArrayList.toString());
        ROW = twoDArray.length;
        COL = twoDArray[0].length;


        int subMarinCounter = 0;
        for (int i = 0; i < listOfArrayList.size(); i++) {
            if (validSubMarin(listOfArrayList.get(i))){
                subMarinCounter++;
            }
        }

        System.out.println();

        return subMarinCounter;

    }


    public static boolean validSubMarin(ArrayList<Index> subMarin){
        if (subMarin.size() < 2){
            return false;
        }
        for (Index index: subMarin) {

            Index right = new Index( index.row, index.column +1);
            Index left = new Index( index.row, index.column-1);

            Index down = new Index( index.row+1, index.column);
            Index up = new Index( index.row-1, index.column);

            Index rightUp = new Index( right.row-1, right.column);
            Index leftUp = new Index( index.row-1, index.column-1);

            Index downRight = new Index( right.row+1, right.column);
            Index downLeft = new Index( index.row+1, index.column-1);



            if (isValid(right) && subMarin.contains(right)){


                if (isValid(down) && subMarin.contains(down)){
                    if (!subMarin.contains(downRight)){
                        return false;
                    }
                }
                if (isValid(downRight) && subMarin.contains(downRight)){
                    if (!subMarin.contains(down)){
                        return false;
                    }
                }
                if (isValid(rightUp) && subMarin.contains(rightUp)){
                    if (!subMarin.contains(up)){
                        return false;
                    }
                }
            }
            if (isValid(down) && subMarin.contains(down)){
                if (isValid(downRight) && subMarin.contains(downRight)){
                    if (!subMarin.contains(right)){
                        return false;
                    }
                }

            }


            // ---diagonals also check minimum distance of 1 index between two subMarin! ---
            // diagonal 1
            if (isValid(downLeft) && subMarin.contains(downLeft)){
//                if (isValid(downRight) && !subMarin.contains(downRight) && isValid(left) && !subMarin.contains(left)){
                    if (isValid(downRight) && !subMarin.contains(down)){
                    return false;


                }

            }
            // diagonal 2
            if (isValid(downRight) && subMarin.contains(downRight)){
//                if (isValid(downLeft) && !subMarin.contains(downLeft) && isValid(right) && !subMarin.contains(right)){
                    if (isValid(downLeft) && !subMarin.contains(downLeft) ){
                    return false;
                }

            }
            // diagonal 3 rightUp
            if (isValid(rightUp) && subMarin.contains(rightUp)){
                if (isValid(up) && !subMarin.contains(up) ){
                    return false;
                }

            }
            // diagonal 4 leftUp
            if (isValid(leftUp) && subMarin.contains(leftUp)){
                if (isValid(up) && !subMarin.contains(up) ){
                    return false;
                }

            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] twoDArray =  {
                {1,0,1,1},
                {0,0,0,1},
                {0,0,0,0},
                {1,1,0,1}
        };
        int[][] twoDArray2 =  {
                {1,1,0,0},
                {1,1,0,0},
                {0,0,1,1},
                {0,0,1,1}
        };

       // example 1
        int numberOfSubMarines = allSubMarines(twoDArray);
        // example 2
        int numberOfSubMarines2 = allSubMarines(twoDArray2);

        Matrix matrix = new Matrix(twoDArray);
        System.out.println("matrix:");
        System.out.println( matrix);
        System.out.println("number Of Valid SubMarines in matrix: " + numberOfSubMarines);
        System.out.println();

        //example 2

        Matrix matrix2 = new Matrix(twoDArray2);
        System.out.println("matrix2:");
        System.out.println( matrix2);
        System.out.println("number Of Valid SubMarines in matrix2: " + numberOfSubMarines2);
    }
}
