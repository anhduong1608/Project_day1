package validate;

import java.security.MessageDigest;

public class PasswordUtil {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b)); // chuyển byte thành hex
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi mã hóa mật khẩu", e);
        }
    }
}
