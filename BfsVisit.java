
import java.util.*;

public class BfsVisit<T> {


    ArrayList<Queue<Index>> results = new ArrayList<>();
    public BfsVisit(){
    }


public ArrayList<Queue<Index>> traverse(IGraph<T> someGraph, Index source, Index destination){
        Queue<Index> workingQueue = new LinkedList<>();
        Set<Index> finished = new LinkedHashSet<>();
        someGraph.getRoot().setData((T) source);
        workingQueue.add((Index) someGraph.getRoot().getData());
        while(!workingQueue.isEmpty()){
            Index removed = workingQueue.remove();
            removed.queue.add(removed);
            finished.add(removed);
            Node<T> node = new Node<>();
            node.setData((T)removed);
            Collection<Node<T>> reachableNodes = someGraph.getReachableNodes(node);
            for(Node<T> reachableNode: reachableNodes){
                //option 1
                if (reachableNode.getData().equals(destination) ){
                    ((Index)node.getData()).queue.add((Index)reachableNode.getData()); // add index that is equals to destination to the path of current node (removed)
                    results.add(((Index) node.getData()).queue); // add node path to results path list
                }
                if(!finished.contains(reachableNode.getData())
                        && !workingQueue.contains(reachableNode.getData())){
                    workingQueue.add((Index) reachableNode.getData());
                    ((Index) reachableNode.getData()).queue.addAll(removed.queue); //add to each neighbor the path of his parent(= 'removed', and also: 'node')
                }
            }
        }

        this.results = shortestPaths(results);

         return this.results;
    }




    private ArrayList<Queue<Index>> shortestPaths(ArrayList<Queue<Index>> allPaths){
//
        int shortest  = allPaths.get(0).size();
        for (int i = 0; i < allPaths.size(); i++) { //find the size of shortest path in collection
            if (allPaths.get(i).size() < shortest){
                shortest = allPaths.get(i).size();
            }
        }

        ArrayList<Queue<Index>> shortestPaths = new ArrayList<>();
        for (Queue<Index> q: allPaths) {
            if (q.size() == shortest){
                shortestPaths.add(q);
            }
        }
        return shortestPaths;
    }



}
