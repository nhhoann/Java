package Assignmentjava2;

import java.io.*;
import java.util.*;

public class QuanLyService {
    private List<TaiLieu> listTaiLieu = new ArrayList<>();

    // --- THÊM TÀI LIỆU ---
    public void themTaiLieu(TaiLieu tl) throws TrungMaException {
        for (TaiLieu item : listTaiLieu) {
            if (item.getMaTL().equalsIgnoreCase(tl.getMaTL())) {
                throw new TrungMaException("Loi: Ma tai lieu da ton tai!");
            }
        }
        listTaiLieu.add(tl);
    }

    // --- SỬA TÀI LIỆU ---
    public void suaTaiLieu(String maTL, String tenMoi) {
        for (TaiLieu tl : listTaiLieu) {
            if (tl.getMaTL().equalsIgnoreCase(maTL)) {
                tl.setTenTL(tenMoi);
                System.out.println("Cap nhat tai lieu " + maTL + " thanh cong.");
                return;
            }
        }
    }

    // --- XÓA TÀI LIỆU ---
    public void xoaTaiLieu(String maTL) {
        listTaiLieu.removeIf(tl -> tl.getMaTL().equalsIgnoreCase(maTL));
        System.out.println("Da xoa tai lieu: " + maTL);
    }

    // --- QUẢN LÝ BẢN SAO (Thêm, Sửa, Xóa) ---
    public void themBanSao(String maTL, BanSao bs) throws TrungMaException {
        for (TaiLieu tl : listTaiLieu) {
            if (tl.getMaTL().equalsIgnoreCase(maTL)) {
                for (BanSao item : tl.getListBanSao()) {
                    if (item.getMaBanSao().equalsIgnoreCase(bs.getMaBanSao())) {
                        throw new TrungMaException("Loi: Ma ban sao da ton tai!");
                    }
                }
                tl.getListBanSao().add(bs);
                return;
            }
        }
    }

    public void suaTrangThaiBanSao(String maTL, String maBS, TrangThai ttMoi) {
        for (TaiLieu tl : listTaiLieu) {
            if (tl.getMaTL().equalsIgnoreCase(maTL)) {
                for (BanSao bs : tl.getListBanSao()) {
                    if (bs.getMaBanSao().equalsIgnoreCase(maBS)) {
                        bs.setTinhTrang(ttMoi);
                        System.out.println("Sua trang thai ban sao " + maBS + " thanh cong.");
                        return;
                    }
                }
            }
        }
    }

    public void xoaBanSao(String maTL, String maBS) {
        for (TaiLieu tl : listTaiLieu) {
            if (tl.getMaTL().equalsIgnoreCase(maTL)) {
                tl.getListBanSao().removeIf(bs -> bs.getMaBanSao().equalsIgnoreCase(maBS));
                System.out.println("Da xoa ban sao " + maBS + " cua " + maTL);
                return;
            }
        }
    }

    // --- LƯU & ĐỌC FILE (Sử dụng I/O Streams) ---
    public void ghiFile(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (TaiLieu tl : listTaiLieu) {
                StringBuilder sbBS = new StringBuilder();
                for (BanSao bs : tl.getListBanSao()) {
                    sbBS.append(bs.getMaBanSao()).append(":").append(bs.getTinhTrang()).append(";");
                }
                String chuoiBS = sbBS.length() > 0 ? sbBS.toString() : "NONE";
                bw.write(tl.getMaTL() + "," + tl.getTenTL() + "," + chuoiBS);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void docFile(String path) {
        listTaiLieu.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    TaiLieu tl = new TaiLieu(parts[0].trim(), parts[1].trim());
                    String chuoiBS = parts[2].trim();
                    if (!chuoiBS.equals("NONE")) {
                        String[] mangBS = chuoiBS.split(";");
                        for (String s : mangBS) {
                            String[] detail = s.split(":");
                            if (detail.length == 2) {
                                tl.getListBanSao().add(new BanSao(detail[0], TrangThai.valueOf(detail[1])));
                            }
                        }
                    }
                    listTaiLieu.add(tl);
                }
            }
        } catch (Exception e) {
            System.out.println("Chua co du lieu file.");
        }
    }

    public List<TaiLieu> getListTaiLieu() {
        return listTaiLieu;
    }
}