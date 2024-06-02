package com.me.dcis_4;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.dcis_4.entity.*;


//Интерфейс репозитория для работы с сущностью Briefcase
public interface Repos extends JpaRepository<Briefcase, Integer> {
	// Метод для поиска briefcases по имени и производителю
	List<Briefcase> findByNameAndManufacturer(String name, String manufacturer);
 
	// Метод для поиска briefcases по категории, описанию или имени
	List<Briefcase> findByCategoryOrDescriptionOrName(String category, String description, String name);
}


@Repository 
interface ProductRepository extends JpaRepository<Product, Integer> {
}
