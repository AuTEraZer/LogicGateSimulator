/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.connection;

import net.plyse.lgs.gate.LogicGateObserver;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class Input extends Link {

    public Input(LogicGateObserver logicGate) {
        super(logicGate);
    }

    public Input(LogicGateObserver logicGate, boolean status) {
        super(logicGate, status);
    }

    @Override
    public void update() {
        this.status = this.connection.isStatusHigh();
        logicGate.update(status);
    }

    @Override
    public void connect(Connection connection) {
        if (connection == null) throw new NullPointerException("The connection cannot be null.");
        this.connection = connection;
        this.connection.addInputLink(this);
    }
}
