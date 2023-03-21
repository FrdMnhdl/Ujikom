package koneksi;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.Connection;
 
public class conek {    
    public Connection con;
    public  Statement stm;
    public  ResultSet rs;
    
    
    public void config(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/pengaduan_masyarakat", "root", "");
            stm = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
        }
    }
    
    
     public String hashPassword(String password) {
    String hashedPassword = null;

    try {
        // Gunakan fungsi PASSWORD untuk mengenkripsi password
        String query = "SELECT PASSWORD('" + password + "')";

        // Ambil koneksi ke database dan eksekusi query
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pengaduan_masyarakat", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        // Ambil hasil hash dari query
        if (rs.next()) {
            hashedPassword = rs.getString(1);
        }

        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return hashedPassword;
}

}