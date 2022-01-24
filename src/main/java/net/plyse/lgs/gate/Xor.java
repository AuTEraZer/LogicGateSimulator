package net.plyse.lgs.gate;

/**
 * @author Raphael Dichler on 25.01.2022.
 * @project LogicGateSimulator
 */
public class Xor extends LogicGate{

    public Xor(boolean[] inputs, boolean outputStatus) {
        super(inputs, outputStatus);
    }

    @Override
    protected void operate() {
        boolean result = false;
        for (int i = 0; i < this.inputs.length; i++) {
            if (inputs[i]) {
                result = !result;
            }
        }
        this.outputStatus = result;
    }
}
