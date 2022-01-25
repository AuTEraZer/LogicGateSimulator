package net.plyse.lgs.connection;

/**
 * @author Raphael Dichler on 25.01.2022.
 * @project LogicGateSimulator
 */
public abstract class Link implements Readable, Invertible{

    protected Connection connection;
    private boolean invertStatus;

    @Override
    public boolean isStatusHigh() {
        return invertStatus ^ this.connection.isStatusHigh();
    }

    @Override
    public void invert() {
        invertStatus = true;
    }

    @Override
    public void reverse() {
        invertStatus = false;
    }

}
