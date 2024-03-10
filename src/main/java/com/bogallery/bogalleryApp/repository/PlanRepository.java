package com.bogallery.bogalleryApp.repository;


import com.bogallery.bogalleryApp.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {
    @Query(value = "select p From Plan p where p.id=id")
    public Plan findById(int id);
}
