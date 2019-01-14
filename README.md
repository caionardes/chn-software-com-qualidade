# Projeto de estudo prático Domain Driven Design (DDD)

- Stack: Java8, Spring Boot, MongoDB, REST, Swagger-UI.

O "Software" é a classe central do dominio.

Foi desenvolvido neste projeto apenas o necessário para exercitar os conceitos do DDD.

As operações desta aplicação são baseadas nos seguintes comportamentos: 
	
	String criarSoftware(String nome);
	void adicionarFuncionalidade(IdSoftware idSoftware, String nomeUseCase, String documentacao);
	void registraInicioNovaAtividade(IdSoftware idSoftware, IdProfissional idProfissional, IdFuncionalidade idFuncionalidade);
	void registraConclusaoAtividade(IdSoftware idSoftware, IdProfissional idProfissional, IdFuncionalidade idFuncionalidade); 

Um "Software" possui "Funcionalidades" que são desenvolvidas por "Profissionais" em uma linha de produção, de acordo com um fluxo configurado.
<br>(Atualmente o fluxo está em HardCode, mas já foi criado o FluxoAtividadeRepository para futura implementação).

Cada etapa da produção deve ser executada por um "Profissional" com perfil adequado para tal, por exemplo "Analista" faz a analise, "Desenvolvedor" o desenvolvimento e "Testador" os testes.

# Fluxo

O fluxo começa quando cria-se um novo "Sofware", sem "funcionalidades".
<br>Então, é possível adicionar "Funcionalidades" ao "Software", cada "Funcionalidade" possui sua "EspecificacaoTecnica" assim como seu status de progresso representado por "Atividade" e "HistoricoAtividade". 

Em paralelo são cadastrados os "Profissionais" com um conjunto de habilidades chamadas "Skills".
<br>Os "Skills" são representações dos papeis dos profissionais de T.I, como Analista, Desenvolvedor, Testador e Apresentador.

O "Profissional" pode registrar o inicio da execução da atividade e a conclusão da mesma.
<br>Quando é feita a conclusão da atividade, a "Funcionalidade" passa para proxima etapa do fluxo de desenvolvimento.

<br>Em seguida o sistema notifica os Profissionais com perfil compativeis para dar continuidade no desenvolvimento da Funcionalidade.
<br>(Ponto interessante para aplicar Event-Driven)

	Projeto baseado no exemplo: https://github.com/citerus/dddsample-core
