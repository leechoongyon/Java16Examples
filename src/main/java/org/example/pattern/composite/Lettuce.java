package org.example.pattern.composite;

public class Lettuce implements Ingredients {

  private final Integer calories;

  public Lettuce(final Integer calories) {
    this.calories = calories;
  }

  @Override
  public Integer getCalories() {
    return calories;
  }
}
