package bf;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by cotix on 14-6-16.
 */
public class test {
    public static void main(String[] args) throws IOException, TypeException, SyntaxException {
        Program prog = new Program();
        prog.parseFunctions(
                ":i:^*b&:" +
                ":v:^*i>i>i>i&:" +
                ":i+:*+&:"+
                ":v+:*+>+>+>+&:");
        prog.execute("v+");
        System.out.println(prog.toString());
    }
}
