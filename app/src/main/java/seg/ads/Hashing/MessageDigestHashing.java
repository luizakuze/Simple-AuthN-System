package seg.ads.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Classe para realizar o hash de senhas com algoritmos de hash com message digest.
 * 
 */
public class MessageDigestHashing implements PasswordHashing {

    private static final SecureRandom S_RANDOM = new SecureRandom();
    private final byte[] salt;
    private final String algorithm;

    /**
     * Construtor Padrão
     * Configura os parâmetros necessários para o funcionamento de um algoritmo com message digest.
     * Opções possíveis: "MD5", "SHA-1", "SHA-256" e "SHA-512"
     * 
     * @param algorithm O algoritmo de hash que será utilizado
     */
    public MessageDigestHashing(String algorithm) {
        this.salt = new byte[16];
        S_RANDOM.nextBytes(this.salt);

        this.algorithm = algorithm;
    }
        
    public byte[] hash(String password)  {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(salt); // Usa o salt fornecido
            return md.digest(password.getBytes(StandardCharsets.UTF_8)); 
        } catch (NoSuchAlgorithmException e) { 
            throw new IllegalStateException("❌ Erro: Algoritmo " + algorithm + " não é suportado no ambiente atual.", e);
        }
    }
 
    public boolean verify(String password, byte[] hashedPassword) {
        return MessageDigest.isEqual(hash(password), hashedPassword);
    }

 
}
