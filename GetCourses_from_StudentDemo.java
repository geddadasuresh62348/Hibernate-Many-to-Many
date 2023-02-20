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

public class GetCourses_from_StudentDemo {

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
			int studentId=4;
			Student student= session.get(Student.class, studentId);
			System.out.println("student from database : "+student);
			System.out.println("student Courses from database : "+student.getCourses());

			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			session.close();
			factory.close();
		}
	}
}
