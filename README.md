# Game Of Life
###Projeto 1 Técnicas de Programação 1 ministrada pelo professor Rodrigo Bonifácio
###2/2016
#####Alunos 

Davi Rabbouni de Carvalho Freitas - 15/0033010

Henrique Brant de Moraes Palmeirão Alvarenga - 15/0011644

Marcos Vinícius Prescendo Tonin - 14/0153233

###Objetivos concluidos

* Implementar método Strategy;
     Método de polimorfismo que permite a variação de regras.
     
* Computar próximas gerações automaticamente
     Sumarizando, foi posto um paramêtro *booleano* que indicava a execução de um *loop* ou não.    
    
* Implementar tabuleiro "infinito"
     Para fazer está fase foi pensado no recurso *Mapa de Karnaugh* usado em sistemas digitais, em que os vizinhos dos extremos se interconectam.

* Interface Gráfica (Java Swing)
     Para a interface gráfica foi utilizado o Java Swing, que é fornecido pela própria Java. Para facilitar foi criado uma pasta GUI com os arquivos .java referentes à interface. Para indicar se uma célula está viva ou morta foi indicado através da cores cinza e vermelho, respectivamente.

* Framework Spring (Injeção dependência)
     Permite a variabilidade do código, podendo transformar o núcleo do projeto em um *framework*.
    
* Memento: mecanismo de UNDO
     Para o fazer o memento foi salvo os estados anteriores em uma lista auxiliar, após feito isso foi criado a funcionalidade *UNDO*, que desfaz *n* gerações.
     
###Instruções de execução (Utilizando a IDE *Eclipse*):
* Adicionar as bibliotecas presentes na pasta *GameOfLife/libs* ao projeto na IDE.
* Utilizar a *JRE System Library [JavaSE-1.8]*.
#FIM DE PÔJETO
