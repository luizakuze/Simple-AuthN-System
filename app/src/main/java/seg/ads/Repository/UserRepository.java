package seg.ads.Repository;

import seg.ads.User;

/**
 * Camada de persistência genérica. 
 */
public interface UserRepository {

    /**
     * Salva um usuário no repositório.
     * 
     * @param user Usuário a ser armazenado
     * @return true se o usuário for registrado sem erros, false caso contrário
     */
    boolean save(User user);

    /**
     * Obtém um usuário a partir de um login.
     * 
     * @param login Login de um usuário
     * @return O usuário com este login
     */
    User findByLogin(String login);  

    /**
     * Verifica se um usuário está registrado.
     * 
     * @param login Login de um usuário
     * @return true caso o usuário exista no repositório, false caso contrário
     */
    boolean containsRegistry(String login);

    /**
     * Substituir a senha de um usuário
     * 
     * @param login Login de um usuário
     * @
     * @return true caso o usuário exista no repositório, false caso contrário
     */
    boolean replacePassword(String login, byte[] newHashedPassword);

    /**
     * Deleta um usuário a partir de um login.
     * 
     * @param login Login de um usuário
     * @return true se o usuário for removido sem erros, false caso contrário
     */
    boolean deleteByLogin(String login);  
}