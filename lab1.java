package com.fpoly.lab1;

import java.util.Scanner;

/**
 *
 * @author tranthinhuhoan
 * Nhập một mảng gồm 5 số nguyên
 * Tính tổng các số chẵn trong mảng và in ra màn hình
 */
public class KiemTra {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] a = new int [5];
        
        System.out.println("Nhap cac so cua mang: ");
        for (int i = 0; i < 5; i++) {
            System.out.print("a[" + i + "] = ");
            a[i] = s.nextInt();
        }
        System.out.println("Cac phan tu cua mang la: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%-5d", a[i]);
        }
        int tong = 0;
        for (int i = 0; i < 5; i++) {
            if (a[i] % 2 == 0) {
                tong += a[i];
            }
        }
        System.out.println("Tong cua cac so chan trong mang la: " + tong);  
    }
}
