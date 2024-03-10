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

        @Autowired
        public Plan findById(int id){
            return this.planRepository.findById(id);


        }

        @Override
        public void create(Plan plan){
            this.planRepository.save(plan);
        }

        @Override
        public void update(Plan plan){
            this.planRepository.save(plan);
        }

        @Override
        public void delete(Plan plan){
            this.planRepository.save(plan);
        }


    }
