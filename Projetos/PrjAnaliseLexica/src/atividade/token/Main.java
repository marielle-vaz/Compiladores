package atividade.token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        String codigo = """
                int a = 134;
                int b = 23;
                if(b > 30)
                    int c = b + a;
                if(a > 100) {
                    int c = a - b;
                    char d = a * a + c;
                }
                """;

        // Expressão regular para capturar tokens relevantes
        String regex = "\\b(int|char|if)\\b|\\w+|[=+\\-*/;(){}]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(codigo);

        List<String> tokens = new ArrayList<>();

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        // Exibir os tokens extraídos
        System.out.println(tokens);
    }

}
