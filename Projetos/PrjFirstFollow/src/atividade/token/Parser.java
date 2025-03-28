package atividade.token;

import java.util.*;

public class Parser {
    private final List<Tokenizer> tokens;
    private int index = 0;

    public Parser(List<Tokenizer> tokens) {
        this.tokens = tokens;
    }

    public boolean parse() {
        return S() && index == tokens.size();
    }

    private boolean S() {
        if (Declaração() || Atribuição() || Comando_if()) {
            return S();
        }
        return true; // ε (vazio)
    }

    private boolean Declaração() {
        return match("KW_INT") && match("ID") && match("SYM_EQUAL") && Expressão() && match("SYM_PV");
    }

    private boolean Atribuição() {
        return match("ID") && match("SYM_EQUAL") && Expressão() && match("SYM_PV");
    }

    private boolean Comando_if() {
        if (match("KW_IF")) {
            if (!Expressão()) return false;
            return Comando_if_corpo();
        }
        return false;
    }

    private boolean Comando_if_corpo() {
        return Comando() || (match("SYM_CH_E") && S() && match("SYM_CH_D"));
    }

    private boolean Comando() {
        return Declaração() || Atribuição() || Comando_if();
    }

    private boolean Expressão() {
        if (!Termo()) return false;
        while (match("SYM_MAIS") || match("SYM_MENOS")) {
            if (!Termo()) return false;
        }
        return true;
    }

    private boolean Termo() {
        if (!Fator()) return false;
        while (match("SYM_MULTI") || match("SYM_DIV")) {
            if (!Fator()) return false;
        }
        return true;
    }

    private boolean Fator() {
        return match("ID") || match("NUM") || (match("SYM_PAR_D") && Expressão() && match("SYM_PAR_E"));
    }

    private boolean match(String expectedToken) {
        if (index < tokens.size() && tokens.get(index).getToken().equals(expectedToken)) {
            index++;
            return true;
        }
        return false;
    }
}
