package com.architecture;

/**
 * Клас для представлення замовника в системі
 */
public class Customer extends User {
    /**
     * Конструктор класу Customer
     * @param id Ідентифікатор замовника
     * @param name Ім'я замовника
     */
    public Customer(String id, String name) {
        super(id, name);
    }

    /**
     * Метод для коментування концепту
     */
    public void commentConcept() {
        System.out.println("Замовник " + name + " коментує концепт");
    }

    /**
     * Метод для затвердження концепту
     */
    public void approveConcept() {
        System.out.println("Замовник " + name + " затверджує концепт");
    }
}