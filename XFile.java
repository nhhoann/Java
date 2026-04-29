package Assignmentjava2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XFile {
    // 1. Ghi file văn bản
    public static void ghiVanBan(String path, List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2. Đọc file văn bản
    public static List<String> docVanBan(String path) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println("File chưa tồn tại hoặc lỗi đọc file.");
        }
        return data;
    }
}