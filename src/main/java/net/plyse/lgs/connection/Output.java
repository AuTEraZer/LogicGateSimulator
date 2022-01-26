/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.connection;

import net.plyse.lgs.gate.LogicGateObserver;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class Output extends Link implements Modifier {

    public Output(LogicGateObserver logicGate) {
        super(logicGate);
    }

    public Output(LogicGateObserver logicGate, boolean status) {
        super(logicGate, status);
    }

    @Override
    public void modifier() {
        if (connection != null) {
            this.connection.modify(isStatusHigh());
        }
    }

    @Override
    public void update() {
        modifier();
    }

}
