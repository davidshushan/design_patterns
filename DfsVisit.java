
import java.util.*;

public class DfsVisit<T> {
    /*

          example:
         [1,0,1,1,0,1]
         [1,1,1,1,0,1]
         example:
         [1,0,1,1]
         [0,1,0,1]

מחסנית


סט finished
(0,0)
(1,0)
(1,1)
(1,2)
(1,3)
(0,3)
(0,2)
רשימת שכנים


         example:
         [1,0,1,1,0]
         [1,1,1,1,0]

         */
    /*
       V נכניס למחסנית את הקודקוד שממנו מתחילים לטייל
     V ֿכל עוד המחסנית לא ריקה:
       V נוציא את הקודקוד בראש המחסנית ונשמור אותו למשתנה
       V את הקודקוד הזה שהיה בראש המחסנית נכניס לסט של אלה שסיימנו איתם
        V  נקרא למתודה getReachableNodes כדי להבין מיהם הקודקודים שישיגים ממנו
       V עבור כל קודקוד ישיג שגיליתי:
         V   אם השכן שלי לא נמצא במחסנית וגם קודקוד ישיג לא נמצא בסט של finished:
             מכניס את הקודקוד הישיג למחסנית
     */
    // You will need to implement an algorithm called BFS (Breadth-First Search)
    private Stack<Node<T>> workingStack;
    private Set<Node<T>> finished2;

    public DfsVisit(){
        workingStack = new Stack<>();
        finished2 = new LinkedHashSet<>();
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
        return finished;
    }

    public HashSet<HashSet<Index>> traverseAllMatrix(MatrixAsGraph matrixAsGraph) {
        Collection<Index> indexesTemp = matrixAsGraph.getInnerMatrix().getAllIndexesWithValue();
        LinkedList<Index> indexesList = new LinkedList<>(indexesTemp);
        HashSet<HashSet<Index>> setOfHashSet = new HashSet<>();
        matrixAsGraph.setRoot(indexesList.pop());
        HashSet<Index> checkedIndexesList = (HashSet<Index>) traverse((IGraph<T>) matrixAsGraph);
        setOfHashSet.add(checkedIndexesList);
        while (!indexesList.isEmpty()) {
            Index removed = indexesList.pop();
            if (!checkedIndexesList.contains(removed)) {
                matrixAsGraph.setRoot(removed);
                HashSet<Index> tempCollection = (HashSet<Index> )traverse((IGraph<T>) matrixAsGraph); // collection of index = error

                if (!setOfHashSet.contains(tempCollection)) {setOfHashSet.add( tempCollection );}

            }
        }

        return setOfHashSet;
    }




}
