/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.gate;

import net.plyse.lgs.connection.Input;
import net.plyse.lgs.connection.SingleLineConnection;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Raphael Dichler on 26.01.2022.
 */
public class LogicGateTest {

    @ParameterizedTest
    @MethodSource("andProvider")
    void testAndUpdate(boolean expectedOutput, LogicGate gate, boolean updateValue) {
        gate.update(updateValue);
        assertEquals(expectedOutput, gate.status);
    }

    static Stream<Arguments> andProvider()  {
        return Stream.of(
               Arguments.of(true, inputToLogicGate(LogicGates.AND, 0b1_111111), true),
               Arguments.of(true, inputToLogicGate(LogicGates.AND, 0b1_1), true),
               Arguments.of(false, inputToLogicGate(LogicGates.AND, 0b1_101111), false),
               Arguments.of(false, inputToLogicGate(LogicGates.AND, 0b1_101010), true),
               Arguments.of(false, inputToLogicGate(LogicGates.AND, 0b1_001010), true),
               Arguments.of(false, inputToLogicGate(LogicGates.AND, 0b1_0000), false),
               Arguments.of(false, inputToLogicGate(LogicGates.AND, 0b1_01), true)
        );
    }

    @ParameterizedTest
    @MethodSource("orProvider")
    void testOrUpdate(boolean expectedOutput, LogicGate gate, boolean updateValue) {
        gate.update(updateValue);
        assertEquals(expectedOutput, gate.status);
    }

    static Stream<Arguments> orProvider()  {
        return Stream.of(
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b1_111111), true),
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b1_1), true),
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b1_101111), false),
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b1_101010), false),
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b1_001010), true),
                Arguments.of(false, inputToLogicGate(LogicGates.OR, 0b1_0000), false),
                Arguments.of(false, inputToLogicGate(LogicGates.OR, 0b1_0), false),
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b1_1), true)
        );
    }

    @ParameterizedTest
    @MethodSource("notProvider")
    void testNotUpdate(boolean expectedOutput, LogicGate gate, boolean updateValue) {
        gate.update(updateValue);
        assertEquals(expectedOutput, gate.status);
    }

    static Stream<Arguments> notProvider()  {
        return Stream.of(
                Arguments.of(false, inputToLogicGate(LogicGates.NOT, 0b1_1), true),
                Arguments.of(true, inputToLogicGate(LogicGates.NOT, 0b1_0), false)
        );
    }

    @ParameterizedTest
    @MethodSource("xorProvider")
    void testXorUpdate(boolean expectedOutput, LogicGate gate, boolean updateValue) {
        gate.update(updateValue);
        assertEquals(expectedOutput, gate.status);
    }

    static Stream<Arguments> xorProvider()  {
        return Stream.of(
                Arguments.of(true, inputToLogicGate(LogicGates.XOR, 0b1_10), false),
                Arguments.of(false, inputToLogicGate(LogicGates.XOR, 0b1_00), false),
                Arguments.of(false, inputToLogicGate(LogicGates.XOR, 0b1_011110), false),
                Arguments.of(true, inputToLogicGate(LogicGates.XOR, 0b1_0111101), false),
                Arguments.of(false, inputToLogicGate(LogicGates.XOR, 0b1_0100001), true),
                Arguments.of(false, inputToLogicGate(LogicGates.XOR, 0b1_000000110), false),
                Arguments.of(true, inputToLogicGate(LogicGates.XOR, 0b1_1101), true)
        );
    }

    private static LogicGate inputToLogicGate(LogicGates gate, int binary) {
        String s = Integer.toBinaryString(binary);
        boolean[] arr = new boolean[s.length() - 1];
        for (int i = 1; i < s.toCharArray().length; i++) {
            arr[i-1] = s.charAt(i) == '1';
        }
        return inputToLogicGate(gate, arr);
    }

    private static LogicGate inputToLogicGate(LogicGates gate, boolean[] inputs) {
        LogicGate logicGate = null;
        switch (gate) {
            case AND -> logicGate = new And(inputs.length, false);
            case OR -> logicGate = new Or(inputs.length, false);
            case NOT -> logicGate = new Not( false);
            case XOR -> logicGate = new Xor(inputs.length, false);
        }

        logicGate.inputs = createInputs(inputs, logicGate);
        logicGate.output.connect(new SingleLineConnection());
        return logicGate;
    }

    private static Input[] createInputs(boolean[] in, LogicGate logicGate) {
        List<Input> inputList = new ArrayList<>();

        for (boolean b : in) {
            Input input = new Input(logicGate, b);
            input.connect(new SingleLineConnection());
            inputList.add(input);
        }

        return inputList.toArray(Input[]::new);
    }

    private enum LogicGates {
        AND, OR, NOT, XOR
    }

}