package net.plyse.lgs.gate;

/**
 * @author Raphael Dichler on 25.01.2022.
 * @project LogicGateSimulator
 */
public class And extends LogicGate  {

    public And(boolean[] inputs, boolean outputStatus) {
        super(inputs, outputStatus);
    }

    @Override
    protected void operate() {
        for (int i = 0; i < this.inputs.length; i++) {
            if (!inputs[i]) {
                outputStatus = false;
                return;
            }
        }
        outputStatus = true;
    }

}
