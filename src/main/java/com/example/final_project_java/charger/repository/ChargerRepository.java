package com.example.final_project_java.charger.repository;

import com.example.final_project_java.charger.Entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChargerRepository extends JpaRepository<ChargingStation, String>  {

}
