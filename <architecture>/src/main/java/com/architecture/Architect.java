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

    /**
     * Метод для встановлення поточної моделі
     */
    public void setCurrentModel(Model model) {
        this.currentModel = model;
    }

    public void login() {
        System.out.println("Архітектор " + getName() + " увійшов у систему.");
    }

    // Допоміжний метод для отримання імені (якщо поле name в User приватне)
    private String getName() {
        return super.toString(); 
    }

    // ==========================================
    // ТОЧКА ВХОДУ (MAIN) І ВЕБ-ЗАПУСК
    // ==========================================

    public static void main(String[] args) {
        try {
            System.out.println("Запуск програми...");
            
            // 1. Створюємо Injector (Guice)
            Injector injector = Guice.createInjector(new ArchitectureModule());

            // 2. Демонстрація консольної роботи
            BIMCollaboration app = injector.getInstance(BIMCollaboration.class);
            app.startDemo();

            // 3. ЗАПУСК ВЕБ-РЕЖИМУ
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