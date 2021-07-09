package com.example.springadvanced.init;

import com.example.springadvanced.print.PrintUtil;
import com.example.springadvanced.repos.IngredientsRepo;
import com.example.springadvanced.repos.LabelRepo;
import com.example.springadvanced.repos.ShampooRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;


@Component
public class AppInitializer implements CommandLineRunner {
    private final ShampooRepo shampooRepo;

    private final LabelRepo labelRepo;

    private final IngredientsRepo ingredientsRepo;

    public AppInitializer(ShampooRepo shampooRepo, LabelRepo labelRepo, IngredientsRepo ingredientsRepo) {
        this.shampooRepo = shampooRepo;
        this.labelRepo = labelRepo;
        this.ingredientsRepo = ingredientsRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        printSeparator();

        // ex 1 - select shampoos by size
        // shampooRepo.findBySizeOrderById(Size.MEDIUM).forEach(PrintUtil::printShampoo);
        // printSeparator();

        // ex 2 - find shampoos by size or label
        // shampooRepo.findBySizeOrLabel(Size.MEDIUM, labelRepo.findByTitle("Shine & Hydration").get(0))
        //         .forEach(PrintUtil::printShampoo);
        // printSeparator();

        // ex 3 - select shampoo by price
        // shampooRepo.findByPriceGreaterThanEqual(6).
        //         forEach(PrintUtil::printShampoo);
        // printSeparator();

        // ex 4 - select ingredient by name
        // ingredientsRepo.findByNameStartsWith("M").forEach(PrintUtil::printIngredient);
        // printSeparator();

        // ex 5 - select ingredients by names
        // ingredientsRepo.findByNameIn(List.of("Lavender", "Herbs", "Apple"))
        //         .forEach(PrintUtil::printIngredient);
        // printSeparator();

        // ex 6 - count shampoos by price
        // double price = 8.5;
        // System.out.println("count = " + shampooRepo.countByPriceLessThan(price));
        // printSeparator();

        // ex 7 - select shampoos by ingredients
        shampooRepo.findByIngredientsIn(Set.of("Berry", "Mineral-Collagen")).forEach(PrintUtil::printShampoo);
        printSeparator();

    }
    private void printSeparator(){
        System.out.println("-".repeat(170) + "\n");
    }
}
