package atividade.token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

public class Tokenizador {
    
    public static void main(String[] args) {

        String codigo = "";
        String caminho = JOptionPane.showInputDialog("Informe o caminho que está o seu código (Ex.: C:\\Users\\codigo.txt)");

        try {
            codigo = new String(Files.readAllBytes(Paths.get(caminho)));
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        
        System.out.println(codigo);
    }
    
}
