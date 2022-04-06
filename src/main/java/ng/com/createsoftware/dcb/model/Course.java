package ng.com.createsoftware.dcb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @SequenceGenerator(
            name="course_sequence",
            sequenceName="course_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
            generator="course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credit;

    //defining a bi-directional one to one mapping between
    //course and course material
    //there already exists  a one to one mapping from CourseMaterial
    //to course...now we create a 1-to-1 mapping from course to
    //course Material
 //...................this mappebby course exists in the CourseMaterial
    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name="student_course_map",
            joinColumns = @JoinColumn(
                    name="course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;

    public void addStudents(Student student){
        if(students == null)
            students = new ArrayList<>();
        students.add(student);
    }
}
