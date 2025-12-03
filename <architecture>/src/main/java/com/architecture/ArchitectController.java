package com.architecture;

import com.google.inject.Inject;
import java.util.List;

public class ArchitectController {
    
    private final ArchitectureService service;

    @Inject
    public ArchitectController(ArchitectureService service) {
        this.service = service;
    }

    public List<Model> getAllModels() {
        return service.getAllModels();
    }
}