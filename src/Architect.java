/**
 * Клас для представлення архітектора в системі
 */
public class Architect extends User implements IReviewer {
    private Model currentModel;

    /**
     * Конструктор класу Architect
     * @param id Ідентифікатор архітектора
     * @param name Ім'я архітектора
     */
    public Architect(String id, String name) {
        super(id, name);
    }

    /**
     * Метод для публікації моделі
     */
    public void publishModel() {
        System.out.println("Архітектор " + name + " публікує модель");
        if (currentModel != null) {
            currentModel.view();
        }
    }

    /**
     * Метод для випуску оновлення моделі
     */
    public final void releaseUpdate() {
        System.out.println("Архітектор " + name + " випускає оновлену версію моделі");
    }

    @Override
    public void reviewModel() {
        System.out.println("Архітектор " + name + " переглядає модель");
    }

    /**
     * Метод для встановлення поточної моделі
     * @param model Модель для роботи
     */
    public void setCurrentModel(Model model) {
        this.currentModel = model;
    }

    /**
     * Повертає ім'я архітектора
     * @return Ім'я архітектора
     */
    public String getName() {
        return name;
    }

    /**
     * Повертає ідентифікатор архітектора
     * @return Ідентифікатор архітектора
     */
    public String getId() {
        return id;
    }
}