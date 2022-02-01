/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.curcuit.sequential;

import net.plyse.lgs.connection.Connection;
import net.plyse.lgs.connection.Input;
import net.plyse.lgs.connection.Output;
import net.plyse.lgs.curcuit.TruthTable;
import net.plyse.lgs.gate.LogicGateObserver;

import java.util.List;
import java.util.Set;


/**
 * @author Raphael Dichler on 27.01.2022.
 */
public class SequentialCircuit implements LogicGateObserver {

    private Input[] inputs;
    private Connection[] inputConnections;
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

    public TruthTable getTruthTable() {
        return getTruthTable(this.inputConnections, this.outputs);
    }

    public static TruthTable getTruthTable(Connection[] inputs, Output[] outputs) {
        TruthTable truthTable = new TruthTable(inputs.length, outputs.length);

        for (int i = inputs.length - 1; i >= 0; i++) {
            boolean[] row = truthTable.getInputsOf(i);
            for (int j = 0; j < inputs.length; j++) {
                inputs[j].modify(row[j]);
            }
            for (int k = 0; k < outputs.length; k++) {
                row[k + inputs.length] = outputs[k].isStatusHigh();
            }
        }

        return truthTable;
    }



}
