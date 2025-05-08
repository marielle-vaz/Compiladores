import java.util.HashMap;
import java.util.Map;

public class Tokenizacao {

    public HashMap<String, Token> tableSymbol;

    public Tokenizacao() {
        this.tableSymbol = new HashMap<String, Token>();
    }

    public void add(String token, String lexema) {
        Token t = new Token(token, lexema);
        this.tableSymbol.put(t.getId(), t);
    }

    public void print() {
        System.out.printf("%-15s | %-10s | %s%n", "ID", "TOKEN", "LEXEMA");
        System.out.println("-----------------------------------------------------");
        for(Map.Entry<String, Token> entry:this.tableSymbol.entrySet())
            System.out.printf("%-15s | %-10s | %s%n",
                entry.getValue().getId(),
                entry.getValue().getToken(),
                entry.getValue().getLexema());
    }
} 