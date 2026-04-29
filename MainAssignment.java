package Assignmentjava2;

public class MainAssignment {
    public static void main(String[] args) {
        QuanLyService service = new QuanLyService();
        String path = "data.txt";

        try {
            // --- 1. THEM MOI ---
            service.themTaiLieu(new TaiLieu("TL001", "Giao trinh Java 2"));
            service.themTaiLieu(new TaiLieu("TL002", "SQL Server Co ban"));
            service.themTaiLieu(new TaiLieu("TL003","SQL Server nang cao"));

            service.themBanSao("TL001", new BanSao("BS001", TrangThai.CON_TOT));
            service.themBanSao("TL001", new BanSao("BS002", TrangThai.HU_HONG));
            service.themBanSao("TL002", new BanSao("BS003", TrangThai.MAT));

            // --- 2. LƯU & ĐỌC FILE ---
            service.ghiFile(path);
            service.docFile(path);

            // --- 3. SUA & XOA (Dap ung muc 1.2) ---
            System.out.println("--- Thuc hien Sua & Xoa ---");
            service.suaTaiLieu("TL002", "SQL Server Nang Cao (Update)");
            service.suaTrangThaiBanSao("TL001", "BS001", TrangThai.HU_HONG);
            service.xoaBanSao("TL001", "BS002"); // Xóa bản sao BS002

            // --- 4. HIEN THI (Dap ung muc 1.3 - Quan he 1-N) ---
            System.out.println("\n--- KET QUA HIEN THI CHI TIET ---");
            for (TaiLieu tl : service.getListTaiLieu()) {
                System.out.println("Ma TL: " + tl.getMaTL() + " | Ten: " + tl.getTenTL());
                for (BanSao bs : tl.getListBanSao()) {
                    System.out.println("  -> Ban sao: " + bs.getMaBanSao() + " | Trang thai: " + bs.getTinhTrang());
                }
            }
            
            // Lưu lại lần cuối sau khi sửa xóa
            service.ghiFile(path);
            System.out.println("\nBUILD SUCCESSFUL!");

        } catch (TrungMaException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}