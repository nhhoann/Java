package Assignmentjava2;

import java.io.Serializable;

/**
 * Thuc the Ban Sao - Dai dien cho tung quyen sach cu the trong kho
 * Ho tro ghi file (Serializable)
 */
public class BanSao implements Serializable {
    // Thuoc tinh
    private String maBanSao;      // Khoa chinh (id) cua ban sao
    private TrangThai tinhTrang;  // Trang thai dung Enum (CON_TOT, HU_HONG...)

    // Khoi tao mac dinh
    public BanSao() {}

    // Khoi tao nhanh voi day du thong tin
    public BanSao(String maBanSao, TrangThai tinhTrang) {
        this.maBanSao = maBanSao;
        this.tinhTrang = tinhTrang;
    }

    // --- Getter & Setter: Truyet xuat va cap nhat du lieu an toan ---
    
    public String getMaBanSao() { return maBanSao; }
    public void setMaBanSao(String maBanSao) { this.maBanSao = maBanSao; }

    public TrangThai getTinhTrang() { return tinhTrang; }
    public void setTinhTrang(TrangThai tinhTrang) { this.tinhTrang = tinhTrang; }

    /**
     * Ghi de phuong thuc toString de in thong tin ban sao nhanh khi debug
     */
    @Override
    public String toString() {
        return "BanSao{" + "ma='" + maBanSao + '\'' + ", tinhTrang=" + tinhTrang + '}';
    }
}