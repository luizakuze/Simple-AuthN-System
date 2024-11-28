package seg.ads.Hashing;

/**
 * Interface para realizar o hash de senhas com diferentes algoritmos.
 */
public interface PasswordHashing {

    /**
     * Método que recebe uma senha e retorna o hash da senha
     * @param password Senha do usuário 
     * @return Hash da senha
     */
    byte[] hash(String password);


    /**
     * Método que verifica se a senha fornecida é igual ao hash da senha armazenado
     * @param password Senha fornecida
     * @param hashedPassword Senha do usuário
     * @return true se as senhas forem correspondentes, false caso contrário
     */
    boolean verify(String password, byte[] hashedPassword);

    /**
     * Define o tipo de algoritmo de hashing com base no nome fornecido.
     *
     * @param algorithm Nome do algoritmo (ex.: PBKDF2, MD5, SHA-256, etc.).
     * @return Uma instância de PasswordHashing correspondente ao algoritmo.
     */
    public static PasswordHashing defineAlgorithmType(String algorithm) {
        return switch (algorithm) {
            case "PBKDF2WithHmacSHA1", "PBKDF2WithHmacSHA256", "PBKDF2WithHmacSHA512"  ->
                new Pbkdf2Hashing(algorithm); // PBKDF2
            case "MD5", "SHA-1", "SHA-256", "SHA-512" ->
                new MessageDigestHashing(algorithm); // MessageDigest
            case "BCrypt" ->
                new BcryptHashing(); // BCrypt
            default -> 
                throw new IllegalStateException("❌ Erro: Algoritmo " + algorithm + " não é suportado no ambiente atual.");
        };
    }
}