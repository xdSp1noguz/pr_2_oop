package com.architecture;

import com.google.inject.Inject;

/**
 * Клас для управління процесом (Setter Injection)
 */
public class BIMCollaboration {
    
    // 1. Прибираємо 'final', оскільки ініціалізація буде пізніше через сетер
    private ArchitectureService dbService;

    // 2. Коментуємо конструктор (Constructor Injection більше не використовується)
    /*
    @Inject
    public BIMCollaboration(ArchitectureService dbService) {
        this.dbService = dbService;
    }
    */

    // 3. Додаємо Setter-метод для впровадження залежності
    @Inject
    public void setArchitectureService(ArchitectureService dbService) {
        this.dbService = dbService;
    }

    public void startDemo() {
        // --- Перевірка, чи залежність впровадилась ---
        if (dbService == null) {
            System.err.println("ПОМИЛКА: Сервіс бази даних не ініціалізовано!");
            return;
        }

        Architect architect = new Architect("A1", "Іван Петренко");
        Customer customer = new Customer("C1", "Марія Коваленко");
        Engineer engineer = new Engineer("E1", "Петро Сидоренко");

        Model model = new Model("M1", 1);
        architect.setCurrentModel(model);
        engineer.setModel(model);

        // Використовуємо сервіс
        dbService.logAction("Setter Injection", "Успішно використано сетер для збереження");

        System.out.println("=== Початок демонстрації ===");
        
        architect.login();
        customer.login();
        engineer.login();

        architect.publishModel();
        dbService.logAction("Публікація", "Архітектор опублікував модель");
        
        customer.commentConcept();
        engineer.reviewModel();
        engineer.checkConflicts();
        engineer.sendRequest();
        
        architect.releaseUpdate();
        dbService.logAction("Оновлення", "Випущено оновлення моделі");
        
        customer.approveConcept();

        System.out.println("=== Кінець демонстрації ===");
    }
}