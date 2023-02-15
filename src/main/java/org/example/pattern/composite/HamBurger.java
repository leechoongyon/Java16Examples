package org.example.pattern.composite;

import java.util.ArrayList;
import java.util.List;

public class HamBurger {

  private final List<Ingredients> ingredientsList = new ArrayList<>();

  private HamBurger() {
  }

  private HamBurger(final List<Ingredients> ingredients) {
    ingredientsList.addAll(ingredients);
  }

  public static HamBurger from(final List<Ingredients> ingredientsList) {
    return new HamBurger(ingredientsList);
  }

  public Integer calculateCalories() {
    var totalCalories = 0;
    for (final Ingredients ingredients : ingredientsList) {
      totalCalories += ingredients.getCalories();
    }
    return totalCalories;
  }
}
