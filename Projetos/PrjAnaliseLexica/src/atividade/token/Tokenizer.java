package atividade.token;

public class Tokenizer{

    private final int id;
    private final String lexema;
    private final String token;
    private final String valor;

    public Tokenizer(int id, String lexema, String token, String valor) {
        this.id = id;
        this.lexema = lexema;
        this.token = token;
        this.valor = valor;
    }
    public String getLexema() {
        return lexema;
    }

    public String getToken() {
        return token;
    }
    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-10s %-20s %-10s", id, lexema, token, valor);
    }
}