package com.architecture;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Клас для представлення архітектора в системі
 */
public class Architect extends User implements IReviewer {
    private Model currentModel;

    /**
     * Конструктор класу Architect
     */
    public Architect(String id, String name) {
        super(id, name);
    }

    /**
     * Геттер для отримання поточної моделі (НЕОБХІДНИЙ для DatabaseService)
     */
    public Model getCurrentModel() {
        return this.currentModel;
    }

    /**
     * Метод для встановлення поточної моделі
     */
    public void setCurrentModel(Model model) {
        this.currentModel = model;
    }

    // Забезпечуємо доступ до ID для бази даних
    @Override
    public String getId() {
        // Викликаємо метод батьківського класу User (або повертаємо поле id)
        return super.getId(); 
    }

    /**
     * ВАЖЛИВО: Змінив private на public, щоб DatabaseService міг отримати ім'я
     */
    @Override
    public String getName() {
        // Якщо у User немає методу getName(), повертаємо toString або реалізуємо логіку
        return super.toString(); 
    }

    /**
     * Метод для публікації моделі
     */
    public void publishModel() {
        System.out.println("Архітектор " + getName() + " публікує модель");
        if (currentModel != null) {
            currentModel.view();
        } else {
            System.out.println("Модель не обрана.");
        }
    }

    /**
     * Метод для випуску оновлення моделі
     */
    public final void releaseUpdate() {
        System.out.println("Архітектор " + getName() + " випускає оновлену версію моделі");
    }

    @Override
    public void reviewModel() {
        System.out.println("Архітектор " + getName() + " переглядає модель");
    }

    public void login() {
        System.out.println("Архітектор " + getName() + " увійшов у систему.");
    }

    // ==========================================
    // ТОЧКА ВХОДУ (MAIN) І ВЕБ-ЗАПУСК
    // ==========================================

    public static void main(String[] args) {
        try {
            System.out.println("Запуск програми...");

            // -----------------------------------------------------------
            // 1. БЛОК ЗБЕРЕЖЕННЯ В БАЗУ ДАНИХ (Виконуємо ваше завдання)
            // -----------------------------------------------------------
            System.out.println(">>> Підключення до бази даних...");
            DatabaseService dbService = new DatabaseService();
            
            // Створюємо таблиці, якщо їх немає
            dbService.initDatabase();

            // Створюємо тестового архітектора та модель
            Architect myArch = new Architect("arch-system-1", "Головний Архітектор");
            Model myModel = new Model("Project-Alpha", 1);
            
            // Встановлюємо зв'язок (Агрегація)
            myArch.setCurrentModel(myModel);

            // Зберігаємо в файл бази даних
            dbService.saveArchitect(myArch);
            System.out.println(">>> Збереження завершено.");
            // -----------------------------------------------------------


            // 2. Створюємо Injector (Guice)
            Injector injector = Guice.createInjector(new ArchitectureModule());

            // 3. Демонстрація консольної роботи
            BIMCollaboration app = injector.getInstance(BIMCollaboration.class);
            // app.startDemo(); // Можна розкоментувати, якщо потрібно

            // 4. ЗАПУСК ВЕБ-РЕЖИМУ
            runWebMode(injector);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runWebMode(Injector injector) {
        System.out.println("Ініціалізація веб-інтерфейсу...");
        // Отримуємо екземпляр класу-вигляду через Guice
        ArchitectWebView webView = injector.getInstance(ArchitectWebView.class);
        // Запускаємо сервер на порту 8080
        webView.start(8080);
    }
}