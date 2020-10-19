UC7 – Associate Postal Address with Customer
==============================

## Short Format

O cliente inicia a associação de um novo endereço postal à sua informação. O sistema solicita os dados necessários (endereço postal). O cliente introduz os dados solicitados. O
sistema valida e apresenta os dados, pedindo que os confirme. O cliente
confirma. O sistema **associa o endereço postal ao cliente** e informa-o do sucesso da
operação.

## SSD


![SSD_UC7.jpg](SSD_UC7.jpg)

## Full Format

### Ator principal

Cliente

### Partes interessadas e seus interesses

-   **Cliente:** pretende associar um novo endereço à sua lista de endereços.

-   **Empresa:** pretende que a cliente possa solicitar serviços para qualquer
    um dos seus endereços.

### Pré-condições

(Estar autenticado no sistema como cliente.)

### Pós-condições

O endereço do cliente é guardado no sistema.

Cenário de sucesso principal (ou fluxo básico)
----------------------------------------------

1.  O cliente inicia a associação de um novo endereço postal à sua informação.
2.  O sistema solicita os dados necessários (i.e. endereço postal).
3.  O Cliente introduz os dados solicitados.
4.  O sistema valida e apresenta os dados, pedindo que os confirme.
5.  O Cliente confirma.
6.  O sistema **associa o endereço postal ao cliente** e informa-o do sucesso da
operação.
    

### Extensões (ou fluxos alternativos)

\*a. O Cliente solicita o cancelamento da registo.

>   O caso de uso termina.

4a. Dados mínimos obrigatórios em falta.

>   O sistema informa quais os dados em falta.

>   O sistema permite a introdução dos dados em falta (passo 2)

>   2a. O Cliente altera os dados. O caso de uso termina.

4b. O sistema deteta que os dados (ou algum subconjunto dos dados) introduzidos
devem ser únicos e que já existem no sistema.

>   O sistema alerta o Cliente para o facto.

>   O sistema permite a sua alteração (passo 2)

>   2a. O Cliente não altera os dados. O caso de uso termina.

4c. O sistema detecta que os dados introduzidos (ou algum subconjunto dos dados)
são inválidos.

>   O sistema alerta o Cliente para o facto.

>   O sistema permite a sua alteração (passo 2).

>   2a. O Cliente não altera os dados. O caso de uso termina.

### Requisitos especiais

\-

### Lista de Variações de Tecnologias e Dados

\-

### Frequência de Ocorrência

\-

### Questões em aberto

-   Quais os dados que em conjunto permitem detetar a duplicação de endereços?
-   O cliente deve ter algum endereço postal como preferencial?
-   Qual a frequência de ocorrência deste caso de uso?
