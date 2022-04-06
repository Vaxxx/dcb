package ng.com.createsoftware.dcb.repository;

import ng.com.createsoftware.dcb.model.Course;
import ng.com.createsoftware.dcb.model.Student;
import ng.com.createsoftware.dcb.model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CourseRepositoryTest {

          @Autowired
           private CourseRepository repository;

          @Test
          public void getAllCourses(){
              List<Course> courses = repository.findAll();
              System.out.println(courses);
          }

          @Test
        public void saveCourseWithTeacher(){
              Teacher teacher  = Teacher.builder()
                      .firstName("Charles")
                      .lastName("Obekpa")
                      .build();

               Course course = Course.builder()
                       .title("Java How to Program")
                       .credit(9)
                       .teacher(teacher)
                       .build();
               repository.save(course);
          }
        //pagination
          @Test
        public void findAllPagination(){
              Pageable firstPageWithThreeRecords =
                      PageRequest.of(0,3);
              Pageable secondPageWithTwoRecords =
                      PageRequest.of(1,2);
              List<Course>courses =
                      repository.findAll(firstPageWithThreeRecords).getContent();

              long totalElements =
                      repository.findAll(firstPageWithThreeRecords).getTotalElements();

              long totalPages =
                      repository.findAll(firstPageWithThreeRecords).getTotalPages();

              System.out.println("Total Pages => " + totalPages);
              System.out.println("Total Elements => " + totalElements);
              System.out.println("Courses => " + courses);
          }

          //pagination and sorting
          @Test
          public void findAllSorting(){
              Pageable sortByTitle =
                      PageRequest.of(
                              0,2, Sort.by("title")
                      );

              Pageable sortByCreditDesc =
                      PageRequest.of(
                              0, 2, Sort.by("credits").descending()
                      );
              Pageable sortByTitleAndCreditDescending =
                      PageRequest.of(
                              0,2,Sort.by("title")
                              .descending().and(Sort.by("credit"))
                      );

              List<Course> courses =
                      repository.findAll(sortByTitle).getContent();

              System.out.println(courses);
          }

          //find by title contianign
    @Test
    public void printFindByTitleContaining(){
              Pageable firstPageTenRecords =
                      PageRequest.of(0, 10);

              List<Course> courses =
                      repository.findByTitleContaining("J",
                               firstPageTenRecords).getContent();

        System.out.println("Courses => " + courses);
    }

    @Test
    public  void saveCourseWithStudentAndTeacher(){

              Teacher teacher = Teacher.builder()
                      .firstName("Ryan")
                      .lastName("Okagbare")
                      .build();


              Student student = Student.builder()
                      .firstName("Nathan")
                      .lastName("George")
                      .emailId("ng@gmail.com")
                      .build();

              Course course = Course.builder()
                      .title("DSA")
                      .credit(10)
                      .teacher(teacher)
                      .build();

              course.addStudents(student);
              repository.save(course);
    }
}