package seg.ads.Repository;

import java.util.HashMap;
import seg.ads.User;
 
/*
 * Camada de persistência em memória utilizando uma tabela hash.
 */
public class UserRepositoryMemory implements UserRepository {
    private HashMap<String, byte[]> registries; // guarda login e senha

    public UserRepositoryMemory() {
        this.registries = new HashMap<>();
    }

    @Override
    public boolean save(User user) {
        // se não houver um usuário com esse login...
        if (!registries.containsKey(user.getLogin())) {
            // ...faz o registro
            registries.put(user.getLogin(), user.getHashedPassword());
            return true;
        }
        return false;
    }
    
    @Override
    public boolean deleteByLogin(String login) {
        return registries.remove(login) != null;
    }

    @Override
    public boolean replacePassword(String login, byte[] newHashedPassword) {
        if (registries.containsKey(login)) {
            registries.put(login, newHashedPassword); 
            return true;
        }
        return false;
    }

    @Override
    public User findByLogin(String login) {
        if (registries.containsKey(login)) {
            return new User(login, registries.get(login));
        }
        return null;
    }
 
    @Override
    public boolean containsRegistry(String login) {
        if (registries.containsKey(login)) {
            return true;
        }
        return false;
    }

    // Método a fins de teste: Imprime todos os usuários registrados
    // public void printAll() { 
    //     if (registros.isEmpty()) {
    //         System.out.println("Nenhum registro");
    //         return;
    //     }
    
    //     registros.forEach((login, hashedPassword) -> {
    //         System.out.println("Login: " + login);
    //         System.out.println("Senha Hash: " + hashedPassword); 
    //     });
    // }

    // Método a fins de teste: obtém o registro
    // public HashMap<String, byte[]> getRegistros() {
    //     return registros;
    // }


    
    
}