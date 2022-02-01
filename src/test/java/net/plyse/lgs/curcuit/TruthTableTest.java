/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.curcuit;

import net.plyse.lgs.connection.Connection;
import net.plyse.lgs.connection.Output;
import net.plyse.lgs.connection.SingleLineConnection;
import net.plyse.lgs.curcuit.sequential.SequentialCircuit;
import net.plyse.lgs.gate.And;
import net.plyse.lgs.gate.LogicGate;
import net.plyse.lgs.gate.Or;
import net.plyse.lgs.gate.Xor;
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

    @Test
    void testGetTruthTable() {
        SingleLineConnection a = new SingleLineConnection();
        SingleLineConnection b = new SingleLineConnection();
        SingleLineConnection c = new SingleLineConnection();
        SingleLineConnection sum = new SingleLineConnection();
        SingleLineConnection carry = new SingleLineConnection();

        LogicGate xorOuter = new Xor(2);
        xorOuter.connectToInput(a, b);
        SingleLineConnection xorOuterOutputXorInnerInput = new SingleLineConnection();
        xorOuter.connectToOutput(xorOuterOutputXorInnerInput);

        LogicGate xorInner = new Xor(2);
        xorInner.connectToInput(xorOuterOutputXorInnerInput, c);
        xorInner.connectToOutput(sum);

        SingleLineConnection andUpperOut = new SingleLineConnection();
        LogicGate andUpper = new And(2);
        andUpper.connectToInput(c, xorOuterOutputXorInnerInput);
        andUpper.connectToOutput(andUpperOut);


        SingleLineConnection andLowerOut = new SingleLineConnection();
        LogicGate andLower = new And(2);
        andLower.connectToInput(a, b);
        andLower.connectToOutput(andLowerOut);

        LogicGate or = new Or(2);
        or.connectToInput(andLowerOut, andUpperOut);
        or.connectToOutput(carry);

        Output carryOutput = null, sumOutput = null;

        try {
            Field field = LogicGate.class.getDeclaredField("output");
            field.setAccessible(true);
            carryOutput = (Output) field.get(or);
            sumOutput = (Output) field.get(xorInner);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


        TruthTable truthTable = SequentialCircuit
                .getTruthTable(new Connection[]{a, b, c}, new Output[]{sumOutput, carryOutput});

        boolean[][] truthTable1 = truthTable.getTruthTable();
        boolean[] booleans = truthTable1[3];

        assertArrayEquals(new boolean[5], truthTable1[0]);                                 // 000 00
        assertArrayEquals(new boolean[]{false, false, true, true, false}, truthTable1[1]); // 001 10
        assertArrayEquals(new boolean[]{false, true, false, true, false}, truthTable1[2]); // 010 10
        assertArrayEquals(new boolean[]{false, true, true, false, true}, booleans);  // 011 01
        assertArrayEquals(new boolean[]{true, false, false, true, false}, truthTable1[4]); // 100 10
        assertArrayEquals(new boolean[]{true, false, true, false, true}, truthTable1[5]);  // 101 01
        assertArrayEquals(new boolean[]{true, true, false, false, true}, truthTable1[6]);  // 110 01
        assertArrayEquals(new boolean[]{true, true, true, true, true}, truthTable1[7]);    // 111 11

    }




}