package net.plyse.lgs.gate;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Raphael Dichler on 25.01.2022.
 * @project LogicGateSimulator
 */
public class XorTest {

    public XorTest() {
    }

    private Xor createXor(int binaryPattern) {
        String binaryString = Integer.toBinaryString(binaryPattern);
        int len = binaryString.length();
        boolean[] inputs = new boolean[len];
        for (int i = 0; i < len; i++) {
            inputs[i] = binaryString.charAt(i) == '1';
        }
        return null;
//        return new Xor(inputs, false);
    }

    @Test
    public void operate() {
        LogicGate xor2Inputs = createXor(0b10);
        xor2Inputs.operate();
//        assertTrue(xor2Inputs.outputStatus);

        LogicGate xor5InputsFalse = createXor(0b01010);
        xor5InputsFalse.operate();
//        assertFalse(xor5InputsFalse.outputStatus);

        LogicGate xor5InputsTrue = createXor(0b01000);
        xor5InputsTrue.operate();
//        assertTrue(xor5InputsTrue.outputStatus);
    }
}