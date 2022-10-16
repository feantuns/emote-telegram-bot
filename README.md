# JAVA Telegram Bot

Bot que devolve gifs baseado no emote icon que o usuário envia no telegram.

Desenvolvido no eclipse utilizando o JavaSE-11.

Todos as bibliotecas .jar utilzadas no projeto estão na pasta lib.

## Documentação

Toda a lógica está no arquivo `Main.java`, no método `main()`. Primeiro defino as variáveis que serão utilizadas na execução. As duas principais são a criação do `bot` e a criação da variável `giphy` que integra com a API do Giphy e possibilita o uso de gifs da plataforma.

Depois, há um loop while para ficar ouvindo mensagens que o bot recebe. Para cada mensagem é verificado se a mensagem é um emote. Caso seja, são buscados 10 gifs na API do Giphy e um gif aleatório entre os 10 é retornado. Caso a mensagem não seja um emote, retorno a mensagem "Envie um emote 🙂 para receber um GIF.".

## Links

Para conseguir uma chave para a API do Giphy:

[https://developers.giphy.com/docs/api#quick-start-guide](https://developers.giphy.com/docs/api#quick-start-guide)

Link da biblioteca de integração com o Gihpy utulizada:

[https://github.com/keshrath/Giphy4J](https://github.com/keshrath/Giphy4J)