package ui;

import java.util.Scanner;

public class ProgramUI {

    private static Scanner scannner = new Scanner(System.in);

    private static PersonManagementUI personManagementUI = new PersonManagementUI();
    private static DepartmentManagementUI departmentManagementUI = new DepartmentManagementUI();
    private static SkillManagementUI skillManagementUI = new SkillManagementUI();

    public static void startProgramUI(){
        while(true){
            System.out.println("0.Exit \n1.Person Management \n2.Department Management \n3.Skill Management");
            int option = scannner.nextInt();
            scannner.nextLine();
            if(option == 0){
                System.exit(0);
            }else if(option == 1){
                personManagementUI.startPersonManagementUI();
            }else if(option == 2) {
                departmentManagementUI.startDepartmentManagementUI();
            }else if(option == 3){
                skillManagementUI.startSkillManagementUI();
            }

        }
    }
}
