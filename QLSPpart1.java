package com.fpoly.assignment;

import java.util.Scanner;

/**
 *
 * @author tranthinhuhoan
 */
public class SanPham {
    String maSP;
    String tenSP;
    double donGia;
    int soLuong;

    public SanPham() {}
// Khai báo
    public SanPham(String maSP, String tenSP, double donGia, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }
// Enter product's information
    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Nhap thong tin ---");
        
        do {
            System.out.println("Ma SP (SPXXX): ");
            this.maSP = sc.nextLine();
        } while (!maSP.matches("SP\\d{3}")); //tức là sau SP có 3 số
        
        System.out.print("Ma SP: "); 
        this.maSP = sc.nextLine();
        
        System.out.print("Ten SP: "); 
        this.tenSP = sc.nextLine();
        
        System.out.print("Gia: "); 
        this.donGia = sc.nextDouble();
        
        System.out.print("SL: "); 
        this.soLuong = sc.nextInt();
    }
// Write phương thức tính tiền
    public void Xuat() {
        double tong = soLuong * donGia;
        System.out.println("Ma: " + maSP + " | Ten: " + tenSP + " | Tong: " + tong);
    }
    
    public double thanhTien() {
        return soLuong * donGia;
    }
    
// Update product's information
    public void capNhat() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Cap nhat thong tin moi ---");
        System.out.print("Ten moi: ");
        this.tenSP = sc.nextLine();
        System.out.print("Gia moi: "); 
        this.donGia = sc.nextDouble();
        System.out.print("SL moi: "); 
        this.soLuong = sc.nextInt();
    }

    
}
