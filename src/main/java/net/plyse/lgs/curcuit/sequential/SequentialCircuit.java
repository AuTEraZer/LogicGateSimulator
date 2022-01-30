/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.curcuit.sequential;

import net.plyse.lgs.connection.Input;
import net.plyse.lgs.connection.Output;
import net.plyse.lgs.gate.LogicGateObserver;

import java.util.List;
import java.util.Set;


/**
 * @author Raphael Dichler on 27.01.2022.
 */
public class SequentialCircuit implements LogicGateObserver {

    private Input[] inputs;
    private Output[] outputs;

    @Override
    public boolean isStatusHigh() {
        return false;
    }

    @Override
    public void update(boolean updateValue) {

    }

    public static void main(String[] args) {
        String instruction = "(A ^ B) ^ C -> sum\n" +
                "(A & B) | ((A ^ B) & C) -> carry";
        List<Integer> integers = List.of(1, 2);
        Set.of(2);
    }


    public SequentialCircuit createSequentialCircuit(String path) {



        return null;
    }


}
