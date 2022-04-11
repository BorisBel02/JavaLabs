import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import proj.factory.Factory;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

public class TestCommands {
    Stack stck = Stack.getStackInstance();
    @Test
    public void testComms() throws Exception{
        Command push = Factory.getFactory().getCommand("PUSH");
        String[] argsPush = {"PUSH", "5"};
        push.exec(argsPush);
        Assertions.assertEquals(5.0, stck.pseudo_pop());
        argsPush[1] = "25";
        push.exec(argsPush);


        String[] args = {"MUL", "2"};
        Command comm = Factory.getFactory().getCommand("MUL");
        comm.exec(args);
        Assertions.assertEquals(125.0, stck.pseudo_pop());


        argsPush[1] = "-300";
        push.exec(argsPush);
        push.exec(argsPush);

        comm = Factory.getFactory().getCommand("ABS");
        comm.exec(args);
        Assertions.assertEquals(300, stck.pseudo_pop());

        comm = Factory.getFactory().getCommand("SUB");
        comm.exec(args);
        Assertions.assertEquals(0.0, stck.pseudo_pop());
    }

    @Test
    public void testDefine() throws Exception{
        Command comm = Factory.getFactory().getCommand("DEFINE");
        String[] args = {"DEFINE", "four", "4"};
        comm.exec(args);

        String[] printArgs = {"PUSH", "four"};
        Command print = Factory.getFactory().getCommand("PUSH");
        print.exec(printArgs);

        Assertions.assertEquals(4, stck.pseudo_pop());
    }
}
