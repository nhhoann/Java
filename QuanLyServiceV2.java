package Assignmentjava2;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class QuanLyServiceV2 {

    // 1. Tìm kiếm, Sắp xếp và Phân trang
    public List<TaiLieu> findDocuments(String keyword, String sortBy, int page, int pageSize) {
        List<TaiLieu> list = new ArrayList<>();
        
        List<String> allowedSort = Arrays.asList("id", "title", "author", "category");
        if (!allowedSort.contains(sortBy.toLowerCase())) sortBy = "id";

        String sql = "SELECT * FROM DOCUMENT WHERE title LIKE ? OR author LIKE ? " +
                     "ORDER BY " + sortBy + " " +
                     "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = DBContext.getConnection(); // Thống nhất tên DBContext
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setInt(3, (page - 1) * pageSize);
            ps.setInt(4, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiLieu tl = new TaiLieu(rs.getString("id"), rs.getString("title"));
                loadCopiesForDocument(tl, conn); 
                list.add(tl);
            }
        } catch (Exception e) {
            System.err.println("Loi truy van: " + e.getMessage());
        }
        return list;
    }

    // 2. Load danh sách bản sao (Quan hệ 1-N)
    private void loadCopiesForDocument(TaiLieu tl, Connection conn) throws SQLException {
        String sql = "SELECT * FROM DOCUMENT_COPY WHERE document_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tl.getMaTL());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TrangThai tt = TrangThai.valueOf(rs.getString("status"));
                BanSao bs = new BanSao(rs.getString("id"), tt);
                tl.getListBanSao().add(bs);
            }
        }
    }

    // 3. Thêm tài liệu mới (INSERT)
    public void addTaiLieu(TaiLieu tl) {
        String sql = "INSERT INTO DOCUMENT (id, title) VALUES (?, ?)";
        try (Connection conn = DBContext.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, tl.getMaTL());
            ps.setString(2, tl.getTenTL());
            
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("==> Them thanh cong tai lieu vao DB!");
            }
        } catch (Exception e) {
            System.out.println("Loi khi them: " + e.getMessage());
        }
    }

    // 4. Cập nhật trạng thái hàng loạt (Sử dụng Transaction)
    public void updateMultipleCopiesStatus(String docId, TrangThai newStatus) {
        String sql = "UPDATE DOCUMENT_COPY SET status = ? WHERE document_id = ?";
        try (Connection conn = DBContext.getConnection()) {
            conn.setAutoCommit(false); 

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, newStatus.name()); 
                ps.setString(2, docId);
                int count = ps.executeUpdate();
                
                conn.commit(); 
                System.out.println("Da cap nhat " + count + " ban sao cho " + docId);
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5. Xóa Tài liệu
    public void deleteDocument(String id) {
        String sql = "DELETE FROM DOCUMENT WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Da xoa tai lieu: " + id);
        } catch (Exception e) {
            System.out.println("Loi xoa: " + e.getMessage());
        }
    }

    // 6. Stream API: Loc tai lieu trong
    public List<TaiLieu> findEmptyDocuments(List<TaiLieu> allDocs) {
        return allDocs.stream()
                      .filter(tl -> tl.getListBanSao().isEmpty())
                      .collect(Collectors.toList());
    }
}