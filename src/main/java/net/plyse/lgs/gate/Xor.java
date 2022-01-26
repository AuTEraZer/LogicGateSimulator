/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.gate;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class Xor extends LogicGate{

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
