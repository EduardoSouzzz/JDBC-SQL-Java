package application;

import java.util.List;
import java.util.Scanner;

import model.dao.CourseDao;
import model.dao.DaoFactory;
import model.entities.Course;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		CourseDao courseDao = DaoFactory.createCourseDao();
		
		/*
		System.out.println("\n=== TEST 1 - insert course ===");
		
		Course c = new Course(null, "PHP", 32, 105.99);
		courseDao.insert(c);
		System.out.println("ADD com sucesso novo curso: " + c.getId());
		*/
		
		
		System.out.println("=== TEST 2 - findById course ===");
		Course course = courseDao.findById(3);
		System.out.println(course);
		
		
		
		/*
		System.out.println("\n=== TEST 3 - findALL course ===");
		List<Course> list = courseDao.findAll();
		for (Course obj : list) {
			System.out.println(obj);
		}
		*/
		
		
		System.out.println("\n=== TEST 4 - UPDATE course ===");
		course = courseDao.findById(7);
		if (course != null) {
			course.setName("Assembly");
			course.setWorkload(70);
			course.setPrice(99.00);
			courseDao.update(course);
			System.out.println("Curso atualizado com sucesso!");
		} else {
			System.out.println("ERRO ao encontrar o curso.");
		}
				

		System.out.println("\n=== TEST 5 - DELETE course ===");
		System.out.println("Digite o id que deseja remover: ");
		int id = sc.nextInt();
		courseDao.deleteById(id);
		System.out.println("ID deletado com sucesso!");
		sc.close();
	}

}
