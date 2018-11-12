package visitor;

public interface Visitable {
    void accept(Visitor visitor); // indicates if a data structure can be visited
}
