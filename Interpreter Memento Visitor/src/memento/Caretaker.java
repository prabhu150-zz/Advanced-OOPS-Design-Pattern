package memento;

import java.util.ArrayList;

public class Caretaker {

    private ArrayList<Memento> savedStates = new ArrayList<>();

    public void addMemento(Memento m) {
        savedStates.add(m);
    }

    Memento getMemento(int index) {
        return savedStates.get(index);
    }
    // package private access to protect list of states
}