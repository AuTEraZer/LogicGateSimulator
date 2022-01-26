/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.gate;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class And extends LogicGate  {

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

    }

}
