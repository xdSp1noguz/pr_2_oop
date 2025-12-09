package com.architecture;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Architect extends User implements IReviewer {
    private Model currentModel;

    public Architect(String id, String name) {
        super(id, name);
    }

    // --- ГЕТТЕРЫ ---
    // Мы убрали @Override и сами методы getId/getName, 
    // потому что теперь они автоматически берутся из класса User.
    // DatabaseService всё равно их увидит.

    public Model getCurrentModel() {
        return this.currentModel;
    }

    public void setCurrentModel(Model model) {
        this.currentModel = model;
    }

    // --- ОСТАЛЬНЫЕ МЕТОДЫ ---
    public void publishModel() {
        // getName() теперь берется напрямую из User
        System.out.println("Архитектор " + getName() + " публікує модель");
        if (currentModel != null) {
            currentModel.view();
        } else {
            System.out.println("Модель не обрана.");
        }
    }

    public final void releaseUpdate() {
        System.out.println("Архитектор " + getName() + " випускає оновлену версію моделі");
    }

    @Override
    public void reviewModel() {
        System.out.println("Архитектор " + getName() + " переглядає модель");
    }

    public void login() {
        System.out.println("Архитектор " + getName() + " увійшов у систему.");
    }

    // --- MAIN ---
    public static void main(String[] args) {
        try {
            System.out.println("Запуск програми...");

            // 1. Работа с БД
            DatabaseService dbService = new DatabaseService();
            dbService.initDatabase();

            Architect myArch = new Architect("arch-1", "Steve Jobs");
            Model myModel = new Model("AppleCampus", 1);
            myArch.setCurrentModel(myModel);

            System.out.println(">>> Сохранение...");
            dbService.saveArchitect(myArch);
            
            // 2. Веб и Guice (если нужно)
            // Injector injector = Guice.createInjector(new ArchitectureModule());
            // runWebMode(injector);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void runWebMode(Injector injector) {
        ArchitectWebView webView = injector.getInstance(ArchitectWebView.class);
        webView.start(8080);
    }
}