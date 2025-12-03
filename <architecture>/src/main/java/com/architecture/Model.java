package com.architecture;

public class Model {
    private String modelid;
    private int version;

    // Порожній конструктор (важливий для Jackson/JSON)
    public Model() {}

    public Model(String modelid, int version) {
        this.modelid = modelid;
        this.version = version;
    }

    public String getModelid() { return modelid; }
    public int getVersion() { return version; }

    // Метод для відображення в консолі
    public void view() {
        System.out.println("Displaying Model: " + modelid + " v." + version);
    }
    
    @Override
    public String toString() {
        return "Model{id='" + modelid + "', version=" + version + "}";
    }
}