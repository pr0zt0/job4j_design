package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert sc = new SimpleConvert();
        List<String> list = sc.toList("first", "second", "three", "four", "five");

        assertThat(list)
                .hasSize(5)
                .filteredOn(e -> e.length() > 5)
                .doesNotContain("first", "four")
                .first().isEqualTo("second");
    }

    @Test
    void checkSet() {
        SimpleConvert sc = new SimpleConvert();
        Set<String> set = sc.toSet("first", "first", "second");

        assertThat(set)
                .hasSize(2)
                .allSatisfy(e->{
                    assertThat(e).doesNotContain("a");
                })
                .noneMatch(e -> e.length() == 0);
    }

    @Test
    void checkMap() {
        SimpleConvert sc = new SimpleConvert();
        Map<String, Integer> map = sc.toMap("Anna", "Tomato", "Jhon", "Orange");

        assertThat(map)
                .containsKeys("Anna", "Jhon")
                .containsValues(0, 1)
                .doesNotContainValue(12)
                .doesNotContainKeys("Petr")
                .containsEntry("Anna", 0);
    }
}