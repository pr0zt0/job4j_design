package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyArr() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkStartSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: = does not contain a key");
    }

    @Test
    void checkContainSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name:  does not contain the symbol \"=\"");
    }

    @Test
    void checkContainValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Jhon="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: Jhon= does not contain a value");
    }

}