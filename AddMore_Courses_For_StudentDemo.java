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

public class AddMore_Courses_For_StudentDemo {

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
			
			// get the Student mary from database
			int studentId=4;
			Student student= session.get(Student.class, studentId);
			System.out.println("student from database : "+student);
			System.out.println("student from database : "+student.getCourses());
			
			// create more courses
			
			Course c1=new Course("Python");
			Course c2=new Course("Springboot");
			Course c3=new Course("C");
						
			// add courses to student with id 4  
			c1.addStudent(student);
			c2.addStudent(student);
			c3.addStudent(student);

			// save the courses
			System.out.println("saving the courses ");
			
			session.save(c1);
			session.save(c2);
			session.save(c3);
		
			
			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			session.close();
			factory.close();
		}
	}
}
