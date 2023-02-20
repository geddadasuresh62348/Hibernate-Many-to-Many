package hibernate.advanced.GetAndDelete.Demo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.advanced.Entity.Course;
import hibernate.advanced.Entity.Instructor;
import hibernate.advanced.Entity.InstructorDetails;
import hibernate.advanced.Entity.Reviews;
import hibernate.advanced.Entity.Student;

public class Delete_a_CourseDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetails.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Reviews.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		// create session
		Session session=factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
				
			// get the Student "amar" from database
			int courseId=15;
			Course	course = session.get(Course.class, courseId);
			System.out.println("\nBefore Deleting....");
			System.out.println("\nstudent from database : "+course);
			System.out.println("\nstudent Courses from database : "+course.getStudents());
			
			session.delete(course);
			System.out.println("\nAfter Deleting....");
			System.out.println("\nstudent from database : "+course);
			System.out.println("\nstudent Courses from database : "+course.getStudents());
			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			session.close();
			factory.close();
		}
	}
}
