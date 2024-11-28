package seg.ads;

/*
 * Classe que representa um usuário de um sistema.
 */
public class User {
    private String login;
    private byte[] hashedPassword;

    public User(String login, byte[] hashedPassword) {
        this.login = login;
        this.hashedPassword = hashedPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
