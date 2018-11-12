package memento;

public class Originator {
    private Object state;

    public void set(Object newState) {
        this.state = newState;
    }

    public Memento storeInMemento() {
        return new Memento(state); // Creates a new Memento with a new state
    }

    public Object restoreFromMemento(Memento memento) {
        state = memento.getSavedState();
        return state;// Gets the state currently stored in memento
    }
}