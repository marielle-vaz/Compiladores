public class Token {

    private String id;
    private String token;
    private String lexema;

    public Token(String token, String lexema) {
        this.id = generateId();
        this.token = token;
        this.lexema = lexema;
    }

    public String getId() {
        return this.id;
    }
    
    public String getToken() {
        return this.token;
    }

    public String getLexema() {
        return this.lexema;
    }
   
    public static String generateId() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int ID_LENGTH = 10;
        StringBuilder id = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++)
            id.append(CHARACTERS.charAt((int)
            (Math.random() * CHARACTERS.length())));
        return id.toString();
    }
} 
   