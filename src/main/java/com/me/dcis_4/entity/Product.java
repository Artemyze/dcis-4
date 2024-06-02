package com.me.dcis_4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Класс Product, представляющий собой продукт.
 */
@Entity
@Table(name="products")
public class Product {
    
    /**
     * Идентификатор продукта.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Название продукта.
     */
    private String name;
    
    /**
     * Категория продукта.
     */
    private String category;
    
    /**
     * Описание продукта.
     */
    private String description;

    /**
     * Защищенный конструктор для создания объекта Product.
     */
    public Product() {
    }

    /**
     * Публичный конструктор для создания объекта Product с заданными параметрами.
     * 
     * @param name        название продукта
     * @param description описание продукта
     * @param category    категория продукта
     */
    public Product(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }
    
    /**
     * Возвращает идентификатор продукта.
     * 
     * @return идентификатор продукта
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор продукта.
     * 
     * @param id идентификатор продукта
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Возвращает название продукта.
     * 
     * @return название продукта
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название продукта.
     * 
     * @param name название продукта
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает категорию продукта.
     * 
     * @return категория продукта
     */
    public String getCategory() {
        return category;
    }

    /**
     * Устанавливает категорию продукта.
     * 
     * @param category категория продукта
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Возвращает описание продукта.
     * 
     * @return описание продукта
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание продукта.
     * 
     * @param description описание продукта
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Переопределенный метод toString для объекта Product.
     * 
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category + "]";
    }

    /**
     * Переопределенный метод hashCode для объекта Product.
     * 
     * @return хеш-код объекта
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null)? 0 : category.hashCode());
        result = prime * result + ((description == null)? 0 : description.hashCode());
        result = prime * result + ((id == null)? 0 : id.hashCode());
        result = prime * result + ((name == null)? 0 : name.hashCode());
        return result;
    }

    /**
     * Переопределенный метод equals для объекта Product.
     * 
     * @param obj объект для сравнения
     * @return true, если объекты равны, false - в противном случае
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass()!= obj.getClass())
            return false;
        Product other = (Product) obj;
        if (category == null) {
            if (other.category!= null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (description == null) {
            if (other.description!= null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id!= null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name!= null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}