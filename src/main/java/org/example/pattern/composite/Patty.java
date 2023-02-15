package org.example.pattern.composite;

public class Patty implements Ingredients {

  private final Integer calories;

  public Patty(final Integer calories) {
    this.calories = calories;
  }

  @Override
  public Integer getCalories() {
    return calories;
  }
}
