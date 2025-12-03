package com.architecture;

import com.google.inject.Inject;

/**
 * Класс для управления процессом (теперь управляется через Guice)
 */
public class BIMCollaboration {
    
    // Поле для сервиса базы данных
    private final ArchitectureService dbService;

    // Внедряем зависимость через конструктор (@Inject)
    @Inject
    public BIMCollaboration(ArchitectureService dbService) {
        this.dbService = dbService;
    }

    // Это больше не static main, а обычный метод запуска
    public void startDemo() {
        // --- Ваш код создания объектов ---
        Architect architect = new Architect("A1", "Іван Петренко");
        Customer customer = new Customer("C1", "Марія Коваленко");
        Engineer engineer = new Engineer("E1", "Петро Сидоренко");

        // Створення моделі
        Model model = new Model("M1", 1);
        architect.setCurrentModel(model);
        engineer.setModel(model);

        // --- Сохраняем информацию в БД (Новая часть) ---
        dbService.logAction("Создание модели", "Модель M1 (ver 1) создана");

        // Демонстрація процесу співпраці
        System.out.println("=== Початок демонстрації ===");
        
        // Вхід користувачів в систему
        architect.login();
        customer.login();
        engineer.login();

        // Публікація моделі архітектором
        architect.publishModel();
        dbService.logAction("Публикация", "Архитектор опубликовал модель");
        
        // Рецензування моделі
        customer.commentConcept();
        engineer.reviewModel();
        engineer.checkConflicts();
        engineer.sendRequest();
        
        // Оновлення моделі
        architect.releaseUpdate();
        dbService.logAction("Обновление", "Выпущено обновление модели");
        
        // Затвердження концепту
        customer.approveConcept();

        System.out.println("=== Кінець демонстрації ===");
    }
}