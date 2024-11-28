package seg.ads;

import java.util.Scanner;

/**
* Classe principal
*/
public class App {
    static Scanner scanner = new Scanner(System.in);
     
        private UserService service;
        
        /**
         * Seleciona o algoritmo de hash que será utilizado. 
         * Opções implementadas: 
         * 1) Algoritmos de hash com message digest: "MD5", "SHA-1", "SHA-256" ou "SHA-512".
         * 2) Algoritmos de hash com PBKDF2: "PBKDF2WithHmacSHA1", "PBKDF2WithHmacSHA256" e "PBKDF2WithHmacSHA512".
         * 3) Algoritmo de hash "BCrypt".
         */
        public void initializeService() {
            // Por padrão utiliza BCrypt 
            this.service = new UserService("BCrypt");  
            menuService();
        }
     
        /**
         * Menu de opções para um Sistema de Autenticação
         */
        private void menuService() {
            while (true) {
                System.out.println(" ----- Sistema de Autenticação ----- ");
                System.out.println(" (1) Registrar um novo usuário ");
                System.out.println(" (2) Atualizar a senha de um usuário "); 
                System.out.println(" (3) Autenticar um usuário ");
                System.out.println(" (4) Sair ");
                System.out.print("Escolha uma opção (1..4) > ");

                int op = scanner.nextInt();
                scanner.nextLine();  
                String login, password;

                // 'Sair' é a única opção que não precisa de autenticação
                if (op == 4) {
                    System.out.println("Finalizando Sistema... 👋👋👋");
                    return;
                }
        
                // Solicitar login e senha para opções 1, 2 e 3
                System.out.println("----- Entre com o login e senha -----");
                System.out.print("Digite seu login > ");
                login = scanner.nextLine();
                System.out.print("Digite sua senha > ");
                password = scanner.nextLine();
 
                switch (op) {
                    case 1: 
                        // Registrar novo usuário
                        if (service.register(login, password)) 
                            System.out.println("✅ Usuário registrado com sucesso!");
                        else 
                            System.out.println("❌ Erro: Este login já existe.");
                        break;
                
                    case 2: 
                        // Atualizar a senha de um usuário
                        if (service.authenticate(login, password)) {
                            System.out.println("Autenticado! 🔓🔑");
                            System.out.print("Digite sua nova senha > ");
                            String newPassword = scanner.nextLine();
                            System.out.print("Confirme sua nova senha > ");
                            String confirmPassword = scanner.nextLine();
                
                            if (service.updatePassword(login, newPassword, confirmPassword)) {
                                System.out.println("✅ Senha atualizada com sucesso!");
                            } else {
                                System.out.println("❌ Erro: Senhas não correspondente.");
                            }
                        } else {
                            System.out.println("❌ Erro: Autenticação falhou! 🔒");
                        }
                        break;
                
                    case 3:
                        // Autenticar um usuário
                        if (service.authenticate(login, password)) {
                            System.out.println("Autenticado! 🔓🔑");
                        } else {
                            System.out.println("❌ Erro: Autenticação falhou! 🔒");
                        }
                        break;
                
                    default:  
                        System.out.println("❌ Erro: Opção inválida!");
                        break;
                }
            }
        }
    
    public static void main(String[] args) {
        App app = new App();
        app.initializeService();
    }
}