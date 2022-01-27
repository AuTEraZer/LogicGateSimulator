/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.gate;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class Xor extends LogicGate{

    private static final int MIN_INPUTS = 2;

    /**
     * @param inputs inputs of the <code>LogicGate</code>. If <code>inputs</code> is less than 2 the Inputs will be
     *               set to 2.
     * @param status internal status
     * @throws IllegalArgumentException If <code>inputs</code> is less than 0
     */
    public Xor(int inputs, boolean status) {
        super(Math.max(MIN_INPUTS, inputs), status);
    }

    /**
     * Creates a <code>LogicGate</code> with internal status false.
     * @param inputs inputs of the LogicGate
     * @throws IllegalArgumentException If <code>inputs</code> is less than 0
     */
    public Xor(int inputs) {
        super(inputs);
    }

    @Override
    public void update(boolean updateValue) {
        updateValue = this.inputs[0].isStatusHigh();

        for (int i = 1; i < this.inputs.length; i++) {
            updateValue ^= this.inputs[i].isStatusHigh();
        }

        this.status = updateValue;
        output.update();
    }
}
