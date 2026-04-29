package Assignmentjava2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Thuc the Tai Lieu - Cho phep ghi file (Serializable)
 */
public class TaiLieu implements Serializable {
    // Thuoc tinh co ban
    private String maTL;     // Khoa chinh
    private String tenTL;
    
    // Quan he 1-N: Mot Tai Lieu chua nhieu Ban Sao
    private List<BanSao> listBanSao;

    // Khoi tao mac dinh
    public TaiLieu() {
        this.listBanSao = new ArrayList<>(); // Tranh loi Null khi dung list
    }

    // Khoi tao nhanh voi Ma va Ten
    public TaiLieu(String maTL, String tenTL) {
        this.maTL = maTL;
        this.tenTL = tenTL;
        this.listBanSao = new ArrayList<>();
    }

    // --- Getter & Setter: Truyet xuat va cap nhat du lieu an toan ---
    
    public String getMaTL() { return maTL; }
    public void setMaTL(String maTL) { this.maTL = maTL; }

    public String getTenTL() { return tenTL; }
    public void setTenTL(String tenTL) { this.tenTL = tenTL; }

    public List<BanSao> getListBanSao() { return listBanSao; }
    public void setListBanSao(List<BanSao> listBanSao) { this.listBanSao = listBanSao; }
}