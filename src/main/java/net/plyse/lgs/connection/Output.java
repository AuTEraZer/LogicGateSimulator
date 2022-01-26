/*
 * Copyright (c) 2022 Raphael Dichler.
 */
package net.plyse.lgs.connection;

/**
 * @author Raphael Dichler on 25.01.2022.
 */
public class Output extends Link implements Modifier {

    @Override
    public void modifier() {
        this.connection.modify(isStatusHigh());
    }

}
