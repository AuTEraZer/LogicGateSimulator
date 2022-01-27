/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.connection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class Connection implements Modifiable, Readable {

    private final List<LinkObserver> inputs;
    private boolean status;

    /**
     *  Create a Connection with status low.
     */
    public Connection() {
        this(false);
    }

    public Connection(boolean status) {
        this.status = status;
        this.inputs = new ArrayList<>();
    }

    @Override
    public boolean isStatusHigh() {
        return status;
    }

    @Override
    public void modify(boolean status) {
        this.status = status;
        notifyLink();
    }

    public void addInputLink(LinkObserver input) {
        this.inputs.add(input);
    }

    private void notifyLink() {
        inputs.forEach(LinkObserver::update);
    }

}
