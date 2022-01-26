/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.gate;

import net.plyse.lgs.connection.Input;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Raphael Dichler on 26.01.2022.
 */
public class LogicGateTest {

    @ParameterizedTest
    @MethodSource("andProvider")
    void testAndUpdate(boolean expectedOutput, LogicGate gate) {
        gate.update(true);
        assertEquals(expectedOutput, gate.status);
    }

    static Stream<Arguments> andProvider()  {
        return Stream.of(
               Arguments.of(true, inputToLogicGate(LogicGates.AND, 0b111111)),
               Arguments.of(true, inputToLogicGate(LogicGates.AND, 0b1)),
               Arguments.of(false, inputToLogicGate(LogicGates.AND, 0b101111)),
               Arguments.of(false, inputToLogicGate(LogicGates.AND, 0b101010)),
               Arguments.of(false, inputToLogicGate(LogicGates.AND, 0b001010)),
               Arguments.of(false, inputToLogicGate(LogicGates.AND, 0b0000)),
               Arguments.of(false, inputToLogicGate(LogicGates.AND, 0b0))
        );
    }

    @ParameterizedTest
    @MethodSource("orProvider")
    void testOrUpdate(boolean expectedOutput, LogicGate gate) {
        gate.update(false);
        assertEquals(expectedOutput, gate.status);
    }

    static Stream<Arguments> orProvider()  {
        return Stream.of(
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b111111)),
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b1)),
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b101111)),
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b101010)),
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b001010)),
                Arguments.of(false, inputToLogicGate(LogicGates.OR, 0b0000)),
                Arguments.of(false, inputToLogicGate(LogicGates.OR, 0b0)),
                Arguments.of(true, inputToLogicGate(LogicGates.OR, 0b1))
        );
    }

    @ParameterizedTest
    @MethodSource("notProvider")
    void testNotUpdate(boolean expectedOutput, LogicGate gate) {
        gate.update(false);
        assertEquals(expectedOutput, gate.status);
    }

    static Stream<Arguments> notProvider()  {
        return Stream.of(
                Arguments.of(false, inputToLogicGate(LogicGates.NOT, 0b1)),
                Arguments.of(true, inputToLogicGate(LogicGates.NOT, 0b0))
        );
    }

    @ParameterizedTest
    @MethodSource("xorProvider")
    void testXorUpdate(boolean expectedOutput, LogicGate gate) {
        gate.update(false);
        assertEquals(expectedOutput, gate.status);
    }

    static Stream<Arguments> xorProvider()  {
        return Stream.of(
                Arguments.of(true, inputToLogicGate(LogicGates.XOR, 0b10)),
                Arguments.of(false, inputToLogicGate(LogicGates.XOR, 0b00)),
                Arguments.of(false, inputToLogicGate(LogicGates.XOR, 0b011110)),
                Arguments.of(true, inputToLogicGate(LogicGates.XOR, 0b0111101)),
                Arguments.of(false, inputToLogicGate(LogicGates.XOR, 0b0000001)),
                Arguments.of(false, inputToLogicGate(LogicGates.XOR, 0b000000110)),
                Arguments.of(true, inputToLogicGate(LogicGates.XOR, 0b110))
        );
    }

    private static LogicGate inputToLogicGate(LogicGates gate, int binary) {
        String s = Integer.toBinaryString(binary);
        boolean[] arr = new boolean[s.length()];
        IntStream.range(0, s.length()).forEach(i -> arr[i] = s.charAt(i) == '1');
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
        return logicGate;
    }

    private static Input[] createInputs(boolean[] in, LogicGate logicGate) {
        List<Input> inputList = new ArrayList<>();

        for (boolean b : in) {
            inputList.add(new Input(logicGate, b));
        }

        return inputList.toArray(Input[]::new);
    }

    private enum LogicGates {
        AND, OR, NOT, XOR
    }

}