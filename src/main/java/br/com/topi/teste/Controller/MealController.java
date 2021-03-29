package br.com.topi.teste.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.topi.teste.TesteApplication;
import br.com.topi.teste.CustomException.ResourceNotFoundException;
import br.com.topi.teste.Entity.Meal;
import br.com.topi.teste.Repository.MealRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class MealController {
	private static final Logger logger = LoggerFactory.getLogger(TesteApplication.class);
	
	@Autowired
	private MealRepository repository;
	
	@GetMapping("/meal")
	public List<Meal> getAllMeals(){
		logger.info("Obter todos os meals");
		return repository.findAll();
	}
	
	@GetMapping("/meal/{id}")
	public ResponseEntity<Meal> getMealById(@PathVariable(value = "id") String mealId) throws ResourceNotFoundException{
		logger.info("Obter meal pelo ID");
		Meal meal = repository.findById(mealId).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
		return ResponseEntity.ok().body(meal);
	}
	
	@PostMapping("/meal")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Meal createMeal(@RequestBody Meal meal) {
		logger.info("Inserindo meal");
		return repository.save(meal);
	}
	
	@PutMapping("/meal/{id}")
	public ResponseEntity<Meal> editMeal(@RequestBody Meal updatedMeal, @PathVariable(value = "id") String mealId) throws ResourceNotFoundException{
		logger.info("Editando meal");
		Meal meal = repository.findById(mealId).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
		meal.setStrMeal(updatedMeal.getStrMeal());
		meal.setStrInstructions(updatedMeal.getStrInstructions());
		return ResponseEntity.ok().body(meal);
	}
	
	@DeleteMapping("/meal/{id}")
	public void deleteMeal(@PathVariable(value = "id") String mealId) throws ResourceNotFoundException {
		logger.info("Deletando meal");
		Meal meal = repository.findById(mealId).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
		repository.delete(meal);
	}
}
