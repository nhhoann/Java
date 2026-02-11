package com.fpoly.assignment;

/**
 *
 * @author tranthinhuhoan
 */
public class Main {
    public static void main(String[] args) {
        SanPham sp = new SanPham();

        sp.Nhap();
        System.out.println("\n Thong tin san pham vua nhap");
        sp.Xuat();
        System.out.println("\n Tien hanh cap nhat san pham");
        sp.capNhat();
        System.out.println("\n Ket qua sau khi cap nhat ");
        sp.Xuat();
    }  
} 
