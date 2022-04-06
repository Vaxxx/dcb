package ng.com.createsoftware.dcb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name="students"
)
public class Student {

    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName="student_sequence",
            allocationSize=1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long studentId;

    private String firstName;
    private String lastName;

    @Column(
            name="email",
            nullable=false
    )
    private String emailId;

    @Embedded
    private Guardian guardian;

}
