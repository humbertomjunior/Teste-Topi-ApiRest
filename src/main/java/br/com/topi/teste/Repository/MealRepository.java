package br.com.topi.teste.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.topi.teste.Entity.Meal;

@Repository
public interface MealRepository extends JpaRepository <Meal, String>{
}
