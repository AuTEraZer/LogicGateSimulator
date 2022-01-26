/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.gate;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class Not extends LogicGate {

    private static final int MAX_NOT_INPUTS = 1;

    public Not(boolean status) {
        super(MAX_NOT_INPUTS, status);
    }

    @Override
    public void update(boolean updateValue) {
        updateValue = !inputs[0].isStatusHigh();
        this.status = updateValue;
        this.output.update();
    }

}
