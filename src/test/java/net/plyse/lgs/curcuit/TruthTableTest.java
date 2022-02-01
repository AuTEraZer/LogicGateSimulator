/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.curcuit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Raphael Dichler on 01.02.2022.
 */
class TruthTableTest {

    @ParameterizedTest
    @MethodSource("constructorProvider")
    void testConstructor(int inputs, int outputs, boolean[][] result) {
        TruthTable truthTable = new TruthTable(inputs, outputs);
        try {
            Field field = truthTable.getClass().getDeclaredField("truthTable");
            field.setAccessible(true);
            boolean[][] table = (boolean[][]) field.get(truthTable);
            for (int i = (1 << inputs) - 1; i >= 0; i--) {
                for (int j = 0; j < inputs; j++) {
                    assertEquals(result[i][j], table[i][j]);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static Stream<Arguments> constructorProvider()  {
        return Stream.of(
                Arguments.of(3, 1, new boolean[][] {
                        {false, false, false, false},
                        {false, false, true, false},
                        {false, true, false, false},
                        {false, true, true, false},
                        {true, false, false, false},
                        {true, false, true, false},
                        {true, true, false, false},
                        {true, true, true, false},
                }),
                Arguments.of(3, 2, new boolean[][] {
                        {false, false, false, false, false},
                        {false, false, true, false, false},
                        {false, true, false, false, false},
                        {false, true, true, false, false},
                        {true, false, false, false, false},
                        {true, false, true, false, false},
                        {true, true, false, false, false},
                        {true, true, true, false, false},
                }),
                Arguments.of(4, 2, new boolean[][] {
                        {false, false, false, false, false, false},
                        {false, false, false, true, false, false},
                        {false, false, true, false, false, false},
                        {false, false, true, true, false, false},
                        {false, true, false, false, false, false},
                        {false, true, false, true, false, false},
                        {false, true, true, false, false, false},
                        {false, true, true, true, false, false},
                        {true, false, false, false, false, false},
                        {true, false, false, true, false, false},
                        {true, false, true, false, false, false},
                        {true, false, true, true, false, false},
                        {true, true, false, false, false, false},
                        {true, true, false, true, false, false},
                        {true, true, true, false, false, false},
                        {true, true, true, true, false, false},
                    })
                );
    }

    @Test
    void testInvalidConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TruthTable(-1, -2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new TruthTable(-1, -2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new TruthTable(-1, 0);
        });

    }





}