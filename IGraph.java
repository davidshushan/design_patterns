
import java.util.Collection;

/**
 * This interface declares the common functionality required of a Graph
 */
public interface IGraph<T> {
    public Node<T> getRoot();
    public Collection<Node<T>>  getReachableNodes(Node<T> aNode);
}
