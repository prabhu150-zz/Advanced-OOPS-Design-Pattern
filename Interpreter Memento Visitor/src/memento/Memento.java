package memento;

public class Memento {

    private final Object state;
    // state should not be changed, only accessed

    Memento(Object state) {
        this.state = state;
    }

    Object getSavedState() {
        return state;
    }//only originator can access

}