/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.curcuit;

/**
 * @author Raphael Dichler on 01.02.2022.
 */
public class TruthTable {

    private boolean[][] truthTable;

    public TruthTable(int inputs, int outputs) {
        if (inputs <= 0 || outputs <= 0)
            throw new IllegalArgumentException("Cannot create a truth table with " + inputs + " inputs and " + outputs + " outputs.");
        int length = 1 << inputs;
        this.truthTable = new boolean[length][inputs + outputs];
        setup(inputs, length);
    }

    private void setup(int in, int length) {
        int alternating = 1;
        for (int i = in - 1; i >= 0; i--) {
            boolean value = false;
            for (int j = 1; j <= length; j++) {
                this.truthTable[j - 1][i] = value;
                if (j  % alternating == 0)
                    value = !value;
            }
            alternating <<= 1;
        }
    }

    public boolean[] getInputsOf(int row) {
        return this.truthTable[row];
    }

    public boolean[][] getTruthTable() {
        return truthTable;
    }

}
