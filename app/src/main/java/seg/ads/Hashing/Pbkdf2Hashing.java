package seg.ads.Hashing;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Classe para realizar o hash de senhas com algoritmos de hash com PBKDF2.
 * 
 */
public class Pbkdf2Hashing implements PasswordHashing {
    
    private static final SecureRandom S_RANDOM = new SecureRandom();
    private final int ITERATIONS = 210000;
    private final int KEYLENGTH = 128;

    private final byte[] salt;
    private final String algorithm; 
 
    
    /**
     * Construtor Padrão
     * Configura os parâmetros necessários para o funcionamento de um algoritmo com PBKDF2.
     * Opções possíveis: "PBKDF2WithHmacSHA1", "PBKDF2WithHmacSHA256" e "PBKDF2WithHmacSHA512".
     * 
     * @param algorithm O algoritmo de hash com PBKDF2 que será utilizado
     */
    public Pbkdf2Hashing(String algorithm) {
        this.salt = new byte[16];
        S_RANDOM.nextBytes(this.salt); // Salt aleatório

        this.algorithm = algorithm;
    }
 
    public byte[] hash(String password) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEYLENGTH);
        try {
            return SecretKeyFactory.getInstance(algorithm).generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new IllegalStateException("❌ Erro: Chave inválida.", e);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("❌ Erro: Algoritmo " + algorithm + " não é suportado no ambiente atual.", e);
        }
    }
 
    public boolean verify(String password, byte[] hashedPassword) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(this.hash(password)).equals(encoder.encodeToString(hashedPassword));
    }
}
