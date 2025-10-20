/**
 * Базовий клас для користувачів системи
 */
public class User {
    protected String id;
    protected String name;

    /**
     * Конструктор класу User
     * @param id Ідентифікатор користувача
     * @param name Ім'я користувача
     */
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Метод для входу в систему
     */
    public void login() {
        System.out.println("Користувач " + name + " увійшов в систему");
    }
}