package net.plyse.lgs.connection;

/**
 * @author Raphael Dichler on 25.01.2022.
 * @project LogicGateSimulator
 */
public class Output extends Link implements Modifier {

    @Override
    public void modifier(Modifiable modifiable) {
        modifiable.modify(isStatusHigh());
    }

}
