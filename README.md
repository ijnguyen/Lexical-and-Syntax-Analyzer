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
A → A - B
A → B
B → C
C → id
C → integer
C → (E)
```
Program structure
```
<Program> ::= <identifier> B| <stmt_list |E
<Statement_List> ::= <Statement>; | <Statement>; <Statement_List>
<Statement> ::= <if> | <assign>
<if> ::= go (<bool_expr>) | <Statement> [else <Statement>]
<assign> ::= `id` `->` <expr>
<expr> ::= <term> {(+|*) <term>}
<term> ::= <factor> {(/|-) <factor>}
<factor> ::= id | integer |(<expr>)
```

## LL Grammar
Yes, it passes disjoint test by not starting with same nonterminals.

## Ambiguous Grammar
I think it will not be ambiguous because the grammar does not create more than one derivations or parse trees. 

## LR(1)
Using the ***rules from Orders of Operations above*** and result:

![image](https://user-images.githubusercontent.com/94132772/206095744-b8ff769b-3a18-4bbe-b141-88a6c2c36f6c.png)

<img width="942" alt="image" src="https://user-images.githubusercontent.com/94132772/206274529-898657ca-7784-43cd-97d5-a1b1f6351756.png">
<img width="942" alt="image" src="https://user-images.githubusercontent.com/94132772/206274728-66c014c2-9ef0-43fb-a125-37631b931d02.png">

1. Code sample: id + id / id - id * id [PASS]

![image](https://user-images.githubusercontent.com/94132772/206095796-005d9188-b568-4ed1-82d9-679139f2ad5c.png)

2. Code sample: id + id / id - id + id [FAIL]

![image](https://user-images.githubusercontent.com/94132772/206095908-6bbbbb63-fc7d-4fb4-940f-3e79fe12d48a.png)

3. Code sample: id - id * id + id [PASS]

![image](https://user-images.githubusercontent.com/94132772/206096010-a46ff3c0-50c9-457e-a0e2-5c0929284328.png)

4. Code sample: id - id * id / id [FAIL]

![image](https://user-images.githubusercontent.com/94132772/206096133-995fa298-8193-4b9b-9707-ea405bf47742.png)
