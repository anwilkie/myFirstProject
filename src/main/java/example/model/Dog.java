package example.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "dogs", schema = "myFirstDatabase")
public class Dog {


    @Column private String name;

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private int id;

    @Column private Integer tailWaggedTimes;

    @Column private Boolean goodDog;
}
