package wood.mike.hibernate;

import wood.mike.hibernate.dao.CourseDao;
import wood.mike.hibernate.dao.InstructorDao;
import wood.mike.hibernate.entity.Course;
import wood.mike.hibernate.entity.Instructor;

public class ManApp {
	InstructorDao instructorDao;

	CourseDao courseDao;

	public static void main(String[] args) {
		new ManApp();
	}

	public ManApp() {
		instructorDao = new InstructorDao();
		courseDao = new CourseDao();
		tests();
	}

	private void tests() {
		testCreateAll();
	}

	private void testCreateAll() {
		Instructor instructor = new Instructor("Gordon", "Grey", "gordon@earth.com");
		instructorDao.saveInstructor(instructor);

		Course tempCourse1 = new Course("Learn Spring Boot");
		instructor.getCourses().add(tempCourse1);

		Course tempCourse2 = new Course("Learn hibernate");
		instructor.getCourses().add(tempCourse2);

		instructorDao.saveInstructor(instructor);

		instructor.getCourses().remove(tempCourse2);

		instructorDao.updateInstructor(instructor);

		Instructor instructorFromDb = instructorDao.getInstructor(instructor.getId());
		assert instructorFromDb.getCourses().size() == 1;
	}
}
