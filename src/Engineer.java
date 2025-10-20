/**
 * Клас для представлення інженера в системі
 */
public class Engineer extends User implements IReviewer {
    private Model model;

    /**
     * Конструктор класу Engineer
     * @param id Ідентифікатор інженера
     * @param name Ім'я інженера
     */
    public Engineer(String id, String name) {
        super(id, name);
    }

    /**
     * Метод для перевірки конфліктів
     */
    public void checkConflicts() {
        System.out.println("Інженер " + name + " перевіряє конфлікти в моделі");
    }

    /**
     * Метод для надсилання запиту на уточнення
     */
    public void sendRequest() {
        System.out.println("Інженер " + name + " надсилає запит на уточнення");
    }

    @Override
    public void reviewModel() {
        System.out.println("Інженер " + name + " переглядає модель");
    }

    /**
     * Метод для встановлення моделі для перевірки
     * @param model Модель для перевірки
     */
    public void setModel(Model model) {
        this.model = model;
    }
}