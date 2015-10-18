grammar Micro;

/* Program */
program           : 'PROGRAM' id 'BEGIN' pgm_body {SemanticDataHandler.popCurrentScope();} 'END';
id                : IDENTIFIER;
pgm_body          : {SemanticDataHandler.pushNewScope(SCOPE.GLOBAL, null);} decl /*{SemanticDataHandler.displayCurrentScope();}*/ func_declarations ;
decl		      : string_decl decl | var_decl decl |;
  
/* Global String Declaration */
string_decl       : 'STRING' id ':=' str ';'  {SemanticDataHandler.addScopeRecord($id.text, "STRING");  SemanticDataHandler.setRecordValue($id.text, $str.text);};
str               : STRINGLITERAL;

/* Variable Declaration */
var_decl          : var_type id_list ';' {SemanticDataHandler.addScopeRecord($id_list.text, $var_type.text);} ;
any_type          : var_type | 'VOID';
var_type	      : 'FLOAT'| 'INT';
id_list           : id id_tail;
id_tail           : ',' id id_tail | ;

/* Function Paramater List */
param_decl_list   : param_decl param_decl_tail | ;
param_decl        : var_type id {SemanticDataHandler.addScopeRecord($id.text, $var_type.text);} ;
param_decl_tail   : ',' param_decl param_decl_tail | ;

/* Function Declarations */
func_declarations : func_decl func_declarations | ;
func_decl         : 'FUNCTION' any_type id {SemanticDataHandler.pushNewScope(SCOPE.FUNCTION, $id.text);} '('param_decl_list')' 'BEGIN' func_body 'END' {SemanticDataHandler.popCurrentScope();} ;
func_body         : decl stmt_list ;

/* Statement List */
stmt_list         : stmt stmt_list | ;
stmt              : base_stmt | if_stmt | for_stmt;
base_stmt         : assign_stmt | read_stmt | write_stmt | return_stmt;

/* Basic Statements */
assign_stmt       : assign_expr ';';
assign_expr       : id ':=' {ASTStackHandler.pushAssignmentTree(SemanticDataHandler.currentScope, $id.text);} expr {ASTStackHandler.updateCurrTree();} ;
read_stmt         : 'READ' '(' id_list ')' {ASTStackHandler.pushIOTree(IO_TYPE.READ, $id_list.text, SemanticDataHandler.currentScope);} ';';
write_stmt        : 'WRITE' '(' id_list ')' {ASTStackHandler.pushIOTree(IO_TYPE.WRITE, $id_list.text, SemanticDataHandler.currentScope);} ';';
return_stmt       : 'RETURN' expr ';';

/* Expressions */		
expr              : expr_prefix factor;
expr_prefix       : expr_prefix factor addop {ASTStackHandler.createArithmeticTree($addop.text);} | ;
factor            : factor_prefix postfix_expr;
factor_prefix     : factor_prefix postfix_expr mulop {ASTStackHandler.createArithmeticTree($mulop.text);} | ;
postfix_expr      : primary | call_expr;
call_expr         : id '(' expr_list ')';
expr_list         : expr expr_list_tail | ;
expr_list_tail    : ',' expr expr_list_tail | ;
primary           : '(' expr ')' {ASTStackHandler.updateCurrSubTree();} | id {ASTStackHandler.addTermNode(ASTNodeType.IDENTIFIER, $id.text);ASTStackHandler.updateCurrSubTree(); } | INTLITERAL {ASTStackHandler.addTermNode(ASTNodeType.LITERAL, $INTLITERAL.text);ASTStackHandler.updateCurrSubTree();} | FLOATLITERAL {ASTStackHandler.addTermNode(ASTNodeType.LITERAL, $FLOATLITERAL.text); ASTStackHandler.updateCurrSubTree();};
addop             : '+' | '-';
mulop             : '*' | '/';

/* Complex Statements and Condition */ 
if_stmt           : 'IF' {SemanticDataHandler.pushNewScope(SCOPE.BLOCK, null);}'(' cond ')' decl  {SemanticDataHandler.popCurrentScope();} stmt_list else_part 'FI';
else_part         : 'ELSE' {SemanticDataHandler.pushNewScope(SCOPE.BLOCK, null);} decl  stmt_list {SemanticDataHandler.popCurrentScope();}| ;
cond              : expr compop expr;
compop            : '<' | '>' | '=' | '!=' | '<=' | '>=';

init_stmt         : assign_expr | ;
incr_stmt         : assign_expr | ;
/* ECE 468 students use this version of for_stmt */
for_stmt          : 'FOR' {SemanticDataHandler.pushNewScope(SCOPE.BLOCK, null);} '('init_stmt ';' cond ';' incr_stmt ')' decl /*{SemanticDataHandler.displayCurrentScope();}*/ stmt_list {SemanticDataHandler.popCurrentScope();} 'ROF';

/************************************Micro language lexer rules********************************************************/
fragment DIGIT : ('0'..'9');
fragment LETTER : ('a'..'z' | 'A'..'Z');
PROGRAM : 'PROGRAM';

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
