package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Plan;
import com.bogallery.bogalleryApp.repository.PlanRepository;
import com.bogallery.bogalleryApp.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanImp implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public List<Plan> findAll() throws Exception {
        return this.planRepository.findAll();
    }

    // Elimina la anotación @Autowired de este método
    @Override
    public Plan findById(Long id) {
        return this.planRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Plan plan) {
        this.planRepository.save(plan);
    }

    @Override
    public void update(Plan plan) {
        this.planRepository.save(plan);
    }

    @Override
    public void delete(Plan plan) {
        this.planRepository.delete(plan);
    }
}
