package atividade.token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        }
        
        System.out.println(codigo);

        //Expressão regular para capturar tokens
        String regex = "\\b(int|char|if)\\b|\\w+|[=+\\-*/;(){}]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(codigo);

        ArrayList<String> tokens = new ArrayList<String>();

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        // Exibir os tokens extraídos
        System.out.println(tokens);
    }

}
