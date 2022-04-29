package proj.factory.command_interface;

import proj.Exception.*;
import proj.values_stack.Stack;


public interface Command {
    public void exec(String[] args, Stack stack) throws CommandException;
}
