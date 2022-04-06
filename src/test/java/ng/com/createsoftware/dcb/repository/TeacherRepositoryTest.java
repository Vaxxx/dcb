package ng.com.createsoftware.dcb.repository;

import ng.com.createsoftware.dcb.model.Course;
import ng.com.createsoftware.dcb.model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository repository;

    @Test
    public void saveTeacher(){
     Course courseScience = Course.builder()
             .title("Social Sciences")
             .credit(3)
             .build();

        Course courseArts = Course.builder()
                .title("Social Arts")
                .credit(3)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Paul")
                .lastName("Akpobasa")
                //.courses(List.of(courseScience, courseArts))
                .build();

        repository.save(teacher);
    }

}