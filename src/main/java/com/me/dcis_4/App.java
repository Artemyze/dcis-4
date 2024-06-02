package com.me.dcis_4;

//Импорты для работы с Spring, Java и сущностями приложения.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.me.dcis_4.entity.Briefcase;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


/**
 * Класс для запуска приложения.
 */
public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
	    ConsoleApp consoleApp = context.getBean(ConsoleApp.class);
	    consoleApp.run();
	    context.close();
	}
}
	

/**
 * Класс ConsoleApp, содержащий основную логику приложения.
 * 
 * @SpringBootApplication - аннотация, указывающая что данный класс является точкой входа для Spring Boot приложения.
 * Она включает в себя аннотации @Configuration, @EnableAutoConfiguration и @ComponentScan.
 */
@SpringBootApplication
class ConsoleApp {
    /**
     * Поле для хранения репозитория Briefcase, инъекция которого происходит с помощью Spring.
     */
    @Autowired
    private Repos repos;
    
    /**
     * Поле для хранения контекста Spring.
     */
    ApplicationContext context;
    
    /**
     * Конструктор класса ConsoleApp, инициализирующий поля класса.
     * 
     * @param applicationContext Контекст Spring
     */
    public ConsoleApp(ApplicationContext applicationContext) {
        this.repos = applicationContext.getBean(Repos.class);
        this.context = applicationContext;
    }
    
    /**
     * Метод запускающего приложение.
     */
    public void run() {
        // Создание сканера для ввода данных с консоли
        Scanner scanner = new Scanner(System.in);
        
        // Бесконечный цикл для отображения меню и обработки пользовательского ввода
        while (true) {
            System.out.println("Выберите опцию:");
            System.out.println("1. Добавить продукт");
            System.out.println("2. Список всех продуктов");
            System.out.println("3. Редактировать продукт");
            System.out.println("4. Удалить продукт");
            System.out.println("5. Поиск продуктов");
            System.out.println("6. Выход");
            
            // Считывание выбора пользователя
            int option = scanner.nextInt();
            
            // Выбор действия в зависимости от выбора пользователя
            switch (option) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    listProducts();
                    break;
                case 3:
                    editProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    searchProducts(scanner);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неправильный ввод");
            }
        }
    }
    
	    
    /**
     * Создает новый объект Briefcase на основе ввода пользователя.
     * 
     * @param scanner объект сканера для чтения ввода пользователя
     * @return новый объект Briefcase с введенными значениями
     */
    private Briefcase getNewBriefcase(Scanner scanner) {
        // Получение имени
        System.out.println("Введите поле name(текст):");
        String name = scanner.next();

        // Получение описания
        System.out.println("Введите поле description(текст):");
        String description = scanner.next();

        // Получение категории
        System.out.println("Введите поле category(текст):");
        String category = scanner.next();

        // Получение производителя
        System.out.println("Введите поле manufacturer(текст):");
        String manufacturer = scanner.next();

        // Получение цены
        System.out.println("Введите поле price(дробное число):");
        double price = Double.parseDouble(scanner.next().trim());

        // Получение веса
        System.out.println("Введите поле weight(дробное число):");
        double weight = Double.parseDouble(scanner.next().trim());

        // Создание нового объекта Briefcase
        Briefcase briefcase = new Briefcase(name, description, manufacturer, category, price, weight);
        return briefcase;
    }
    
	    
    /**
     * Обновляет существующий объект Briefcase новыми значениями из другого объекта Briefcase.
     * 
     * @param existingBriefcase существующий объект Briefcase, который будет обновлен
     * @param newBriefcase новый объект Briefcase, из которого будут взяты новые значения
     * @return обновленный объект Briefcase
     * @throws SecurityException если возникнет ошибка безопасности при доступе к полям объекта
     */
    private Briefcase updateBriefcase(Briefcase existingBriefcase, Briefcase newBriefcase) throws SecurityException {
        // Получение массива всех полей объекта Briefcase, включая поля суперкласса
        Field[] allFields = Stream.concat(Arrays.stream(existingBriefcase.getClass().getDeclaredFields()), 
                                          Arrays.stream(existingBriefcase.getClass().getSuperclass().getDeclaredFields()))
                                      .toArray(Field[]::new);
        
        // Перебор всех полей
        for (Field field : allFields) {
            // Установка доступности поля для изменения
            field.setAccessible(true);
            
            try {
                // Получение нового значения поля из объекта newBriefcase
                Object newValue = field.get(newBriefcase);
                
                // Если новое значение не null, то обновляем поле existingBriefcase
                if (newValue!= null) {
                    field.set(existingBriefcase, newValue);
                }
            } catch (IllegalAccessException e) {
                // Обработка исключения доступа к полю
            }
        }
        
        // Возвращение обновленного объекта Briefcase
        return existingBriefcase;
    }
    
    
	    
    /**
     * Добавляет новый продукт в базу данных.
     * 
     * @param scanner объект сканера для получения данных о новом продукте
     */
    @Transactional
    private void addProduct(Scanner scanner) {
        Briefcase newProductBriefcase = getNewBriefcase(scanner);
        repos.save(newProductBriefcase);
        System.out.println(context.getBean("entityManagerFactory"));
        System.out.println("Продукт был успешно добавлен!");
    }

    /**
     * Выводит список всех продуктов из базы данных.
     */
    private void listProducts() {
        List<Briefcase> briefcases = repos.findAll();
        for (Briefcase briefcase : briefcases) {
            System.out.println(briefcase);
        }
    }
	    
    /**
     * Редактирует продукт в репозитории.
     * 
     * @param scanner сканер для ввода данных
     */
    @Transactional
    private void editProduct(Scanner scanner) {
        System.out.println("Enter product ID:");
        Integer id = scanner.nextInt();
        Briefcase existingBriefcase = repos.findById(id).orElse(null);
        if (existingBriefcase != null) {
            Briefcase newBriefcase = getNewBriefcase(scanner);
            existingBriefcase = updateBriefcase(existingBriefcase, newBriefcase);
            repos.save(existingBriefcase);
            System.out.println("Продукт был успешно измененен!");
        } else {
            System.out.println("Продукт не найден!");
        }
    }

    /**
     * Удаляет продукт из репозитория.
     * 
     * @param scanner сканер для ввода данных
     */
    @Transactional
    private void deleteProduct(Scanner scanner) {
        System.out.println("Enter product ID:");
        Integer id = scanner.nextInt();
        repos.deleteById(id);
        System.out.println("Продукт удален!");
    }
	    
    
    /**
     * Ищет продукты в репозитории по заданным критериям.
     * 
     * @param scanner сканер для ввода данных
     */
    private void searchProducts(Scanner scanner) {
        System.out.println("Введите по очереди значения полей, разделяя их кнопкой Enter. Поля для ввода по порядку - name, description, category:");
        String name = scanner.next();
        String description = scanner.next();
        String category = scanner.next();
        List<Briefcase> briefcases = repos.findByCategoryOrDescriptionOrName(category, description, name);
        for (Briefcase briefcase: briefcases) {
            System.out.println(briefcase);
        }
    }
}
