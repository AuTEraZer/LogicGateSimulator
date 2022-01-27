/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.gate;

import net.plyse.lgs.connection.Readable;

/**
 * @author Raphael Dichler on 26.01.2022.
 */
public interface LogicGateObserver extends Readable {

    void update(boolean updateValue);

}
