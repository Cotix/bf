package bf;

/**
 * Created by cotix on 14-6-16.
 */
public class State {
    private TuringMachine machine;

    public State(int p, TuringMachine m) {
        machine = m;
    }

    public void resetPtr() {
        machine.resetPtr();
    }

    public TuringMachine getMachine() {
        return machine;
    }

    public void left() {
        machine.left();
    }
    public void right() {
        machine.right();
    }
    public void inc() throws TypeException {
        machine.inc();
    }
    public void dec() throws TypeException {
        machine.dec();
    }
    public TuringMachine enter() {
        return machine.getMachine();
    }

    public TuringMachine create(char type) {
        return machine.createMachine(type);
    }

    public void delete() {
        machine.delete();
    }

    public byte getValue() throws TypeException {
        return machine.getValue();
    }

}
