package com.marinshalamanov.codeforces.codeforces289;

import java.util.Scanner;

public class CF289A {
    
    static int[] result = {
        1,
        2,
        6,
        20,
        70,
        252,
        924,
        3432,
        12870,
        48620
    };
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(result[in.nextInt() - 1]);
    }
}