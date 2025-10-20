/**
 * Клас для представлення 3D-моделі
 */
public class Model {
    private String modelId;
    private int version;

    /**
     * Конструктор класу Model
     * @param modelId Ідентифікатор моделі
     * @param version Версія моделі
     */
    public Model(String modelId, int version) {
        this.modelId = modelId;
        this.version = version;
    }

    /**
     * Метод для перегляду моделі
     */
    public void view() {
        System.out.println("Перегляд моделі " + modelId + " (версія " + version + ")");
    }
}