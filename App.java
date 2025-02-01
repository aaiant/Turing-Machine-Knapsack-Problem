
public class App {
    
    public static void main(String[] args) {
        TuringMachine tm = new TuringMachine("q0","_aabbcc_").readFile("src\\data.txt");
        tm.run();
    }
    
    
}
