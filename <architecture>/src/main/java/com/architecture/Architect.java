package com.architecture;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Architect extends User implements IReviewer {
    private Model currentModel;

    public Architect(String id, String name) {
        super(id, name);
    }
    
    // --- ВАШИ МЕТОДЫ (publishModel, login и т.д.) ---
    // (Убедитесь, что они здесь есть, как в вашем коде)
    public void publishModel() { System.out.println("Архитектор публикует модель..."); }
    public void releaseUpdate() { System.out.println("Архитектор обновляет модель..."); }
    public void setCurrentModel(Model m) { this.currentModel = m; }
    public void login() { System.out.println("Архитектор вошел в систему."); }
    @Override public void reviewModel() {}

    // --- НОВАЯ ТОЧКА ВХОДА ---
    public static void main(String[] args) {
        try {
            System.out.println("Запуск через Google Guice...");
            
            // 1. Создаем "инжектор" (он читает настройки из Module)
            Injector injector = Guice.createInjector(new ArchitectureModule());

            // 2. Просим его создать BIMCollaboration.
            // Guice увидит @Inject в конструкторе BIMCollaboration и автоматически создаст ArchitectureService
            BIMCollaboration app = injector.getInstance(BIMCollaboration.class);

            // 3. Запускаем демо
            app.startDemo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}