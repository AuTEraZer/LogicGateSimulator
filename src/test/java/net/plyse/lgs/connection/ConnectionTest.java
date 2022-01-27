/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.connection;

import junit.framework.TestCase;
import net.plyse.lgs.gate.And;
import net.plyse.lgs.gate.LogicGate;
import org.junit.jupiter.api.Test;

/**
 * @author Raphael Dichler on 27.01.2022.
 */
public class ConnectionTest extends TestCase {

    @Test
    void testConnectionChangeBehavior() {
        LogicGate andGate = new And(3);
        Connection connection = new Connection();
    }


}