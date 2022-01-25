package net.plyse.lgs.connection;

/**
 * @author Raphael Dichler on 25.01.2022.
 * @project LogicGateSimulator
 */
public class Connection implements Modifiable, Readable{

    private boolean status;

    public Connection() {
        this(false);
    }

    public Connection(boolean status) {
        this.status = status;
    }

    @Override
    public boolean isStatusHigh() {
        return status;
    }

    @Override
    public void modify(boolean status) {
        this.status = status;
    }

}
