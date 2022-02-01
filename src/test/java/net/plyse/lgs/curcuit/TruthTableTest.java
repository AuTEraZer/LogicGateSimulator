/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.curcuit;

import net.plyse.lgs.gate.LogicGateTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Raphael Dichler on 01.02.2022.
 */
class TruthTableTest {

    @ParameterizedTest
    @MethodSource("constructorProvider")
    void constructorTest(int inputs, int outputs, boolean[][] result) {

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
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},
                        {false, false, false, false, false, false},

                }),
                );
    }






}