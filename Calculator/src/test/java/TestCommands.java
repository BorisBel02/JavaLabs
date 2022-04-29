import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import proj.factory.Factory;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

public class TestCommands {

    @Test
    public void testComms() throws Exception{
        Stack stack = new Stack();
        Command push = Factory.getFactory().getCommand("PUSH");
        String[] argsPush = {"PUSH", "5"};
        push.exec(argsPush, stack);
        Assertions.assertEquals(5.0, stack.pick());
        argsPush[1] = "25";
        push.exec(argsPush, stack);


        String[] args = {"MUL", "2"};
        Command comm = Factory.getFactory().getCommand("MUL");
        comm.exec(args, stack);
        Assertions.assertEquals(125.0, stack.pick());


        argsPush[1] = "-300";
        push.exec(argsPush, stack);
        push.exec(argsPush, stack);

        comm = Factory.getFactory().getCommand("ABS");
        comm.exec(args, stack);
        Assertions.assertEquals(300, stack.pick());

        comm = Factory.getFactory().getCommand("SUB");
        comm.exec(args, stack);
        Assertions.assertEquals(0.0, stack.pick());
    }

    @Test
    public void testDefine() throws Exception{
        Stack stack = new Stack();
        Command comm = Factory.getFactory().getCommand("DEFINE");
        String[] args = {"DEFINE", "four", "4"};
        comm.exec(args, stack);

        String[] printArgs = {"PUSH", "four"};
        Command print = Factory.getFactory().getCommand("PUSH");
        print.exec(printArgs, stack);

        Assertions.assertEquals(4, stack.pick());
    }
}
