/*
 * Copyright (c) 2022 Raphael Dichler.
 */

package net.plyse.lgs.curcuit;

import java.util.Arrays;

/**
 * @author Raphael Dichler on 01.02.2022.
 */
public class TruthTable {

    private boolean[][] truthTable;

    public TruthTable(int inputs, int outputs) {
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
                if (j  % alternating == 0) {
                    value = !value;
                }
            }
            alternating <<= 1;
        }
    }


    public static void main(String[] args) {



        System.out.println(Arrays.deepToString(new TruthTable(3, 1).truthTable));
    }

}
