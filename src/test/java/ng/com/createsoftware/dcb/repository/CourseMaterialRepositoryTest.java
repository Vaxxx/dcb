package ng.com.createsoftware.dcb.repository;

import ng.com.createsoftware.dcb.model.Course;
import ng.com.createsoftware.dcb.model.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){
        Course course = Course.builder()
                .title("Java 104")
                .credit(5)
                .build();

        CourseMaterial material = CourseMaterial.builder()
                .url("www.geekzmeet.com")
                .course(course)
                .build();
        repository.save(material);
    }

    @Test
    public void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = repository.findAll();
        System.out.println(courseMaterials);
    }


}