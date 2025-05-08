if exist "Yylex.java" ( del Yylex.java )
if exist "Yylex.class" ( del Yylex.class )
if exist "output.txt" ( del output.txt )
if not defined JFLEX_HOME set JFLEX_HOME=C:\Desenvolvimento\jflex-1.9.1
if not defined JFLEX_VERSION set JFLEX_VERSION=1.9.1
java -Xmx128m -jar "%JFLEX_HOME%\lib\jflex-full-%JFLEX_VERSION%.jar" lexical_analyzer.flex
javac Yylex.java
java Yylex input.txt > output.txt 