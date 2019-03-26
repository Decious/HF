package by.voinilo.touchsoft;

import java.util.ArrayList;

public class Tester {
    void go() {
        for (int i = 0; i < 100; i++) {
            System.out.println(canBeEqualTo24(createArray()));

        }

    }


    int[] createArray() {
        int[] mas = new int[4];
        for (int i = 0; i < 4; i++) {
            mas[i] = (int) (Math.random() * 8) + 1;
            System.out.print(" " + mas[i] + " ");

        }
        System.out.println(" ");
        return mas;
    }


    public static void main(String[] args) {
        new Tester().go();
    }

    public boolean canBeEqualTo24(int[] nums) {
        int a = nums[0];
        int b = nums[1];
        int c = nums[2];
        int d = nums[3];
        //simple sum
        if ((a + b + c + d) == 24) {
            return true;
        } else {
            return false;
        }
    }
}
