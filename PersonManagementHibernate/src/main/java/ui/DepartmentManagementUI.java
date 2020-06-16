package ui;

import dao.DepartmentDao;
import model.DepartmentModel;

import java.util.List;
import java.util.Scanner;

public class DepartmentManagementUI {
    Scanner scanner = new Scanner(System.in);
    private DepartmentDao departmentDao = new DepartmentDao();

    public void startDepartmentManagementUI() {
        while (true) {
            menu();
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1)
                addDepartment();
            else if (option == 2)
                viewAllDepartments();
            else if (option == 3)
                updateDepartment();
            else if (option == 4)
                deleteDepartment();
            else if (option == 0)
                ProgramUI.startProgramUI();
        }
    }

    private void deleteDepartment() {
        viewAllDepartments();
        System.out.print("Enter id to delete : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        DepartmentModel department = departmentDao.findDepartmentById(id);
        departmentDao.deleteDepartment(department);
    }

    private void updateDepartment() {
        viewAllDepartments();
        System.out.print("Enter id to update : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        DepartmentModel department = departmentDao.findDepartmentById(id);
        System.out.print("Enter new name : ");
        department.setName(scanner.next());
        departmentDao.updateDepartment(department);
    }

    private void viewAllDepartments() {
        List<DepartmentModel> departments = departmentDao.getAllDepartments();
        departments.forEach(department -> System.out.println(department.getId() + "." + department.getName()));
    }

    private void addDepartment() {
        DepartmentModel department = new DepartmentModel();
        System.out.print("Enter name : ");
        department.setName(scanner.next());
        departmentDao.addDepartment(department);
    }

    private void menu() {
        System.out.println("DEPARTMENT MANAGEMENT \n0.Back \n1.Add Department \n2.View Departments " +
                "\n3.Update Department \n4.Delete Department \n");
    }
}
