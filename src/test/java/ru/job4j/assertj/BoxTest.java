package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
        assertThat(box.isExist()).isTrue();
        assertThat(box.getArea()).isNotZero()
                .isGreaterThan(1)
                .isEqualTo(1256.6370614359173);
        assertThat(box.getNumberOfVertices())
                .isZero();
    }

    @Test
    void isThisUnknow() {
        Box box = new Box(456, -1);
        assertThat(box.whatsThis())
                .isEqualTo("Unknow object");
        assertThat(box.isExist()).isFalse();
        assertThat(box.getArea())
                .isLessThan(1)
                .isEqualTo(0);
        assertThat(box.getNumberOfVertices())
                .isNotZero();
    }
}