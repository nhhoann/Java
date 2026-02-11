package com.fpoly.lab4;

import java.util.Scanner;

/**
 *
 * @author tranthinhuhoan
 * Nhập 1 mảng gồm 5 số nguyên
 * Tính tổng các phân tử trong mảng
 * Đếm xem trong mảng đó có bao nhiêu phân tử chia hết cho 3
 * In các phần tử chia hết cho 3 đó ra màn mình
 */
public class ForDemo01 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int [] a = new int [5];
        
        System.out.println("Nhap cac phan tu trong mang: "); 
        for (int i = 0; i < 5; i++) {
            System.out.print("a[" + i + "] = ");
            a[i] = s.nextInt();
        }
        System.out.print("Cac phan tu cua mang la: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%-5d", a[i]);
        }
        System.out.println();
        int tong = 0;
        for (int i = 0; i < 5; i++) {
            if (a[i] >= 0) {
                tong += a[i];
            }
        }
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (a[i] % 3 == 0) {
                System.out.println(a[i]);
                count++;
            }
        }
        System.out.println("Cac phan tu chia het cho 3 la: " + count);
    }
    
}
