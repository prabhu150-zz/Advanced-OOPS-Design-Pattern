package Strategy;

import Model.Student;
import Strategy.PriorityStrategy;

public class OrderByBoth implements PriorityStrategy {


	@Override
	public boolean comparePriority(Object element1, Object element2) {

		Student student1 = (Student) element1, student2 = (Student) element2;

		final int MAX_UNITS = 150;
		final double MAX_GPA = 4.0, UNITS_WEIGHT = 0.7, GPA_WEIGHT = 0.3;

		double units_norm = UNITS_WEIGHT / MAX_UNITS , gpa_norm = GPA_WEIGHT / MAX_GPA;

		double priority_first = units_norm *  student1.getUnits() + gpa_norm *  student1.getGpa() ,
				priority_second = units_norm *  student2.getUnits() + gpa_norm *  student2.getGpa();

		if (priority_first <= priority_second)
			return true;
		return false;
	}
}
