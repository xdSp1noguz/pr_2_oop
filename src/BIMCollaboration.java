/**
 * Головний клас для демонстрації роботи системи
 */
public class BIMCollaboration {
    public static void main(String[] args) {
        // Створення користувачів
        Architect architect = new Architect("A1", "Іван Петренко");
        Customer customer = new Customer("C1", "Марія Коваленко");
        Engineer engineer = new Engineer("E1", "Петро Сидоренко");

        // Створення моделі
        Model model = new Model("M1", 1);
        architect.setCurrentModel(model);
        engineer.setModel(model);

        // Демонстрація процесу співпраці
        System.out.println("=== Початок демонстрації ===");
        
        // Вхід користувачів в систему
        architect.login();
        customer.login();
        engineer.login();

        // Публікація моделі архітектором
        architect.publishModel();
        
        // Рецензування моделі
        customer.commentConcept();
        engineer.reviewModel();
        engineer.checkConflicts();
        engineer.sendRequest();
        
        // Оновлення моделі
        architect.releaseUpdate();
        
        // Затвердження концепту
        customer.approveConcept();

        System.out.println("=== Кінець демонстрації ===");
    }
}