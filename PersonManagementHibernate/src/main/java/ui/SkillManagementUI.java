package ui;

import dao.PersonDao;
import dao.SkillDao;
import model.DepartmentModel;
import model.PersonModel;
import model.SkillModel;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SkillManagementUI {
    Scanner scanner = new Scanner(System.in);
    private SkillDao skillDao = new SkillDao();
    private PersonDao personDao = new PersonDao();

    public void startSkillManagementUI() {
        while (true) {
            menu();
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1)
                addSkill();
            else if (option == 2)
                viewAllSkills();
            else if (option == 3)
                updateSkill();
            else if (option == 4)
                deleteSkill();
            else if (option == 5)
                setSkillToPerson();
            else if (option == 0)
                ProgramUI.startProgramUI();
        }
    }

    public void setSkillToPerson() {
        List<PersonModel> people = personDao.getAllPeople();
        people.forEach(person -> System.out.println(person.getId()
                + "." + person.getName()
                + " - " + person.getAge()));
        System.out.print("Enter id of person : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        PersonModel personModel = personDao.findPersonById(id);
        viewAllSkills();
        System.out.println("Enter id of skill : ");
        int pick = scanner.nextInt();
        scanner.nextLine();
        SkillModel skillModel = skillDao.findSkillById(pick);
        List<SkillModel> skills = personModel.getSkills();
        skills.add(skillModel);
        personDao.updatePerson(personModel);
    }

    private void deleteSkill() {
        viewAllSkills();
        System.out.print("Enter id to delete : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SkillModel skill = skillDao.findSkillById(id);
        skillDao.deleteSkill(skill);
    }

    private void viewAllSkills() {
        List<SkillModel> skills = skillDao.getAllSkills();
        skills.forEach(skill -> System.out.println((skill.getId())
                + "." + skill.getName()));
    }

    private void updateSkill() {
        viewAllSkills();
        System.out.print("Enter id to update : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SkillModel skillModel = skillDao.findSkillById(id);
        System.out.print("Enter new name : ");
        skillModel.setName(scanner.next());
        skillDao.updateSkill(skillModel);
    }

    private void addSkill() {
        SkillModel skillModel = new SkillModel();
        System.out.print("Enter name : ");
        skillModel.setName(scanner.next());
        skillDao.addSkill(skillModel);
    }


    private void menu() {
        System.out.println("SKILLS MANAGEMENT \n0.Back \n1.Add Skill \n2.View Skills " +
                "\n3.Update Skill \n4.Delete Skill \n5.Assign Skill \n");
    }
}
