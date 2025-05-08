%%

%{
    Tokenizacao listToken = new Tokenizacao();
%}

%standalone

letra = [a-zA-Z_]
digito = [0-9]
id = {letra}({letra}|{digito})*
number = ({digito})+
operator = [-*+/]
equal = [=]
end = [;]
par = [()]
space = [\n\r\ \t\b\012]

%%

{id}                {listToken.add(TokenEnum.KW_ID.toString(), yytext());}
{number}            {listToken.add(TokenEnum.KW_NUMBER.toString(), yytext());}
{operator}          {listToken.add(TokenEnum.KW_OP.toString(), yytext());}
{equal}             {listToken.add(TokenEnum.KW_ATT.toString(), yytext());}
{end}               {listToken.add(TokenEnum.KW_END.toString(), yytext());}
{par}               {listToken.add(TokenEnum.KW_PAR.toString(), yytext());}
{space}             {}
<<EOF>>             {
                        listToken.print();
                        return 0;
                    } 