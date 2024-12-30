# Sistema de autenticação de usuários 🔓🔑
> Data: 27/11/2024 <br>
> Autora: Luiza Kuze

## 💻 Tecnologias 
- Gradle
- Java 21
- Spring Security

## ✨ Funcionalidades
- **Armazenamento seguro de senhas**: as senhas são armazenadas como hashes para maior segurança.  
- **Persistência genérica**: utiliza uma tabela hash, mas pode ser adaptada para banco de dados relacional.  
- **Algoritmo de hash configurável**: o padrão é `BCrypt`, mas pode ser ajustado durante a inicialização do serviço. Os algoritmos implementados incluem:
    - `BCrypt`: Forte, com salting automático.  
    - `PBKDF2`: Algoritmo derivado de chave com suporte a múltiplas iterações, incluindo:  
    - `HmacSHA1`  
    - `HmacSHA256`  
    - `HmacSHA512`  
    - **MessageDigest**: Algoritmos de digest, incluindo:  
        - `MD5` (não recomendado para segurança moderna)  
        - `SHA-1` (mais seguro que MD5, mas desatualizado)  
        - `SHA-256` (recomendado)  
        - `SHA-512` (fortemente recomendado)  
- **Sistema de autenticação**: oferece as seguintes ações:  
  - Cadastro de usuários;  
  - Atualização de senhas;  
  - Autenticação de usuários.  

- **Interface via linha de comando**.
## 📚 Diagrama UML
```mermaid
classDiagram
    direction LR

    class PasswordHashing {
        <<interface>>
        +hash(password: String) byte[]
        +verify(password: String, hashedPassword: byte[]) boolean
        +defineAlgorithmType(algorithm: String) PasswordHashing
    }

    class BcryptHashing {
        +hash(password: String)  byte[]
        +verify(password: String, hashedPassword: byte[]) boolean
    }
    
    class MessageDigestHashing {
        -S_RANDOM: SecureRandom
        -salt: byte[]
        -algorithm: String
        +MessageDigestHashing(algorithm: String)
        +hash(password: String): byte[]
        +verify(password: String, hashedPassword: byte[]) boolean
    }

    class Pbkdf2Hashing {
        -S_RANDOM: SecureRandom
        -ITERATIONS: int
        -KEYLENGTH: int
        -salt: byte[]
        -algorithm: String
        +Pbkdf2Hashing(algorithm: String)
        +hash(password: String) byte[]
        +verify(password: String, hashedPassword: byte[]) boolean
    }

    class User {
        -login: String
        -hashedPassword: byte[]
        +User(login: String, hashedPassword: byte[])
        +getLogin() String
        +getHashedPassword() byte[]
        +setLogin(login: String) void
        +setHashedPassword(hashedPassword: byte[]) void
    }

    class UserRepository {
        <<interface>>
        +save(user: User) boolean
        +findByLogin(login: String) User
        +containsRegistry(login: String) boolean
        +replacePassword(login: String, newHashedPassword: byte[]) boolean
        +deleteByLogin(login: String) boolean
    }

    class UserRepositoryMemory {
        -registries: HashMap ~< String, byte[] >~
        +UserRepositoryMemory()
        +save(user: User): boolean
        +findByLogin(login: String)  User
        +containsRegistry(login: String)  boolean
        +replacePassword(login: String, newHashedPassword: byte[])  boolean
        +deleteByLogin(login: String)  boolean
    }

    class UserService {
        -repository: UserRepository
        -passwordHashing: PasswordHashing
        +UserService(algorithm: String)
        +register(login: String, password: String) boolean
        +updatePassword(login: String, newPassword String, confirmNewPassword: String)  boolean
        +authenticate(login: String, password: String)  boolean
    }

    class App {
        -service: UserService
        +initializeService()  void
        +menuService()  void
    }

    PasswordHashing <|.. BcryptHashing
    PasswordHashing <|.. MessageDigestHashing
    PasswordHashing <|.. Pbkdf2Hashing

    UserRepository <|.. UserRepositoryMemory

    UserRepository o-- User
    UserService *-- UserRepository
    UserService *-- PasswordHashing
    App *-- UserService
    
```

## 📜 Licença

Este projeto é licenciado sob a MIT License.
