# GeradorDeUsuariosMikroTik
 
 Código que lê um arquivo .txt separa os campos e a partir dele usando a API MikroTik registra ou deleta novos usuários para hotéis.

 
* Formatação do Documento

O código lê um arquivo denominado "frontint.txt" (O arquivo que ele lê pode ser trocado no arquivo config.properties) 

o Documento (nome dado ao "frontint.txt") é formatado em:

 
     operação+quarto,        ,Data+Hora+Data,                    ,Nome,             ,Codigo.
     CKO405               2020-09-0417:472020-09-09         BIANCA ULTIMA ANNI        0002

* Operação

Neste Código utilizamos a operação = "CKO" para deletar um usuário, e a operação = "CKI" para adicioná lo.




# Funcionamento

para este caso utilizamos o nome do hóspede como usuário e o quarto como senha, utilizamos de um projeto usando Maven (com o Eclipse IDE) e a API do MikroTik (https://github.com/GideonLeGrange/mikrotik-java), Primeiramente o codigo abre as configurações para então separar os campos do "Documento" em um array que denominamos de "ArrayTemp" opôs isso é criada uma String com o comando que irá para o MikroTik (Já com o usuário e a senha e caso for uma operação de deletar o comando é diferente) apagamos por fim o ArrayTemp, e adicionamos essa String em um outro array denominado "ArrayFinal" e repetimos esse processo para cada hóspede registrado no "Documento", por fim enviamos todos os comandos já prontos para o Mikrotik.
