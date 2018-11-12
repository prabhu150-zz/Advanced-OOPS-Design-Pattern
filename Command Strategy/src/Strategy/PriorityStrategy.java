package Strategy; /**
 *
 */

import Model.Student;

/**
 * @author Abhi
 */

public interface PriorityStrategy {

    boolean comparePriority(Object element1, Object element2);

}
