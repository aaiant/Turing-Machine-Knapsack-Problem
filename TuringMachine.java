import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TuringMachine {

    private Map<String, Transition> transitions;
    private String currentState;
    private StringBuilder tape;
    private int headPosition;

    public TuringMachine(String initialState, String tape) {
        this.transitions = new HashMap<>();
        this.currentState = initialState;
        this.tape = new StringBuilder(tape);
        this.headPosition = 0;
    }

    public void addTransition(String state, char readSymbol, String nextState,
            char writeSymbol, int direction) {
        Transition transition = new Transition(nextState, writeSymbol, direction);
        transitions.put(state + readSymbol, transition);
    }


    public void run() {
        while (transitions.containsKey(currentState + tape.charAt(headPosition))) {
            Transition transition = transitions.get(currentState + tape.charAt(headPosition));
            System.out.print(" initial state: " + currentState + " -- ");
            currentState = transition.nextState;
            tape.setCharAt(headPosition, transition.writeSymbol);
            headPosition += transition.direction;
            if (headPosition < 0) {
                tape.insert(0, "_");
                headPosition = 0;
            } else if (headPosition == tape.length()) {
                tape.append("_");
            }
            System.out.println("current band: " + tape + " -- current state: " + currentState);
            System.out.println();
        }
    }

    public TuringMachine readFile(String filename){
        TuringMachine tm = new TuringMachine("q0",this.tape.toString());
        StringBuffer path = new StringBuffer(filename);
		File file = new File(path.toString());
		try {
				BufferedReader br = new BufferedReader(new FileReader(file));
                String[] s;
                String line;
                while((line = br.readLine()) != null){
                    s = line.split(" ");
                    int direction = -1;
                    if(s[4].equals("R"))
                      direction = 1;
                    tm.addTransition(s[0], s[1].charAt(0), s[2], s[3].charAt(0), direction);
                }
                br.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
        return tm;
    }
    
    public String getTape() {
        return tape.toString();
    }
    public String getCurrentState(){
        return currentState;
    }

    private static class Transition {

        private String nextState;
        private char writeSymbol;
        private int direction;

        public Transition(String nextState, char writeSymbol, int direction) {
            this.nextState = nextState;
            this.writeSymbol = writeSymbol;
            this.direction = direction;
        }
    }
}
