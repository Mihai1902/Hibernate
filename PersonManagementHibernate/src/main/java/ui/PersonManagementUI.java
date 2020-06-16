package ui;

import dao.DepartmentDao;
import dao.PersonDao;
import model.DepartmentModel;
import model.PersonModel;
import model.SkillModel;

import java.util.List;
import java.util.Scanner;

public class PersonManagementUI {
    Scanner scanner = new Scanner(System.in);
    private PersonDao personDao = new PersonDao();
    private DepartmentDao departmentDao = new DepartmentDao();

    public void startPersonManagementUI() {
        while (true) {
            menu();
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1)
                addPerson();
            else if (option == 2)
                viewAllPeople();
            else if (option == 3)
                updatePerson();
            else if (option == 4)
                deletePerson();
            else if (option == 5)
                setDepartmentToPerson();
            else if (option == 0)
                ProgramUI.startProgramUI();
        }
    }

    private void deletePerson() {
        viewAllPeople();
        System.out.print("Enter id to delete : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        PersonModel person = personDao.findPersonById(id);
        personDao.deletePerson(person);
    }

    private void updatePerson() {
        viewAllPeople();
        System.out.print("Enter id to update : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        PersonModel person = personDao.findPersonById(id);
        System.out.print("Enter new name : ");
        person.setName(scanner.next());
        System.out.print("Enter new age : ");
        person.setAge(scanner.nextInt());
        scanner.nextLine();
        personDao.updatePerson(person);
    }

    private void viewAllPeople() {
        List<PersonModel> people = personDao.getAllPeople();
        people.forEach(person -> {
            System.out.println(person.getId()
                    + "." + person.getName()
                    + " - " + person.getAge() + " ");
            DepartmentModel departmentModel = person.getDepartmentModel();
            if (departmentModel != null) {
                System.out.print("Department: " + departmentModel.getName() + " \n");
            } else {
                System.out.println("Department: -");
            }
            List<SkillModel> skills = person.getSkills();
            if (!skills.isEmpty()) {
                skills.forEach(skill -> System.out.println("Skill: " + skill.getName() + " "));
            } else {
                System.out.println("");
            }
            System.out.println("\n");
        });
    }

    private void addPerson() {
        PersonModel personModel = new PersonModel();
        System.out.print("Enter name : ");
        personModel.setName(scanner.next());
        System.out.print("Enter age : ");
        personModel.setAge(scanner.nextInt());
        scanner.nextLine();
        personDao.addPerson(personModel);
    }

    public void setDepartmentToPerson() {
        viewAllPeople();
        System.out.print("Enter id of person : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        PersonModel personModel = personDao.findPersonById(id);

        List<DepartmentModel> departments = departmentDao.getAllDepartments();
        departments.forEach(department -> System.out.println(department.getId() + "." + department.getName()));

        System.out.println("Enter id of department : ");
        int pick = scanner.nextInt();
        scanner.nextLine();
        DepartmentModel department = departmentDao.findDepartmentById(pick);
        personModel.setDepartmentModel(department);
        personDao.updatePerson(personModel);
    }


    private void menu() {
        System.out.println("PERSON MANAGEMENT \n0.Back \n1.Add Person \n2.View People " +
                "\n3.Update Person \n4.Delete Person \n5.Assign Department \n");
    }


}
