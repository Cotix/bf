package bf;

import java.util.Arrays;

/**
 * Created by cotix on 14-6-16.
 */
public class TuringMachine {
    private TuringMachine[] mem;
    private byte[] value;
    private char[] type;
    private int ptr = 0;
    private static final int MEM_SIZE = 6;
    public TuringMachine() {
        mem = new TuringMachine[MEM_SIZE];
        value = new byte[MEM_SIZE];
        type = new char[MEM_SIZE];
        for (int i = 0; i != MEM_SIZE; ++i) {
            type[i] = 'b';
        }
    }

    public void setValue(byte v) {
        value[ptr] = v;
        type[ptr] = 'b';
    }

    public void resetPtr() {
        ptr = 0;
    }

    public char getType() {
        return type[ptr];
    }

    public int getPtr() {
        return ptr;
    }

    public void left() {
        if (ptr > 0)
            ptr--;
    }

    public void right() {
        if (ptr < MEM_SIZE)
            ptr++;
    }

    public void inc() throws TypeException {
        if (type[ptr] != 'b') {
            throw new TypeException("Incorrect type for increment");
        }
        value[ptr]++;
    }

    public void dec() throws TypeException {
        if (type[ptr] != 'b') {
            throw new TypeException("Incorrect type for decrement");
        }
        value[ptr]--;
    }

    public TuringMachine getMachine() {
        return mem[ptr];
    }

    public TuringMachine createMachine(char t) {
        mem[ptr] = new TuringMachine();
        type[ptr] = t;
        return mem[ptr];
    }

    public void delete() {
        mem[ptr] = null;
        type[ptr] = 0;
    }

    public byte getValue() throws TypeException {
        if (type[ptr] != 'b') {
            throw new TypeException("Incorrect type for getValue");
        }
        return value[ptr];
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (int i = 0; i != MEM_SIZE-1; ++i) {
            if (type[i] == 'b' || type[i] == 0) {
                output.append(value[i]+", ");
            } else {
                output.append(mem[i].toString() + ", ");
            }
        }
        int i = MEM_SIZE-1;
        if (type[i] != 'b' || type[i] != 0) {
            output.append(value[i]+"]");
        } else {
            output.append(mem[i].toString() + "]");
        }
        return output.toString();

    }

}

