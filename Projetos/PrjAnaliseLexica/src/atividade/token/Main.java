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
            String token = classificarToken(lexema);
            String valor = (token.equals("NUM")) ? lexema : "-"; 

            tabelaSimbolos.put(i + 1, new Tokenizer(i + 1, lexema, token, valor));
        }

        // Exibir a tabela de símbolos formatada
        System.out.printf("%-5s %-10s %-20s %-10s%n", "ID", "Lexema", "Token", "Valor");
        System.out.println("------------------------------------------------------");
        for (Map.Entry<Integer, Tokenizer> entry : tabelaSimbolos.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println("\nCódigo Tokenizado:");
        for (Map.Entry<Integer, Tokenizer> entry : tabelaSimbolos.entrySet()) {
            System.out.print("<" + entry.getValue().getToken() + ", " + entry.getKey() + "> ");
        }
        System.out.println();
    }
    
        
    private static String classificarToken(String lexema) {
        if (lexema.matches("\\b(int|char|if)\\b")) return "KW_" + lexema.toUpperCase(); // PALAVRAS RESERVADAS
        if (lexema.matches("[a-zA-Z_]\\w*")) return "ID"; // IDENTIFICADOR
        if (lexema.matches("\\d+")) return "NUM"; // NÚMEROS
        if (lexema.matches("[=+\\-*/;(){}]")) return IdentificarOperadores(lexema); // SÍMBOLOS
        return "UNK"; // DESCONHECIDO
    }

    private static String IdentificarOperadores(String operador){

        if(operador.equals("=")){
            return "SYM_EQUAL";
        }
        else if (operador.equals(";")) {
            return "SYM_PV";
        }
        else if (operador.equals("(")) {
            return "SYM_PAR_D";
        }
        else if (operador.equals(")")) {
            return "SYM_PAR_E";
        }
        else if (operador.equals(">")) {
            return "SYM_MAIOR";
        }
        else if (operador.equals("<")) {
            return "SYM_MENOR";
        }
        else if (operador.equals("+")) {
            return "SYM_MAIS";
        }
        else if (operador.equals("-")) {
            return "SYM_MENOS";
        }
        else if (operador.equals("*")) {
            return "SYM_MULTI";
        }
        else if (operador.equals("/")) {
            return "SYM_DIV";
        }
        else if (operador.equals("{")){
            return "SYM_CH_E";
        }
        else if (operador.equals("}")){
            return "SYM_CH_D";
        }
        return "";
    }
}