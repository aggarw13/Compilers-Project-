grammar Micro;

//Micro language lexer rules
keyword : KEYWORD;
fragment DIGIT : ('0'..'9');
fragment LETTER : ('a'..'z' | 'A'..'Z');

KEYWORD :
	'PROGRAM' 
	| 'BEGIN' 
	| 'END' 
	| 'INT' 
	| 'FLOAT'
	| 'STRING' 
	| 'FOR'
	| 'ROF'
	| 'WRITE'
	| 'READ'
	| 'FUNCTION'
	| 'VOID'
	| 'IF'
	| 'ELSE'
	| 'FI'
	| 'CONTINUE'
	| 'BREAK'
	| 'RETURN';

IDENTIFIER :
	LETTER(LETTER|DIGIT)*;

INTLITERAL :
	DIGIT+;

STRINGLITERAL :
	'"'~('"')*'"';

FLOATLITERAL :
	DIGIT*'.'DIGIT+ ;

COMMENT :
	'--'.*?('\n' | '\r\n') -> skip;

WHITESPACES :
	('\t' | '\n' | '\r' | ' ' )+ -> skip;
	
OPERATOR :
	'+' | '-' | '*' | '/' 
	| '<' | '>' | '=' | '!=' | '<=' | '>='
	| ':=' | ';' | '(' | ')' | ',';
