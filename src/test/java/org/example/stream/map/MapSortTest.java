package org.example.stream.map;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MapSortTest {


  @Test
  @DisplayName("String type value 로 map sort")
  void testMapSortStringValueTest() {
    final Map<Integer, String> map = Map.of(
        10, "abc",
        5, "def",
        2, "adef"
    );

    final Map<Integer, String> sortedMap =
        map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue,
                LinkedHashMap::new)
            );

    Assertions.assertEquals(Map.of(10, "abc", 2, "adef", 5, "def"), sortedMap);
  }

  @Test
  @DisplayName("String type value 로 map reverse sort")
  void testMapReverseSortStringValueTest() {
    final Map<Integer, String> map = Map.of(
        10, "abc",
        5, "def",
        2, "adef"
    );

    final Map<Integer, String> sortedMap =
        map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue,
                LinkedHashMap::new)
            );

    Assertions.assertEquals(
        Map.of(
            5, "def",
            2, "adef",
            10, "abc"
        ),
        sortedMap
    );
  }

  @Test
  @DisplayName("Object value 로 Map sort")
  void testMapSortObjectValueTest() {
    final Map<Integer, Value> map =
        Map.of(3, Value.builder()
                .id("123")
                .name("test3")
                .build(),
            2, Value.builder()
                .id("234")
                .name("test2")
                .build(),
            1, Value.builder()
                .id("345")
                .name("test1")
                .build()
        );

    final Map<Integer, Value> sortedMap =
        map.entrySet()
            .stream()
            .sorted(Entry.comparingByValue(Comparator.comparing(Value::getName)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

    Assertions.assertEquals(
        Map.of(
            1, Value.builder()
                .id("345")
                .name("test1")
                .build(),
            2, Value.builder()
                .id("234")
                .name("test2")
                .build(),
            3, Value.builder()
                .id("123")
                .name("test3")
                .build()
        ),
        sortedMap
    );
  }

  @Test
  @DisplayName("Object value CustomComparator Map sort")
  void testMapSortObjectValueCustomComparatorTest() {
    final Map<Integer, Value> map =
        Map.of(3, Value.builder()
                .id("123")
                .name("test3")
                .build(),
            2, Value.builder()
                .id("234")
                .name("test2")
                .build(),
            1, Value.builder()
                .id("345")
                .name("test1")
                .build()
        );

    final Map<Integer, Value> sortedMap =
        map.entrySet()
            .stream()
            .sorted(Comparator.comparing(e -> CustomComparator.getValueOrder(e.getValue().name)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

    Assertions.assertEquals(
        Map.of(
            1, Value.builder()
                .id("345")
                .name("test1")
                .build(),
            2, Value.builder()
                .id("234")
                .name("test2")
                .build(),
            3, Value.builder()
                .id("123")
                .name("test3")
                .build()
        ),
        sortedMap
    );
  }

  @Data
  @Builder
  @AllArgsConstructor
  public static class Value {

    private String id;
    private String name;
  }


  public static class CustomComparator {

    private static final Map<String, Integer> valueOrderMap =
        Map.of(
            "test1", 1,
            "test2", 2,
            "test3", 3
        );

    public static Integer getValueOrder(final String value) {
      return valueOrderMap.get(value);
    }
  }
}
