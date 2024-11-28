package seg.ads;

import seg.ads.Hashing.PasswordHashing;
import seg.ads.Repository.UserRepository;
import seg.ads.Repository.UserRepositoryMemory;

/*
 * Classe que implementa um serviço de autenticação.
 */
public class UserService {
    private UserRepository repository;
    private PasswordHashing passwordHashing; 
 
    public UserService(String algorithm) {
        this.repository = new UserRepositoryMemory(); // Essa linha define o modelo da camada de persistência de dados
        this.passwordHashing = PasswordHashing.defineAlgorithmType(algorithm); 
    }
 
    /**
     * Define o tipo de algoritmo de hashing com base no nome fornecido.
     *
     * @param algorithm Nome do algoritmo (ex.: PBKDF2, MD5, SHA-256, etc.).
     * @return Uma instância de PasswordHashing correspondente ao algoritmo.
     */
    public boolean register(String login, String password) {
        byte[] hashedPassword;
        hashedPassword = passwordHashing.hash(password);
        return repository.save(new User(login, hashedPassword));
    }
 
    /**
     * Altera a senha de um usuário cadastrado no sistema.
     *
     * @param newPassword Nova senha.
     * @param login Login atual.
     */
    public boolean updatePassword(String login, String newPassword, String confirmNewPassword) {
        //TODO: poderia calcular o hash somente se o usuário existir, se não já retorna false
        if (newPassword.equals(confirmNewPassword)) {
            byte[] hashedNewPassword;
            hashedNewPassword = passwordHashing.hash(newPassword);
            return (repository.replacePassword(login, hashedNewPassword));
        }
        return false;
    }

    /**
     * Autentica um usuário no sistema.
     *
     * @param newPassword Senha do usuário
     * @param login Login do usuário
     * @return true se a autenticação ocorrer sem problemas, false caso contrário
     */
    public boolean authenticate(String login, String password) {
        if (repository.containsRegistry(login)) {
            return (passwordHashing.verify(password, repository.findByLogin(login).getHashedPassword()));
        }
        return false;
    }

    // Classe para realizar alguns testes
    // public UserRepositoryMemory getRepository() {
    //     return repository;
    // }


    
  
}

 


 