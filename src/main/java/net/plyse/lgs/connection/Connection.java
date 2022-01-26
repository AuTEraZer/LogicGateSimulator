/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.connection;

import java.util.List;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class Connection implements Modifiable, Readable{

    private List<Input> inputs;
    private boolean status;

    public Connection() {
        this(false);
    }

    public Connection(boolean status) {
        this.status = status;
    }

    @Override
    public boolean isStatusHigh() {
        return status;
    }

    @Override
    public void modify(boolean status) {
        this.status = status;
    }

}