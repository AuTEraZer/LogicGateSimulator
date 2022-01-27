/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.connection;

import net.plyse.lgs.gate.And;
import net.plyse.lgs.gate.LogicGate;
import net.plyse.lgs.gate.Or;
import net.plyse.lgs.gate.Xor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * @author Raphael Dichler on 27.01.2022.
 */
public class ConnectionTest {

    @ParameterizedTest
    @MethodSource("connectionChangeBehaviorProvider")
    void testConnectionChangeBehavior(LogicGate gate, Connection c1, Connection c2, Connection c3, Connection o1,
                                      boolean[] expOutput) {

        c1.modifyAndPropagate(true);
        Assertions.assertEquals(expOutput[0], gate.isStatusHigh());
        Assertions.assertTrue(c1.isStatusHigh());
        Assertions.assertEquals(expOutput[0], o1.isStatusHigh());

        c2.modifyAndPropagate(true);
        Assertions.assertEquals(expOutput[1], gate.isStatusHigh());
        Assertions.assertTrue(c2.isStatusHigh());
        Assertions.assertEquals(expOutput[1], o1.isStatusHigh());

        c3.modifyAndPropagate(true);
        Assertions.assertEquals(expOutput[2], gate.isStatusHigh());
        Assertions.assertTrue(c3.isStatusHigh());
        Assertions.assertEquals(expOutput[2], o1.isStatusHigh());


    }


    static Stream<Arguments> connectionChangeBehaviorProvider() {
        GateWrapper and = new GateWrapper(Gate.AND);
        GateWrapper or = new GateWrapper(Gate.OR);
        GateWrapper xor = new GateWrapper(Gate.XOR);

        return Stream.of(
                Arguments.of(and.gate, and.i1,and.i2,and.i3,and.o1, new boolean[] {false, false, true}),
                Arguments.of(or.gate, or.i1, or.i2, or.i3, or.o1, new boolean[] {true, true, true}),
                Arguments.of(xor.gate, xor.i1, xor.i2, xor.i3, xor.o1, new boolean[] {true, false, true})
        );
    }

    @Test
    void testRecursiveConnectionBehaviorTest() {
        LogicGate and = new And(2);
        Connection inputAnd = new Connection();
        Connection inputAndOr = new Connection();

        LogicGate or = new Or(2);
        Connection inputOrOutputAnd = new Connection();

        Connection output = new Connection();

        and.connectToInput(inputAnd, inputAndOr);
        and.connectToOutput(inputOrOutputAnd);

        or.connectToInput(inputAndOr, inputOrOutputAnd);
        or.connectToOutput(output);

        Assertions.assertFalse(inputOrOutputAnd.isStatusHigh());
        Assertions.assertFalse(output.isStatusHigh());

        inputAndOr.modifyAndPropagate(true);
        Assertions.assertFalse(inputOrOutputAnd.isStatusHigh());
        Assertions.assertTrue(output.isStatusHigh());

        inputAndOr.modifyAndPropagate(false);
        inputAnd.modifyAndPropagate(true);
        Assertions.assertFalse(inputOrOutputAnd.isStatusHigh());
        Assertions.assertFalse(output.isStatusHigh());

        inputAndOr.modifyAndPropagate(true);
        Assertions.assertTrue(inputOrOutputAnd.isStatusHigh());
        Assertions.assertTrue(output.isStatusHigh());

    }

    private static class GateWrapper {

        private LogicGate gate;
        private final Connection i1;
        private final Connection i2;
        private final Connection i3;
        private final Connection o1;

        public GateWrapper(Gate gate) {
            i1 = new Connection();
            i2 = new Connection();
            i3 = new Connection();
            o1 = new Connection();
            switch (gate) {
                case AND -> this.gate = new And(3);
                case OR -> this.gate = new Or(3);
                case XOR -> this.gate = new Xor(3);
            }

            this.gate.connectToInput(i1, i2, i3);
            this.gate.connectToOutput(o1);

        }
    }

    private enum Gate{
        AND, OR, XOR;
    }

}