package atividade.token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Main {

    /* Eduardo Lima Pinheiro
     * Marielle Rodrigues Vaz */

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

        List<Tokenizer> tokens = tokenize(codigo);
        printTokens(tokens);
        
        Parser parser = new Parser(tokens);
        if (parser.parse()) {
            System.out.println("Parsing completed successfully.");
        } else {
            System.out.println("Parsing failed.");
        }
    }

    private static List<Tokenizer> tokenize(String codigo) {
        String regex = "\"|'|\\b(int|char|if)\\b|\\w+|[=+\\-*<>/!;(){}]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(codigo);

        List<Tokenizer> tokens = new ArrayList<>();
        Map<String, Integer> idMap = new HashMap<>();
        int idCounter = 1;

        while (matcher.find()) {
            String lexema = matcher.group();
            int id;
            if (idMap.containsKey(lexema)) {
                id = idMap.get(lexema);
            } else {
                id = idCounter;
                idMap.put(lexema, idCounter);
                idCounter++;
            }
            String token = classificarToken(lexema);
            String valor = (token.equals("NUM") || token.equals("STR") || token.equals("CHAR")) ? lexema : "-";
            tokens.add(new Tokenizer(id, lexema, token, valor));
        }
        return tokens;
    }

    private static void printTokens(List<Tokenizer> tokens) {
        System.out.printf("%-5s %-10s %-20s %-10s%n", "ID", "Lexema", "Token", "Valor");
        System.out.println("------------------------------------------------------");
        for (Tokenizer token : tokens) {
            System.out.println(token);
        }
    }

    private static String classificarToken(String lexema) {
        if (lexema.equals("\"")) return "SYM_ASPAS_DUPLAS";
        if (lexema.equals("'")) return "SYM_ASPAS_SIMPLES";
        if (lexema.matches("\"[^\"]*\"")) return "STR";
        if (lexema.matches("'[^']'")) return "CHAR";
        if (lexema.matches("\\b(int|char|if)\\b")) return "KW_" + lexema.toUpperCase();
        if (lexema.matches("[a-zA-Z_]\\w*")) return "ID";
        if (lexema.matches("\\d+")) return "NUM";
        if (lexema.matches("[=+\\-*<>/;!(){}]")) return IdentificarOperadores(lexema);
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
            case "!": return "SYM_EX";
            default: return "";
        }
    }
}