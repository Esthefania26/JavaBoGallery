package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    public List<Plan> findAll() throws Exception;

    public Plan findById(int id);

    public void create(Plan plan);
    public void update(Plan plan);
    public void delete(Plan plan);
}
