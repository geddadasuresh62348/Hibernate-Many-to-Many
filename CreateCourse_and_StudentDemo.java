package hibernate.advanced.CreationDemo;

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

public class CreateCourse_and_StudentDemo {

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
				
			// create a Course 
			Course course=new Course("Java");
			System.out.println("\nSaved course : "+course);
			
			// save the Course
			session.save(course);
			
			// create a Students List
			Student s1=new Student("amar","akbhar","akm@gmail.com");
			Student s2=new Student("ameer","anub","anb@gmail.com");
			Student s3=new Student("babar","ajam","bajam@gmail.com");
									
			List<Student> students=Arrays.asList(s1,s2,s3);
			System.out.println("\nCourse Students : "+course.getStudents());

			// add students to the Course
//			course.setStudents(students);
			course.addStudent(s1);
			course.addStudent(s2);
			course.addStudent(s3);
			// save the Course
			System.out.println("\n Saving the Course and ");
			session.save(course);
			session.save(s1);
			session.save(s2);
			session.save(s3);
			System.out.println("\n After saving ");
			System.out.println(course);
			System.out.println(course.getStudents());
			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			session.close();
			factory.close();
		}
	}
}
