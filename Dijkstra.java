import java.util.*;

public class Dijkstra {

    /**
     *
     * @param twoDArray
     * @param source
     * @param destination
     *
     *
     *-----Pseudo Code----- (not accurate)
     *
     * index obj contain:
     * row
     * column
     * isvisted ---> using for destination, to check if destination already has been visited or Not
     * previous ---> called 'parent' in the code
     * value - the value of matrix[index.row][index.col]
     * distance --> the distance sum from the source
     *
     * func:
     * 	source prev = null:
     * 	q = new q
     * 	source.distance = 0
     * 	q.push source
     * 	destination path = null;
     *
     * 	while (!q.is enpty)
     * 		temp = q.pop
     * 		temp.get neigbors
     *
     * 		foreach item in neigbors:
     * 			if(item != temp.previous)
     * 				if (item.distance == null)  #make sure this is null!
     * 					item.distance == item.value + item.previous.distance
     * 					// q.push(item)
     *
     * 				#(if we are here it means that item.distance is Not null)
     *
     * 				else:
     * 					tempDistance  = item.value + item.previous.distance
     * 					if tempDistance < item.distance
     * 						item.distance = tempDistance
     * 						item.previous = temp
     *
     * 			if item Not in q:
     * 				q.push(item)
     *
     * 			if item.Index == destination:
     * 				destination path = item
     *
     * 	if destination path != null:
     * 		temp destination path = destination path;
     * 		array list final path;
     * 		while (temp destination path != source)
     * 			final path.add(temp destination path)
     * 			temp destination path = temp destination path.previous
     *
     * 	return final path;
     *
     */

    public static void DijkstraSearch(int [][] twoDArray, Index source, Index destination){
        Matrix matrix = new Matrix(twoDArray);

        source.distance = 0;
//        source.parent = source;
        source.parent = null;
        Queue<Index> workingQ = new LinkedList();
        Set<Index> visited = new LinkedHashSet<>();
        Set<Index> finished = new LinkedHashSet<>();
        Index tempShortestPath = null;
        ArrayList<Index> finalPath = new ArrayList<>();
        Stack<Index> finalStack= new Stack<>();
        Index destinationPath = null;

        workingQ.add(source);

        while (!workingQ.isEmpty()){
            Index tempIndex =  workingQ.remove();
            visited.add(tempIndex);
            finished.add(tempIndex);
            ArrayList<Index> neighbors =(ArrayList<Index>) matrix.getNeighbors(tempIndex);

            for (Index item : neighbors) {
                    item.parent = tempIndex;

                    item.value =  twoDArray[item.row][item.column];
                Index tempItem = visited.stream().filter(index -> index.equals(item)).findAny().orElse(null);
//                    System.out.println("tempItem:" + tempItem);
                if (tempItem != null){
                    item.distance = tempItem.distance;

                }
                if (!item.equals(tempIndex.parent)) {



                    if (item.distance == -1) {
                        item.distance = (item.value + item.parent.distance);
                        System.out.println("item: " + item + ", item.distance: " + item.distance);
//                        workingQ.add(item);
                    }

                    else {
                        int tempDistance = item.value + item.parent.distance;
                        if (tempDistance < item.distance){
                            item.distance = tempDistance;
                            item.parent = tempIndex;


                            System.out.println("from else: " + " item: " + item + ", item.distance: " + item.distance + ", item.parent: " + item.parent);
                        }

                    }

                }
                if (item.equals(destination)){
                    if (!destination.isVisited()) {
                        destinationPath = item;
                        destination.setVisited(true);
                    }
                    else if ( item.distance < destinationPath.distance){
                        destinationPath = item;
                    }
                }

//                if (!finished.contains(item) ){      // work but enter more indexes to the queue
                if (!finished.contains(item) && destinationPath ==null){
                    workingQ.add(item);
                    visited.add(item);
                }
          }


        }
        if (destinationPath != null){
            System.out.println("the distance from source distance: " + destinationPath.distance);
            while (!destinationPath.equals(source)){
                Index tempIndex = destinationPath;
                finalStack.add(tempIndex);
                destinationPath = destinationPath.parent;
            }
            finalStack.add(destinationPath);
           while (!finalStack.isEmpty()){
               finalPath.add(finalStack.pop());
           }
        }
        System.out.println(finalPath.toString());

    }


    public static void main(String[] args) {
        int [][] twoDArray = {
                {100,200,100},
                {500,900,300},
                {100,100,100}

        };

        Index source = new Index(0,0);
        Index destination = new Index(2,2);
        DijkstraSearch(twoDArray,source,destination);
    }
}
