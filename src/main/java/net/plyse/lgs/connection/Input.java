/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.connection;

import net.plyse.lgs.gate.LogicGateObserver;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class Input extends Link {

    private LogicGateObserver logicGate;

    @Override
    public void update() {
        this.status = this.connection.isStatusHigh();
        logicGate.update(status);
    }

}
