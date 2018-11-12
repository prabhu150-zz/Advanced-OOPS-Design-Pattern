package Strategy;

import Model.Student;
import Strategy.PriorityStrategy;

public class OrderByGpa implements PriorityStrategy {

    @Override
    public boolean comparePriority(Object element1, Object element2) {
        Student student1 = (Student) element1, student2 = (Student) element2;
        if (student1.getGpa() <= student2.getGpa())
            return true;
        return false;
    }
}
