%%

%{
    
%}

%standalone

titulo = GRAPH:
space = [\n\r\ \t\b\012]

%%

{titulo}            {System.out.println("Tenho um grafo");}
{space}             {System.out.println("Tenho um espaço");}