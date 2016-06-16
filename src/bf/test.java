package bf;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by cotix on 14-6-16.
 */
public class Test {
    public static void main(String[] args) throws IOException, TypeException, SyntaxException {
        Program prog = new Program();
        prog.parseFunctions(
        ":C:^*b&:"+
        ":S:^*b>b+++[[>]C[<]>-]C<C&:"+
        ":Sh:**|h&>*|e&>*|l&>*|l&>*|o&&:"+
        ":So:**[.&>*]&&:");
        prog.execute("Sho");
        System.out.println(prog.toString());
    }
}
