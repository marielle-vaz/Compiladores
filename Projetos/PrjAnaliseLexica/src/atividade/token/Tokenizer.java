package atividade.token;

public class Tokenizer{

    private int id;
    private String lexema;
    private String token;
    private String valor;

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

    @Override
    public String toString() {
        return String.format("%-5d %-10s %-20s %-10s", id, lexema, token, valor);
    }
}

// public class Tokenizer {
//     private final String lexema;
//     private final String token;

//     public Tokenizer(String lexema, String token) {
//         this.lexema = lexema;
//         this.token = token;
//     }

//     public String getLexema() {
//         return lexema;
//     }

//     public String getToken() {
//         return token;
//     }
// }