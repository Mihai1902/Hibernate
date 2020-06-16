package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "people")
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;

    @ManyToOne(cascade = CascadeType.ALL)
    private DepartmentModel departmentModel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    private List<SkillModel> skills = new ArrayList<>();


    public DepartmentModel getDepartmentModel() {
        return departmentModel;
    }

    public List<SkillModel> getSkills() {
        return skills;
    }

    public void setDepartmentModel(DepartmentModel departmentModel) {
        this.departmentModel = departmentModel;
    }

    public void setSkills(List<SkillModel> skills) {
        this.skills = skills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

}
