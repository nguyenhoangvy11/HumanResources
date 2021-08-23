package model;

import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        int str = console.nextInt();
        System.out.println("name: " + str);
        String str1 = console.nextLine() + console.nextLine();
        System.out.println("name 1: " + str1);
    }
}
