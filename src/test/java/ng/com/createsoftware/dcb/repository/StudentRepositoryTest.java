package ng.com.createsoftware.dcb.repository;

import ng.com.createsoftware.dcb.model.Guardian;
import ng.com.createsoftware.dcb.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("vax@mail.com")
                .firstName("Vax")
                .lastName("Bare")
                //.guardianName("Steven")
               // .guardianEmail("steven@mail.com")
               // .guardianMobile("08033505478")
                .build();
        studentRepository.save(student);
    }

    @Test
    void printAllStudents(){
        List<Student> students = studentRepository.findAll();
        System.out.println("Students:  " + students);

    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .email("elizabeth@mail.com")
                .name("Elizabeth")
                .mobile("08035669250")
                .build();
        Student student = Student.builder()
                .emailId("vakpo@mail.com")
                .firstName("Vakpo")
                .lastName("Okagbare")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("Vakpo");
        System.out.println(students);
    }
    ///where name like first name
    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("Vakpo");
        System.out.println(students);
    }
    ///where name is the guardian name
    @Test
    public void printStudentByGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Elizabeth");
        System.out.println(students);
    }

    @Test
    public void getStudentByEmailAddress(){
        Student students =
                studentRepository.getStudentByEmailAddress("vax@mail.com");
        System.out.println(students);
    }
 @Test
    public void getStudentFirstNameByEmailAddress(){
        String firstName =
                studentRepository.getStudentFirstNameByEmailAddress("vax@mail.com");
        System.out.println(firstName);
    }
//Native Query
    @Test
    public void getStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative("vax@mail.com");
        System.out.println("Student = " + student);
    }

    //native named param
    @Test
    public void getStudentByEmailAddressNativeNamedParam(){
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam("vax@mail.com");
        System.out.println("Student = " + student);
    }

    @Test
    public void updateStudentNameByEmailIdTest(){
          studentRepository.updateStudentNameByEmailId("Vaxxinate", "vakpo@mail.com");
    }

}