package com.me.dcis_4.entity;

import jakarta.persistence.*;

/**
 * Класс Briefcase, наследующий от Product, представляющий собой портфель.
 */
@Entity
public class Briefcase extends Product {
    
    /**
     * Идентификатор портфеля.
     */
    private Integer id;
    
    /**
     * Производитель портфеля.
     */
    private String manufacturer;
    
    /**
     * Вес портфеля.
     */
    private Double weight;
    
    /**
     * Цена портфеля.
     */
    private Double price;
    
    /**
     * Защищенный конструктор для создания объекта Briefcase.
     */
    protected Briefcase() {}
    
    /**
     * Публичный конструктор для создания объекта Briefcase с заданными параметрами.
     * 
     * @param name        название портфеля
     * @param description описание портфеля
     * @param category    категория портфеля
     * @param manufacturer производитель портфеля
     * @param weight      вес портфеля
     * @param price       цена портфеля
     */
    public Briefcase(String name, String description, String category, String manufacturer, Double weight, Double price) {
        super(name, description, category);
        this.manufacturer = manufacturer;
        this.weight = weight;
        this.price = price;
    }
    
    /**
     * Возвращает производителя портфеля.
     * 
     * @return производитель портфеля
     */
    public String getManufacturer() {
        return manufacturer;
    }
    
    /**
     * Устанавливает производителя портфеля.
     * 
     * @param manufacturer производитель портфеля
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    /**
     * Возвращает вес портфеля.
     * 
     * @return вес портфеля
     */
    public Double getWeight() {
        return weight;
    }
    
    /**
     * Устанавливает вес портфеля.
     * 
     * @param weight вес портфеля
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    /**
     * Возвращает цену портфеля.
     * 
     * @return цена портфеля
     */
    public Double getPrice() {
        return price;
    }
    
    /**
     * Устанавливает цену портфеля.
     * 
     * @param price цена портфеля
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    
    /**
     * Переопределенный метод hashCode для объекта Briefcase.
     * 
     * @return хеш-код объекта
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id!= null? id.hashCode() : 0);
        result = 31 * result + (manufacturer!= null? manufacturer.hashCode() : 0);
        result = 31 * result + (weight!= null? weight.hashCode() : 0);
        result = 31 * result + (price!= null? price.hashCode() : 0);
        return result;
    }
    
    /**
     * Переопределенный метод equals для объекта Briefcase.
     * 
     * @param obj объект для сравнения
     * @return true, если объекты равны, false - в противном случае
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass()!= obj.getClass())
            return false;
        Briefcase other = (Briefcase) obj;
        if (id == null) {
            if (other.id!= null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (manufacturer == null) {
            if (other.manufacturer!= null)
                return false;
        } else if (!manufacturer.equals(other.manufacturer))
            return false;
        if (price == null) {
            if (other.price!= null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (weight == null) {
            if (other.weight!= null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        return true;
    }
    
    /**
     * Переопределенный метод toString для объекта Briefcase.
     * 
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Briefcase{" +
                "id=" + super.getId() +
                ", manufacturer='" + manufacturer + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", category='" + super.getCategory() + '\'' +
                '}';
    }
    
}