package Memento;

import java.util.Stack;

public class Memento {
    Stack<String> state;

    public Memento(){
        state = new Stack<String>();
    }

    public void StoreState(String s){
        state.add(s);
    }
    public String getState(){
        return state.pop();
    }
}
