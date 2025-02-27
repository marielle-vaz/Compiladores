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

        /* Eduardo Lima Pinheiro
         * Marielle Rodrigues Vaz 
        */

        String codigo = "";
        String caminho = JOptionPane.showInputDialog("Informe o caminho que está o seu código (Ex.: C:\\Users\\codigo.txt)");

        try {
            codigo = new String(Files.readAllBytes(Paths.get(caminho)));
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        System.out.println("Código Fonte:\n" + codigo);

        String regex = "\\b(int|char|if)\\b|\\w+|[=+\\-*<>/;(){}]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(codigo);

        List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        List<Tokenizer> tabelaSimbolos = new ArrayList<>();
        Map<String, Integer> idMap = new HashMap<>();
        int idCounter = 1;

        for (String lexema : tokens) {
            int id;
            if (idMap.containsKey(lexema)) {
                id = idMap.get(lexema);
            } else {
                id = idCounter++;
                idMap.put(lexema, id);
            }

            String token = classificarToken(lexema);
            String valor = (token.equals("NUM")) ? lexema : "-";
            tabelaSimbolos.add(new Tokenizer(id, lexema, token, valor));
        }

        System.out.printf("%-5s %-10s %-20s %-10s%n", "ID", "Lexema", "Token", "Valor");
        System.out.println("------------------------------------------------------");
        for (Tokenizer entry : tabelaSimbolos) {
            System.out.println(entry);
        }

        System.out.println("\nCódigo Tokenizado:");
        for (Tokenizer entry : tabelaSimbolos) {
            System.out.print("<" + entry.getToken() + ", " + entry.getId() + "> ");
        }
        System.out.println();
    }

    private static String classificarToken(String lexema) {
        if (lexema.matches("\\b(int|char|if)\\b")) return "KW_" + lexema.toUpperCase();
        if (lexema.matches("[a-zA-Z_]\\w*")) return "ID";
        if (lexema.matches("\\d+")) return "NUM";
        if (lexema.matches("[=+\\-*<>/;(){}]")) return IdentificarOperadores(lexema);
        return "UNK";
    }

    private static String IdentificarOperadores(String operador) {
        switch (operador) {
            case "=": return "SYM_EQUAL";
            case ";": return "SYM_PV";
            case "(": return "SYM_PAR_D";
            case ")": return "SYM_PAR_E";
            case ">": return "SYM_MAIOR";
            case "<": return "SYM_MENOR";
            case "+": return "SYM_MAIS";
            case "-": return "SYM_MENOS";
            case "*": return "SYM_MULTI";
            case "/": return "SYM_DIV";
            case "{": return "SYM_CH_E";
            case "}": return "SYM_CH_D";
            default: return "";
        }
    }
}
