package ng.com.createsoftware.dcb.repository;

import ng.com.createsoftware.dcb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student>findByFirstName(String firstName);
    List<Student>findByFirstNameContaining(String name);
    List<Student>findByLastNameNotNull();
    List<Student> findByGuardianName(String guardianName);

    @Query("select s from Student s where s.emailId=?1")
    Student getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from Student s where s.emailId=?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    @Query(
            value="select * from students s where s.email = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);


    //Native Named Param
    @Query(
            value="select * from students s where s.email = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId")String emailId);


    @Modifying
    @Transactional
    @Query(
            value="update students set first_name = ?1 where email = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);

}
