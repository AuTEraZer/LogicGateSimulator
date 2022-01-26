/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.connection;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class Input extends Link implements LinkObserver {

    @Override
    public void update() {
        this.status = this.connection.isStatusHigh();
    }

}
