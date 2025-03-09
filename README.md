# Academia
Repositório destinado a criação de um pequeno projeto para fechamento da disciplina de BD 2

## Razão para atividade

Esse sistema eu pensei para refletir um problema que um amigo anda tendo para o gerenciamento de pessoas na sua academia de luta. 
Então, saber sobre as aulas, alunos cadastrados, informações sobre os alunos e outras coisas a mais era de muita importancia. Então utilizei
dessa ideia para realizar a atividade para que se assemelhasse mais com a realidade. Para fazer o controle de informações sensiveis, utilizei o banco de dados relacional.
Já para controlar as imagens de comprovante de pagamento, além de utilizar do bando relacional para armenzar os ids referentes ao pagamento e a esse comprovante. Utilizei 
de um modelo não relacional para armazenar informações pertinentes a como resgatar e salvar essas imagens dentro do MinIO. Fazendo assim a comunicação entre as atividades 
do MinIO, JDBC e MongoDB.

- Para realizar esse projeto adotei o jdbc para fazer a comunicação com o meu banco de dados. As tabelas para o banco de dados relacional estão descritas na parte referente ao changelog.
- Para poder realizar o controle das fotos dos comprovantes de pagamentos dessa academia, usei do mongoDB para armazenar informações sobre os buckets associados no MinIO.
- Utilizei o MinIO para guardar as imagens de comprovantes.

## Como rodar
- Para poder rodar o projeto é necessário criar os arquivos .env e liquibase.properties que estão como exemplo;
- No package db.changelog existe um arquivo de sql que contém as tabelas para versionar no seu banco de dados;
- *Para executar o projeto, não use o Main. Ainda não está desenvolvido*
- Como essa primeira fase foi para fechar uma atividade da matéria de BD2, não é necessário a criação de uma API. Então fiz testes unitários que vão fazer todas as operações que consta nos repositories;
- Para executar digite: **mvn test**
