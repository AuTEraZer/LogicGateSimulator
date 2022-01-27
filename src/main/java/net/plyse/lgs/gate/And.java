/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.gate;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
@SuppressWarnings("ALL")
public class And extends LogicGate  {

    /**
     * @param inputs inputs of the <code>LogicGate</code>
     * @param status internal status
     * @throws IllegalArgumentException If <code>inputs</code> is less than 0
     */
    public And(int inputs, boolean status) {
        super(inputs, status);
    }

    /**
     * Creates a <code>LogicGate</code> with internal status false.
     * @param inputs inputs of the LogicGate
     * @throws IllegalArgumentException If <code>inputs</code> is less than 0
     */
    public And(int inputs) {
        super(inputs);
    }

    /**
     * Updates the <code>Output</code> opon the current <code>Inputs</code>.
     *
     * @param updateValue The status to which the last connection had changed
     */
    @Override
    public void update(boolean updateValue) {
        if (!updateValue) {
            this.status = false;
            output.update();
            return;
        }

        for (int i = 0; i < this.inputs.length; i++) {
            if (!inputs[i].isStatusHigh()) {
                this.status = false;
                output.update();
                return;
            }
        }

        this.status = true;
        output.update();
    }

}
