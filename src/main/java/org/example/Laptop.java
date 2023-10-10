//Подумать над структурой класса Ноутбук (Laptop) для магазина техники - выделить поля и методы.
// Реализовать в java.
//Создать множество ноутбуков Set< >. (добиться чтобы во множестве не было одинаковых ноутбуков)
//        (*) Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и
//        выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
//        Например: “Введите цифру, соответствующую необходимому критерию:
//        1 - ОЗУ
//
//        2 - Объем ЖД
//
//        3 - Операционная система
//
//        4 - Цвет …
//
//        Далее нужно запросить минимальные значения для указанных критериев -
//        сохранить параметры фильтрации можно также в Map.
//        Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.


package org.example;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.Predicate;

public class Laptop {
    int ram;
    int hdd;
    String operationSystem;
    String color;
    String brandName;
    String screenType;

    public Laptop(int ram, int hdd, String operationSystem, String color){
        this.ram = ram;
        this.hdd = hdd;
        this.operationSystem = operationSystem;
        this.color = color;
    }

    public String toString() {
        return "Laptop {" +
                "RAM: " + ram + " GB, " +
                "HDD: " + hdd + " GB, " +
                "OS: " + operationSystem + ", " +
                "Color: " + color +
                (brandName != null ? ", Brand: " + brandName : "") +
                (screenType != null ? ", Screen Type: " + screenType : "") +
                "}";
    }

    public boolean equals(Object laptop) {
        if (this == laptop) return true;
        if (laptop == null || getClass() != laptop.getClass()) return false;

        Laptop newlaptop = (Laptop) laptop;

        return this.ram == newlaptop.ram &&
                this.hdd == newlaptop.hdd &&
                Objects.equals(this.operationSystem, newlaptop.operationSystem) &&
                Objects.equals(this.color, newlaptop.color) &&
                Objects.equals(this.brandName, newlaptop.brandName) &&
                Objects.equals(this.screenType, newlaptop.screenType);
    }

    public int hashCode(){
        return Objects.hash(ram, hdd, operationSystem, color, brandName, screenType);
    }

    private static void filterAndPrintLaptops(Set<Laptop> laptops) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ram");
        System.out.println("2 - hdd");
        System.out.println("3 - operationSystem");
        System.out.println("4 - color");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите значение для выбранного критерия:");

        Predicate<Laptop> filter = laptop -> true;

        switch (choice) {
            case 1:
                int ram = scanner.nextInt();
                filter = laptop -> laptop.ram >= ram;
                break;
            case 2:
                int hdd = scanner.nextInt();
                filter = laptop -> laptop.hdd >= hdd;
                break;
            case 3:
                String os = scanner.nextLine();
                filter = laptop -> laptop.operationSystem.equalsIgnoreCase(os);
                break;
            case 4:
                String color = scanner.nextLine();
                filter = laptop -> laptop.color.equalsIgnoreCase(color);
                break;
        }

        laptops.stream().filter(filter).forEach(System.out::println);
    }



    public static void main(String[] args) {
        Laptop laptop1 = new Laptop(32,500, "Windows", "Black");
        Laptop laptop2 = new Laptop(32,500, "Windows", "Black");
        Laptop laptop3 = new Laptop(64,1000, "MacOS", "White");
        Laptop laptop4 = new Laptop(128,2500, "Windows", "Rose");
        Laptop laptop5 = new Laptop(64,1500, "MacOS", "Blue");

        Set<Laptop> data = new HashSet<>(List.of(laptop1, laptop2, laptop3, laptop4, laptop5));

        System.out.println("Общий список ноутбуков, без идентичныx: ");
        for (Laptop laptop : data) {
            System.out.println(laptop);
        }
        System.out.println();
        filterAndPrintLaptops(data);
    }


}
