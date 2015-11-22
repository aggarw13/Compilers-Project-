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
var_decl          : var_type id_list ';' {SemanticDataHandler.addScopeRecord($id_list.text, $var_type.text);   ASTStackHandler.updateFunctionScope($id_list.text, SCOPE.LOCAL); } ;
any_type          : var_type {ASTStackHandler.setFuncRetType($var_type.text);}| 'VOID' {ASTStackHandler.setFuncRetType(new String("VOID"));};
var_type	      : 'FLOAT'| 'INT';
id_list           : id id_tail;
id_tail           : ',' id id_tail | ;

/* Function Paramater List */
param_decl_list   : param_decl param_decl_tail | ;
param_decl        : var_type id {SemanticDataHandler.addScopeRecord($id.text, $var_type.text);  ASTStackHandler.updateFunctionScope($id.text, SCOPE.PARAM);} ;
param_decl_tail   : ',' param_decl param_decl_tail | ;

/* Function Declarations */
func_declarations : func_decl func_declarations | ;
func_decl         : 'FUNCTION' any_type id {SemanticDataHandler.pushNewScope(SCOPE.FUNCTION, $id.text); ASTStackHandler.addFunction($id.text);} '('param_decl_list')' 'BEGIN' func_body 'END' {SemanticDataHandler.popCurrentScope(); ASTStackHandler.addFuncInfoNode(ASTNodeType.FUNC_END);} ;
func_body         : decl stmt_list ;

/* Statement List */
stmt_list         : stmt stmt_list | ;
stmt              : base_stmt | if_stmt | for_stmt;
base_stmt         : assign_stmt | read_stmt | write_stmt | return_stmt;

/* Basic Statements */
assign_stmt       : assign_expr ';';
assign_expr       : id { ASTStackHandler.addTermNode(ASTNodeType.IDENTIFIER, $id.text);} ':=' {ASTStackHandler.pushAssignmentTree(SemanticDataHandler.currentScope);} expr {ASTStackHandler.updateCurrTree();} ;
read_stmt         : 'READ' '(' id_list ')' {ASTStackHandler.pushIOTree(IO_TYPE.READ, $id_list.text, SemanticDataHandler.currentScope);} ';';
write_stmt        : 'WRITE' '(' id_list ')' {ASTStackHandler.pushIOTree(IO_TYPE.WRITE, $id_list.text, SemanticDataHandler.currentScope);} ';';
return_stmt       : 'RETURN' expr ';' {ASTStackHandler.addFuncInfoNode(ASTNodeType.RETURN); } ;

/* Expressions */		
expr              : expr_prefix factor;
expr_prefix       : expr_prefix factor addop {ASTStackHandler.createArithmeticTree($addop.text); ASTStackHandler.subExprStack.push("+");} | ;
factor            : factor_prefix postfix_expr;
factor_prefix     : factor_prefix postfix_expr mulop {ASTStackHandler.createArithmeticTree($mulop.text); ASTStackHandler.subExprStack.push("*"); /*ASTStackHandler.SubTreeBlockEnded = false;*/} | ;
postfix_expr      : primary {ASTStackHandler.SubTreeBlock = false;}| call_expr;
call_expr         : id {ASTStackHandler.pushFunctionCall($id.text, ASTNodeType.FUNC_CALL_BEGIN); } '(' expr_list ')' {ASTStackHandler.pushFunctionCall(null, ASTNodeType.FUNC_CALL_END);} ;
expr_list         : expr {ASTStackHandler.pushFuncParamNode();} expr_list_tail | ;
expr_list_tail    : ',' expr {ASTStackHandler.pushFuncParamNode();} expr_list_tail | ;
primary           : {/*ASTStackHandler.SubTreeBlock = true; ASTStackHandler.SubTreeBlockEnded = false;*/ ASTStackHandler.subExprStack.push("(");} '(' expr  {/*ASTStackHandler.SubTreeBlock = false; ASTStackHandler.SubTreeBlockEnded = true;*/}  ')' {ASTStackHandler.subExprStack.push(")"); ASTStackHandler.updateCurrSubTree();} | id {ASTStackHandler.addTermNode(ASTNodeType.IDENTIFIER, $id.text); ASTStackHandler.updateCurrSubTree();} | INTLITERAL {ASTStackHandler.addTermNode(ASTNodeType.LITERAL, $INTLITERAL.text); ASTStackHandler.updateCurrSubTree();} | FLOATLITERAL {ASTStackHandler.addTermNode(ASTNodeType.LITERAL, $FLOATLITERAL.text); ASTStackHandler.updateCurrSubTree();};
addop             : '+' | '-';
mulop             : '*' | '/';

/* Complex Statements and Condition */ 
if_stmt           : 'IF' {SemanticDataHandler.pushNewScope(SCOPE.BLOCK, null); ASTStackHandler.pushIFELSEStructureTree(ASTNodeType.IF, SemanticDataHandler.currentScope);}'(' cond ')' decl  {SemanticDataHandler.popCurrentScope();} stmt_list else_part 'FI' {ASTStackHandler.pushIFELSEStructureTree(ASTNodeType.FI, null);};
else_part         : 'ELSE' {SemanticDataHandler.pushNewScope(SCOPE.BLOCK, null); ASTStackHandler.pushIFELSEStructureTree(ASTNodeType.ELSE, SemanticDataHandler.currentScope);} decl  stmt_list {SemanticDataHandler.popCurrentScope();}| ;
cond              : expr {ASTStackHandler.setCondExpr("l", null);} compop {ASTStackHandler.setCondExpr("c", $compop.text);} expr {ASTStackHandler.setCondExpr("r", null);}; 
compop            : '<' | '>' | '=' | '!=' | '<=' | '>=';

init_stmt         : assign_expr {/* System.out.println("Completes INIT "); */ ASTStackHandler.changeNodeType(ASTNodeType.FOR_INIT, false);} | ;
incr_stmt         : assign_expr {/* System.out.println("Completes INCR"); */ ASTStackHandler.changeNodeType(ASTNodeType.FOR_INCR, false);} | {ASTStackHandler.changeNodeType(ASTNodeType.FOR_INCR, true);} ;
/* ECE 468 students use this version of for_stmt */
for_stmt          : 'FOR' {SemanticDataHandler.pushNewScope(SCOPE.BLOCK, null); ASTStackHandler.pushFORStructure(ASTNodeType.FOR, SemanticDataHandler.currentScope); /*System.out.println("Starts expr tree creations!")*/;} '('init_stmt ';' cond ';' incr_stmt ')' { /*System.out.println("Finishes exprs tress creation!");*/} decl  stmt_list {SemanticDataHandler.popCurrentScope();} 'ROF' {ASTStackHandler.pushFORStructure(ASTNodeType.ROF, SemanticDataHandler.currentScope); /*System.out.println("Completes ROF");*/};

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
