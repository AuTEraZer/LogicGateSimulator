/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.connection;

import net.plyse.lgs.gate.LogicGateObserver;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public abstract class Link implements Readable, Invertible, LinkObserver, Connectable {

    protected final LogicGateObserver logicGate;
    protected Connection connection = null;
    protected boolean status;
    private boolean invertStatus = false;

    public Link(LogicGateObserver logicGate) {
        this(logicGate, false);
    }

    public Link(LogicGateObserver logicGate, boolean status) {
        this.logicGate = logicGate;
        this.status = status;
    }

    @Override
    public boolean isStatusHigh() {
        return invertStatus ^ status;
    }

    @Override
    public void invert() {
        invertStatus = true;
    }

    @Override
    public void reverse() {
        invertStatus = false;
    }

    @Override
    /**
     * @throws NullPointerException If <code>connection</code> is null
     */
    public void connect(Connection connection) {
        if (connection == null) throw new NullPointerException("The connection cannot be null.");
        this.connection = connection;
        this.connection.addInputLink(this);
    }

}
