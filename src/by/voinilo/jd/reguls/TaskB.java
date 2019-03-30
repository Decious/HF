package by.voinilo.jd.reguls;

import java.sql.SQLOutput;
import java.util.Scanner;

public class TaskB {
    public static void main(String[] args) {
        new TaskB().go();
    }

    int num;

    public void go() {

        System.out.println("Enter number of people");
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();
        int d = num + 1;
        int[][] salaries = new int[4][num];

        String[] people = new String[d];

        System.out.println("Enter names");
        for (int i = 0; i < d; i++) {
            people[i] = scanner.nextLine();
        }

        for (int i = 0; i < 4; i++) {
            System.out.println("enter salary for " + people[i]);
            for (int j = 0; j < d; j++) {
                salaries[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(salaries[i][j]);
            }
        }
    }
}

