Teste tecnico Digio

Microserviço usando java 17 com spring

Open Feign para consumo dos dados
Spring Rest para criação dos endpoints:

Para rodar o serviço executar o arquivo DigioApplication

GET: /compras - Retornar uma lista das compras ordenadas de forma crescente por valor, deve conter o nome dos clientes, cpf dos clientes, dado dos produtos, quantidade das compras e valores totais de cada compra.
GET: /maior-compra/ano - (Exemplo: /maior_compra/2016) - Retornar a maior compra do ano informando os dados da compra disponibilizados, deve ter o nome do cliente, cpf do cliente, dado do produto, quantidade da compra e seu valor total.
GET: /clientes-fieis - Retornar o Top 3 clientes mais fieis, clientes que possuem mais compras recorrentes com maiores valores.
GET: /recomendacao/cliente/tipo - Retornar uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra.


Documentação Swagger
![image](https://github.com/user-attachments/assets/0bf02098-96df-441c-9c74-b29224ed083b)


Collection do Postman:

[Teste Digio.postman_collection.json](https://github.com/user-attachments/files/16355066/Teste.Digio.postman_collection.json)
