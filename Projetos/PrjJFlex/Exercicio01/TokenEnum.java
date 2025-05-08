public enum TokenEnum {
    KW_ID ("KW_ID"),
    KW_NUMBER ("KW_NUMBER"),
    KW_OP ("KW_OP"),
    KW_ATT ("KW_ATT"),
    KW_END ("KW_END"),
    KW_PAR ("KW_PAR");
    private String descricao;

    TokenEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}