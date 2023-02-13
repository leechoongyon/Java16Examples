package org.example.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StaticFactoryMethodTest {

  private static final String DEFAULT_ID = "10";
  private static final String DEFAULT_NAME = "test";

  /**
   * from : 매개 변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 method 명명 규칙입니다.
   */
  @Test
  @DisplayName("정적팩토리메서드 from 테스트")
  void testStaticFactoryMethodFromTest() {
    final var actual = Member.from(DEFAULT_NAME);
    final var expected = Member.builder()
        .id(DEFAULT_ID)
        .name(DEFAULT_NAME)
        .build();
    Assertions.assertEquals(expected, actual);
  }

  /**
   * of : 여러 매개변수를 받아 인스턴스를 반환
   */
  @Test
  @DisplayName("정적팩토리메서드 of 테스트")
  void testStaticFactoryMethodOfTest() {
    final var actual = Member.of(DEFAULT_ID, DEFAULT_NAME);
    final var expected = Member.builder()
        .id(DEFAULT_ID)
        .name(DEFAULT_NAME)
        .build();
    Assertions.assertEquals(expected, actual);
  }


  @Data
  @Builder
  @AllArgsConstructor
  public static class Member {


    private String id;
    private String name;

    public static Member from(final String name) {
      return Member.builder()
          .id(DEFAULT_ID)
          .name(name)
          .build();
    }

    public static Member of(final String id, final String name) {
      return Member.builder()
          .id(id)
          .name(name)
          .build();
    }

  }
}
