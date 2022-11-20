# Lexical-and-Syntax-Analyzer
This is a lexical and syntax analyzer.

## Rules for recognizing all lexemes as their proper token
Operations and its tokens:

![image](https://user-images.githubusercontent.com/94132772/202885369-3614eac1-072f-472b-9a06-714903586e0e.png)


Integer:
```
[0-9]*(b1|b2|b4|b8)
```

![image](https://user-images.githubusercontent.com/94132772/202883262-fc10d6b7-0316-44db-8841-1c27027933d0.png)

 
 Variable names:
 ![image](https://user-images.githubusercontent.com/94132772/202884387-4d5b544c-5713-4c52-a538-3fefd7961215.png)

 
 
Keyword and its regex:
```
loop: go
data type declaration: num | char | String
selection statement: choose
```

## Production rules for implementing the mathematical syntax of operators and operands, loops, variable declaration, selection statements
Order of Operations
```
E → E + T
E → T
T → T * F
T → F
F → F / A
F → A
A → B - C
B → C
C → id
C → (E)
```
Program structure
```
<Program> ::= <identifier> B| <stmt_list |E
<Statement_List> ::= <Statement>; | <Statement>; <Statement_List>
<Statement> ::= <if> | <assign>
<if> ::= go (<bool_expr>) | <Statement> [else <Statement>]
<assign> ::= `id` `->` <expr>
<expr> ::= <term> {(+|-) <term>}
<factor> ::= id | (<expr>)
```

## LL Grammar
Yes, it passes disjoint test by not starting with same nonterminals.

## Ambiguous Grammar
I think it will not be ambiguous because the grammar does not create more than one derivations or parse trees. 

## LR(1)
Using the rules from Operations above and result:

![image](https://user-images.githubusercontent.com/94132772/202885670-4e5d02a1-6055-49b9-81e6-e46baafd6cb0.png)
