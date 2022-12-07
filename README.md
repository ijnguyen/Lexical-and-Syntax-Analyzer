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
<expr> ::= <term> {(+|-) <term>}
<factor> ::= id | (<expr>)
```

## LL Grammar
Yes, it passes disjoint test by not starting with same nonterminals.

## Ambiguous Grammar
I think it will not be ambiguous because the grammar does not create more than one derivations or parse trees. 

## LR(1)
Using the ***rules from Orders of Operations above*** and result:

![image](https://user-images.githubusercontent.com/94132772/206051136-33206bf6-ff8e-42f0-8eed-bfa01e9a7200.png)
1. Code sample: id + id / id - id * id [PASS]

![image](https://user-images.githubusercontent.com/94132772/206051478-d59d2365-9a19-47cc-a1ca-662549d99f92.png)

2. Code sample: id + id / id - id + id [FAIL]

![image](https://user-images.githubusercontent.com/94132772/206051886-e9d40ef0-7082-4175-bf6f-25c852e3d7e3.png)

3. Code sample: id - id * id + id [PASS]

![image](https://user-images.githubusercontent.com/94132772/206052728-a0251a34-f028-42e6-8865-9dd5ade2a6ee.png)

4. Code sample: id - id * id / id [FAIL]

![image](https://user-images.githubusercontent.com/94132772/206052924-59d5c47b-9114-4c8c-a023-4fc803102a11.png)

