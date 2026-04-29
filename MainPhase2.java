package Assignmentjava2;

import java.util.List;
import java.util.Scanner;

public class MainPhase2 {
    private static final QuanLyServiceV2 service = new QuanLyServiceV2();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n========= MENU QUAN LY THU VIEN =========");
            System.out.println("1. Xem danh sach (Phan trang)");
            System.out.println("2. Tim kiem tai lieu");
            System.out.println("3. Sua trang thai hang loat (Transaction)");
            System.out.println("4. Loc tai lieu chua co ban sao (Stream)");
            System.out.println("5. Them tai lieu moi"); // Chức năng mới
            System.out.println("0. Thoat");
            System.out.print("Moi ban chon (0-5): ");
            
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1: menuPhanTrang(); break;
                case 2: menuTimKiem(); break;
                case 3: menuTransaction(); break;
                case 4: menuStreamLoc(); break;
                case 5: menuThem(); break; // Gọi hàm thêm
                case 0: System.out.println("Tam biet!"); break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    // --- HAM THEM TAI LIEU ---
    private static void menuThem() {
        System.out.println("\n--- NHAP THONG TIN TAI LIEU MOI ---");
        System.out.print("Nhap Ma (e.g TL100): ");
        String ma = sc.nextLine();
        System.out.print("Nhap Ten Tai Lieu: ");
        String ten = sc.nextLine(); 

        TaiLieu moi = new TaiLieu(ma, ten);
        service.addTaiLieu(moi);
    }

    // --- CAC HAM KHAC (Giu nguyen nhu cu) ---
    private static void menuPhanTrang() {
        System.out.print("Nhap so trang muon xem: ");
        int p = Integer.parseInt(sc.nextLine());
        List<TaiLieu> ds = service.findDocuments("", "id", p, 2);
        inDS(ds);
    }

    private static void menuTimKiem() {
        System.out.print("Nhap tu khoa: ");
        String k = sc.nextLine();
        inDS(service.findDocuments(k, "id", 1, 10));
    }

    private static void menuTransaction() {
        System.out.print("Nhap Ma TL can sua ban sao: ");
        String id = sc.nextLine();
        service.updateMultipleCopiesStatus(id, TrangThai.CON_TOT);
        System.out.println("Da thuc hien cap nhat!");
    }

    private static void menuStreamLoc() {
        List<TaiLieu> tatCa = service.findDocuments("", "id", 1, 100);
        service.findEmptyDocuments(tatCa).forEach(t -> System.out.println(" -> " + t.getTenTL()));
    }

    private static void inDS(List<TaiLieu> ds) {
        if (ds.isEmpty()) System.out.println("Khong co du lieu.");
        for (TaiLieu t : ds) {
            System.out.println("[" + t.getMaTL() + "] " + t.getTenTL());
            t.getListBanSao().forEach(b -> System.out.println("   + " + b.getMaBanSao() + ": " + b.getTinhTrang()));
        }
    }
}