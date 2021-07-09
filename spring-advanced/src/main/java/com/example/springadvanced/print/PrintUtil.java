package com.example.springadvanced.print;

import com.example.springadvanced.entities.Ingredient;
import com.example.springadvanced.entities.Shampoo;

import java.util.stream.Collectors;

public class PrintUtil {
    public static void printShampoo(Shampoo shampoo) {
        System.out.printf("|%5d | %-30.30s | %-8.8s | %8.2f | %-40.40s | %-50.50s |%n",
                shampoo.getId(), shampoo.getBrand(), shampoo.getSize(),
                shampoo.getPrice(), shampoo.getLabel().getTitle() + "-" + shampoo.getLabel().getSubtitle(),
                shampoo.getIngredients().stream().map(Ingredient::getName)
                .collect(Collectors.joining(", ")));
    }
}
