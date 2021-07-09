package com.example.springadvanced.init;

import com.example.springadvanced.entities.Size;
import com.example.springadvanced.print.PrintUtil;
import com.example.springadvanced.repos.IngredientsRepo;
import com.example.springadvanced.repos.LabelRepo;
import com.example.springadvanced.repos.ShampooRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        // ex 1 - select shampoos by size
       shampooRepo.findBySizeOrderById(Size.MEDIUM).forEach(PrintUtil::printShampoo);
        printSeparator();

    }
    private void printSeparator(){
        System.out.println("-".repeat(170) + "\n");
    }
}
