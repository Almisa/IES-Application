Como Base de dados utilizamos MariaDB, em que os nosso dados s�o l� armazenados.
Para ter a aplica��o funcionar em qualquer Base de dados local, basta mudar os dados que se encontram no ficheiro MariaDB para os dados da base de dados local.
Depois da conta estar criadas apenas basta mudar os dados da cloud para o da conta criada no ficheiro Subscriber.
Para enviar a mensagem para a aplica��o basta apenas utilizar a aplica��o de girosc�pio disponibilizada e mudar os dados da cloud, para estes serem enviados.
Caso queira simular os dados basta ter uma conta no cloudamqp e usar o site para simular as mensagens. 
Para o envio ser bem sucedido o t�pico desta tem de ser "sensor/data" e a mensagem inserida tem estar separada por "/" e possuir os dados todos necess�rios. Como por exemplo "102.07916/-10.02497/-3.000012/2"