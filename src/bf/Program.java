package bf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collector;

/**
 * Created by cotix on 14-6-16.
 */
public class Program {
    private String code;
    private HashMap<String, String> functions;
    private String functionScope;
    private Stack<State> stateStack;
    private Scanner input;
    public Program() {
        functionScope = "*";
        functions = new HashMap<>();
        State rootState = new State(0, new TuringMachine());
        stateStack = new Stack<>();
        stateStack.push(rootState);
        input = new Scanner(System.in);
    }

    public void parseFunctions(String c) throws SyntaxException {
        String[] def = c.split(":");
        if (def.length % 3 != 0) {
            throw new SyntaxException("Mismatching :s");
        }
        for (int i = 0; i != def.length; i += 3) {
            functions.put(def[i+1],def[i+2]);
        }
    }

    public String toString() {
        return stateStack.peek().getMachine().toString();
    }

    public void execute(String code) throws TypeException {
        int ptr = 0;
        step: while (ptr < code.length()) {
            State state = stateStack.peek();
            char op = code.charAt(ptr++);
            if (op == '|') {
                state.getMachine().setValue((byte) code.charAt(ptr++));
                continue step;
            }
            String type = functionScope; // + (state.getMachine().getType() == null ? "": state.getMachine().getType()) + op;

            for (int i = type.length() - 1; i >= 0; i--) {
                char s = type.charAt(i);
                if (functions.containsKey(Character.toString(s)+op)) {
                    functionScope += op;
                    execute(functions.get(Character.toString(s)+op));
                    functionScope = functionScope.substring(0,functionScope.length()-1);
                    continue step;
                }
            }
            String t = Character.toString(state.getMachine().getType());
            if (state.getMachine().getType() != 0) {
                if (functions.containsKey(t+op)) {
                    functionScope += op;
                    execute(functions.get(t+op));
                    functionScope = functionScope.substring(0,functionScope.length()-1);
                    continue step;
                }
            }
            if (functions.containsKey(Character.toString(op))) {
                functionScope += op;
                execute(functions.get(Character.toString(op)));
                functionScope = functionScope.substring(0,functionScope.length()-1);
                continue step;
            }

            switch (op) {
                case '>':
                    state.right();
                    break;
                case '<':
                    state.left();
                    break;
                case '+':
                    state.inc();
                    break;
                case '-':
                    state.dec();
                    break;
                case '*':
                    if (state.enter() == null) {
                        state.create(functionScope.charAt(functionScope.length()-1));
                    }
                    stateStack.push(new State(0, state.enter()));
                    break;
                case '&':
                    stateStack.pop().resetPtr();
                    break;
                case '^':
                    state.delete();
                    break;

                case '[':
                    if (state.getValue() == 0) {
                        int c = 1;
                        int i;
                        for (i = ptr; i != code.length(); ++i) {
                            if (code.charAt(i) == ']') {
                                if (--c == 0) {
                                    break;
                                }
                            } else if (code.charAt(i) == '[') {
                                c++;
                            }
                        }
                        ptr = i+1;
                    }
                    break;

                case ']':
                    int c = 1;
                    int i;
                    for (i = ptr-2; i != -1; --i) {
                        if (code.charAt(i) == '[') {
                            if (--c == 0) {
                                break;
                            }
                        } else if (code.charAt(i) == ']') {
                            c++;
                        }
                    }
                    ptr = i;
                    break;

                case '.':
                    System.out.print((char)state.getValue());
                    break;

                case ',':
                    byte b = 0;
                    try {
                        b = (byte)System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    state.getMachine().setValue(b);
                    break;

                case 'b':
                    state.getMachine().setValue((byte)0);
            }

        }
    }
}