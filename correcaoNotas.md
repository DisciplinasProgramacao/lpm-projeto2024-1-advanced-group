# ADVANCED 

## Sprint 1 - Até 07/abril
  
  - Nota de grupo (8 pontos)
    - Modelo UML - restaurante, mesas, requisicoes, cliente (nota de grupo, 8 pontos)
	
  - Nota individual (12 pontos)
    - Implementações e testes + app
    - Documentação das classes.

### Commit 2696c50 (08/abr)
Diagrama 1.0 - Notações de relações, métodos da requisição. - Todos - 6	

Cliente sem documentação, sem teste - Arthur - 4

Mesa sem documentação, sem teste. Muitos get/set sem sentido - Lucas - 4

Requisição sem documentação, sem teste. Sem lógica implementada - Luiz Felipe - 4

Restaurante sem documentação, sem teste. Muitos prints e gets - Eduardo - 7

App só testes - Luiz Paulo e Henrique - 4

## Sprint 2 - Até 19/maio

- Nota de grupo (6 pontos)
  - Modelo UML atualizado - cardápio e pedidos
  - Estrutura Spring
  - Nota individual (14 pontos)	
    - Implementações cardápio e pedidos
    - Controllers
    - Correções anteriores

### Commit 	c1c2b45 (20/05)
Problemas
    
    Classes sem documentação - Todos 
    Mesa com métodos da requisição (conta)
	
Diagrama 2.1 - Pedidos sem métodos addItemCardapio sem parametros, pedido na mesas, sem cardapio - Todos - 2
	
Item sem validação de get/set  - Luiz Paulo - 12

Requisição sem item individual, usando Cardapio (é de restaurante) - Luiz Felipe - 11,5

Restaurante com prints - Eduardo - 11,5

Controller Cliente em outra branch. Pedido controller sem update 'individual em outra branch Pedido/Cliente/Requisicao/mesa - Arthur - 8,5

Controller Mesa em outra branch. Requisicao controller em outra branch sem update e outras operacoes - Lucas - 8,5

Correcao do Main - apenas cliente e requisição - Henrique - 7,5

## Sprint 3 - Até 05/junho
  - Nota de grupo (6 pontos)
    - Modelo atualizado - menu fechado
  
  - Nota individual (14 pontos)	
    - Implementações menu fechado e app
    - Correções anteriores

### Commit ea5fe09
_Dada a confusão feita e refatoração sem aderência aos requisitos e cartões anteriores, esta sprint está avaliada coletivamente. A avaliação pode ser revista após a apresentação da Sprint 4, considerando as atribuições individuais_

Todos:
- Diagrama 6 - sem menu fechado e misturando controller com modelo - Todos - 3
- Sem classe restaurante. "jogaram fora" as entidades anteriores. - Todos - 7

## Sprint 4 - Apresentação em 24/06
  - Nota de grupo 7/10 pontos
	  - Modelo atualizado - sem pedido fechado. 
	  - Apresentação - ok
    - Runner/App
	
  - Nota individual (10 pontos)
    - Ajustes do último quadro "Projeto GitHub"
	  - Correções das sprints anteriores
	
### Cartões SP4

Restaurante (Lucas/Luiz Paulo): gets em sequencia. atualizar requisicoes com foreach. criar itens pedido com vários. sem tratamento de exceções. 6,5

Requisicao(Eduardo/Luiz Felipe): sets sem validação. Finalizar sem fechar pedido. Calcular totais fazendo 10% (regra do pedido) 6

Pedido (henrique): if para tipo!!! 4

Item/Enum (Arthur): ok, mas não era a tarefa marcada. 5

Runner/App: ok. Mas a modularidade/organização está muito ruim.  