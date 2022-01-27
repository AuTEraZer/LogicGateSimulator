/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.gate;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
@SuppressWarnings("ALL")
public class Or extends LogicGate{

    /**
     * @param inputs inputs of the <code>LogicGate</code>. If <code>inputs</code> is less than 2 the Inputs will be
     *               set to 2.
     * @param status internal status
     * @throws IllegalArgumentException If <code>inputs</code> is less than 0
     */
    public Or(int inputs, boolean status) {
        super(inputs, status);
    }

    /**
     * Creates a <code>LogicGate</code> with internal status false.
     * @param inputs inputs of the LogicGate
     * @throws IllegalArgumentException If <code>inputs</code> is less than 0
     */
    public Or(int inputs) {
        super(inputs);
    }

    @Override
    public void update(boolean updateValue) {
        if (updateValue) {
            this.status = true;
            output.update();
            return;
        }

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].isStatusHigh()) {
                this.status = true;
                output.update();
                return;
            }
        }

        this.status = false;
        output.update();
    }

}
