package atividade.token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String codigo = "";
        String caminho = JOptionPane.showInputDialog("Informe o caminho que está o seu código (Ex.: C:\\Users\\codigo.txt)");

        try {
            codigo = new String(Files.readAllBytes(Paths.get(caminho)));
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        System.out.println("Código Fonte:\n" + codigo);

        // Expressão regular para capturar tokens
        String regex = "\\b(int|char|if)\\b|\\w+|[=+\\-*/;(){}]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(codigo);

        List<String> tokens = new ArrayList<>();

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        // Criar tabela de símbolos
        Map<Integer, Tokenizer> tabelaSimbolos = new HashMap<>();

        for (int i = 0; i < tokens.size(); i++) {
            String lexema = tokens.get(i);
            String token = identificarToken(lexema);
            String valor = (token.equals("NUMERO")) ? lexema : ""; // Se for número, armazena o valor

            tabelaSimbolos.put(i, new Tokenizer(i, lexema, token, valor));
        }

        // Exibir a tabela de símbolos formatada
        System.out.printf("%-5s %-10s %-20s %-10s%n", "ID", "Lexema", "Token", "Valor");
        System.out.println("------------------------------------------------------");
        for (Map.Entry<Integer, Tokenizer> entry : tabelaSimbolos.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    // Método para identificar o tipo de token
    private static String identificarToken(String lexema) {
        if (lexema.matches("\\b(int|char|if)\\b")) return "PALAVRA_RESERVADA";
        if (lexema.matches("\\d+")) return "NUMERO";
        if (lexema.matches("[a-zA-Z_]\\w*")) return "IDENTIFICADOR";
        if (lexema.matches("[=+\\-*/;(){}]")) return "OPERADOR";
        return "DESCONHECIDO";
    }
}

// Classe Tokenizer para representar um token
class Tokenizer {
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

    @Override
    public String toString() {
        return String.format("%-5d %-10s %-20s %-10s", id, lexema, token, valor);
    }
}
