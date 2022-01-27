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

        c1.modify(true);
        Assertions.assertEquals(expOutput[0], gate.isStatusHigh());
        Assertions.assertTrue(c1.isStatusHigh());
        Assertions.assertEquals(expOutput[0], o1.isStatusHigh());

        c2.modify(true);
        Assertions.assertEquals(expOutput[1], gate.isStatusHigh());
        Assertions.assertTrue(c2.isStatusHigh());
        Assertions.assertEquals(expOutput[1], o1.isStatusHigh());

        c3.modify(true);
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
        Connection inputAnd = new SingleLineConnection();
        Connection inputAndOr = new SingleLineConnection();

        LogicGate or = new Or(2);
        Connection inputOrOutputAnd = new SingleLineConnection();

        Connection output = new SingleLineConnection();

        and.connectToInput(inputAnd, inputAndOr);
        and.connectToOutput(inputOrOutputAnd);

        or.connectToInput(inputAndOr, inputOrOutputAnd);
        or.connectToOutput(output);

        Assertions.assertFalse(inputOrOutputAnd.isStatusHigh());
        Assertions.assertFalse(output.isStatusHigh());

        inputAndOr.modify(true);
        Assertions.assertFalse(inputOrOutputAnd.isStatusHigh());
        Assertions.assertTrue(output.isStatusHigh());

        inputAndOr.modify(false);
        inputAnd.modify(true);
        Assertions.assertFalse(inputOrOutputAnd.isStatusHigh());
        Assertions.assertFalse(output.isStatusHigh());

        inputAndOr.modify(true);
        Assertions.assertTrue(inputOrOutputAnd.isStatusHigh());
        Assertions.assertTrue(output.isStatusHigh());

    }


    @Test
    void testFullAdderTest() {

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


        Assertions.assertFalse(sum.isStatusHigh());
        Assertions.assertFalse(carry.isStatusHigh());

        c.modify(true);
        Assertions.assertTrue(sum.isStatusHigh());
        Assertions.assertFalse(carry.isStatusHigh());

        a.modify(true);
        b.modify(true);
        Assertions.assertTrue(sum.isStatusHigh());
        Assertions.assertTrue(carry.isStatusHigh());
        Assertions.assertFalse(xorOuterOutputXorInnerInput.isStatusHigh());
        Assertions.assertFalse(andUpperOut.isStatusHigh());
        Assertions.assertTrue(andLowerOut.isStatusHigh());

        c.modify(false);
        Assertions.assertFalse(sum.isStatusHigh());
        Assertions.assertTrue(carry.isStatusHigh());

    }

    private static class GateWrapper {

        private LogicGate gate;
        private final Connection i1;
        private final Connection i2;
        private final Connection i3;
        private final Connection o1;

        public GateWrapper(Gate gate) {
            i1 = new SingleLineConnection();
            i2 = new SingleLineConnection();
            i3 = new SingleLineConnection();
            o1 = new SingleLineConnection();
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
        AND, OR, XOR
    }

}