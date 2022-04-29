package proj.values_stack;

import org.apache.log4j.Logger;
import proj.Exception.StackIsEmptyException;
import proj.Exception.UndefinedVariable;

import java.util.*;

public class Stack {
    private static final Logger logger = Logger.getLogger(Stack.class);

    public Stack(){
        values = new ArrayList<>();
        definedVariables = new HashMap<>();
    }

    private Map<String, Double> definedVariables;
    private List<Double> values;

    public void addVar(String key, Double value){
        definedVariables.put(key, value);
    }
    public Double getVar(String key) throws UndefinedVariable {
        if(definedVariables.containsKey(key)) {
            return definedVariables.get(key);
        }
        else{
            logger.error("Variable: " + key + " was not defined");
            throw new UndefinedVariable(key);
        }
    }
    public void push(Double val){
        values.add(0, val);
    }
    public Double pop() throws StackIsEmptyException {
        if(values.size() == 0){
            logger.error("Attempt to pop from empty stack.");
            throw new StackIsEmptyException("Stack is empty.");
        }
        Double val = values.get(0);
        values.remove(0);
        return val;
    }
    public Double pick() throws StackIsEmptyException {
        if(values.size() == 0){
            throw new StackIsEmptyException("Stack is empty.");
        }
        return values.get(0);
    }
    public final List<Double> returnStack(){
        return Collections.unmodifiableList(values);
    }
    public Integer size(){
        return values.size();
    }
}
