package org.example.pattern.composite;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HamBurgerTest {

  @Test
  void testCalulateCalories() {
    final HamBurger hamBurger = HamBurger.from(
        List.of(
            new Patty(200),
            new Lettuce(50)
        )
    );
    Assertions.assertEquals(250, hamBurger.calculateCalories());
  }
}
