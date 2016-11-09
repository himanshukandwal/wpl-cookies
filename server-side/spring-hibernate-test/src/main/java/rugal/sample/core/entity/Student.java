package rugal.sample.core.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author rugal
 */
@Data
@Entity(name = "student")
public class Student
{

    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    @Column(length = 20)
    private String name;

    @Column
    private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
   
}
