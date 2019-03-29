package by.voinilo.jd.jd01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class TaskA {

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {

        int[] mas = new int[10];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            mas[i] = sc.nextInt();

        }
        step1(mas);
        step2(mas);

    }

    static void step1(int[] mas) {
        for (int i : mas
        ) {
            if (mas[i] > max) {
                max = mas[i];
            }
            if (mas[i] < min) {
                min = mas[i];
            }
        }
        System.out.println(" ");
        System.out.println("MAX= " + max);
        System.out.println("MIN= " + min);
    }

    static void step2(int[] mas) {
        double sum = 0;
        double avg = 0;
        for (int elem : mas) {
            sum+=elem;


        }
        avg= sum/mas.length;


        System.out.println(avg);
    }

    static void step3(int[] mas) {
    }
}
