package seg.ads.Hashing;

import java.nio.charset.StandardCharsets;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe para realizar o hash de senhas com BCrypt
 * 
 */
public class BcryptHashing implements PasswordHashing {
     
    public byte[] hash(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password).getBytes(StandardCharsets.UTF_8);
    }
 
    public boolean verify(String password, byte[] hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, new String(hashedPassword, StandardCharsets.UTF_8));
    }

   
}
