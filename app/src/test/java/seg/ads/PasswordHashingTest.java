package seg.ads;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import org.junit.jupiter.api.Test;

import seg.ads.Hashing.BcryptHashing;
import seg.ads.Hashing.PasswordHashing;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordHashingTest {

    private static final SecureRandom S_RANDOM = new SecureRandom();
    private PasswordHashing passwordHashing; 

        @Test
        public void testHashPasswordWithMessageDigest() throws NoSuchAlgorithmException {
    
            byte[] salt = new byte[16];
            S_RANDOM.nextBytes(salt);
            String[] algorithms = { "MD5", "SHA-1", "SHA-256", "SHA-512" };
            byte[] hashedPassword;
            String password = "123456";
    
            for (String algorithm : algorithms) {
                passwordHashing = PasswordHashing.defineAlgorithmType(algorithm); 
                hashedPassword = passwordHashing.hash(password);
            assertTrue(passwordHashing.verify(password, hashedPassword));
        }
    }

    @Test
    public void testHashPasswordWithPBKDF2() { 
        String[] algorithms = { "PBKDF2WithHmacSHA1", "PBKDF2WithHmacSHA256", "PBKDF2WithHmacSHA512" };
        byte[] hashedPassword;
        String password = "123456";

        for (String algorithm : algorithms) {
            passwordHashing = PasswordHashing.defineAlgorithmType(algorithm); 
            hashedPassword = passwordHashing.hash(password);
        assertTrue(passwordHashing.verify(password, hashedPassword));
        }
    }

    @Test
    public void testHashPasswordWithBCrypt() {
        String password = "123456";
        passwordHashing = PasswordHashing.defineAlgorithmType("BCrypt"); 
        byte[] hashedPassword = passwordHashing.hash(password);
        assertTrue(passwordHashing.verify(password, hashedPassword));
    }
}
