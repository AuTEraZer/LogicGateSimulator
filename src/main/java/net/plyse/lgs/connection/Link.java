/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.connection;

import net.plyse.lgs.gate.LogicGate;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public abstract class Link implements Readable, Invertible, LinkObserver {

    protected Connection connection;
    protected boolean status = false;
    private boolean invertStatus = false;

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

}
