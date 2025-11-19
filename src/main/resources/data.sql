-- =================================================================================
-- 1. INSERÇÃO DE GÊNEROS
-- =================================================================================

INSERT INTO generos (nome, descricao)
SELECT 'Comédia Romantica', 'Séries e Filmes focados em humor e relacionamentos amorosos.'
    WHERE NOT EXISTS (SELECT 1 FROM generos WHERE nome = 'Comédia Romantica');

INSERT INTO generos (nome, descricao)
SELECT 'Suspense', 'Séries e Filmes de alta tensão e mistério, envolvendo crimes ou conspirações.'
    WHERE NOT EXISTS (SELECT 1 FROM generos WHERE nome = 'Suspense');

INSERT INTO generos (nome, descricao)
SELECT 'Ficção Científica', 'Conteúdo focado em tecnologia futurista, espaço e distopias.'
    WHERE NOT EXISTS (SELECT 1 FROM generos WHERE nome = 'Ficção Científica');

INSERT INTO generos (nome, descricao)
SELECT 'Ação e Aventura', 'Cenas de combate, perseguições e grandes jornadas.'
    WHERE NOT EXISTS (SELECT 1 FROM generos WHERE nome = 'Ação e Aventura');

-- =================================================================================
-- 2. INSERÇÃO DE PLANOS
-- =================================================================================

INSERT INTO planos (nome, descricao, preco, duracao_dias, limite_dispositivos, resolucao, dispositivos_download, tem_anuncios, ativo, data_criacao)
SELECT 'Plano Bronze', 'Streaming básico em SD, com anúncios. Acesso limitado.', 19.90, 30, 1, 'SD (480p)', 0, TRUE, TRUE, CURRENT_TIMESTAMP()
    WHERE NOT EXISTS (SELECT 1 FROM planos WHERE nome = 'Plano Bronze');

INSERT INTO planos (nome, descricao, preco, duracao_dias, limite_dispositivos, resolucao, dispositivos_download, tem_anuncios, ativo, data_criacao)
SELECT 'Plano Prata', 'Streaming em HD, sem anúncios, 2 telas simultâneas.', 39.90, 30, 2, 'HD (720p)', 1, FALSE, TRUE, CURRENT_TIMESTAMP()
    WHERE NOT EXISTS (SELECT 1 FROM planos WHERE nome = 'Plano Prata');

INSERT INTO planos (nome, descricao, preco, duracao_dias, limite_dispositivos, resolucao, dispositivos_download, tem_anuncios, ativo, data_criacao)
SELECT 'Plano Ouro', 'Streaming em 4K, sem anúncios, 4 telas simultâneas e downloads ilimitados.', 59.90, 30, 4, '4K (2160p)', 5, FALSE, TRUE, CURRENT_TIMESTAMP()
    WHERE NOT EXISTS (SELECT 1 FROM planos WHERE nome = 'Plano Ouro');

-- =================================================================================
-- 3. INSERÇÃO DE FILMES
-- =================================================================================

INSERT INTO filmes (titulo, sinopse, data_lancamento, genero, duracao_minutos, classificacao_indicativa)
SELECT 'O Silêncio dos Inocentes', 'Uma jovem agente do FBI precisa da ajuda de um canibal psicopata para capturar um serial killer.', '1991-02-14', 'Suspense', 118, '18'
    WHERE NOT EXISTS (SELECT 1 FROM filmes WHERE titulo = 'O Silêncio dos Inocentes');

INSERT INTO filmes (titulo, sinopse, data_lancamento, genero, duracao_minutos, classificacao_indicativa)
SELECT 'A Origem', 'Um ladrão que rouba segredos corporativos através de tecnologia de compartilhamento de sonhos.', '2010-07-30', 'Ficção Científica', 148, '14'
    WHERE NOT EXISTS (SELECT 1 FROM filmes WHERE titulo = 'A Origem');

INSERT INTO filmes (titulo, sinopse, data_lancamento, genero, duracao_minutos, classificacao_indicativa)
SELECT 'Parasita', 'Uma família pobre se infiltra na vida de uma família rica.', '2019-11-07', 'Suspense', 132, '16'
    WHERE NOT EXISTS (SELECT 1 FROM filmes WHERE titulo = 'Parasita');

INSERT INTO filmes (titulo, sinopse, data_lancamento, genero, duracao_minutos, classificacao_indicativa)
SELECT 'Blade Runner 2049', 'Um caçador de andróides descobre um segredo que pode mergulhar o que resta da sociedade no caos.', '2017-10-05', 'Ficção Científica', 164, '14'
    WHERE NOT EXISTS (SELECT 1 FROM filmes WHERE titulo = 'Blade Runner 2049');

INSERT INTO filmes (titulo, sinopse, data_lancamento, genero, duracao_minutos, classificacao_indicativa)
SELECT 'La La Land: Cantando Estações', 'Um pianista de jazz se apaixona por uma aspirante a atriz em Los Angeles.', '2017-01-19', 'Comédia Romantica', 128, 'Livre'
    WHERE NOT EXISTS (SELECT 1 FROM filmes WHERE titulo = 'La La Land: Cantando Estações');

INSERT INTO filmes (titulo, sinopse, data_lancamento, genero, duracao_minutos, classificacao_indicativa)
SELECT 'Missão Impossível: Acerto de Contas', 'Ethan Hunt deve rastrear uma arma nova e aterrorizante antes que caia em mãos erradas.', '2023-07-13', 'Ação e Aventura', 163, '14'
    WHERE NOT EXISTS (SELECT 1 FROM filmes WHERE titulo = 'Missão Impossível: Acerto de Contas');

INSERT INTO filmes (titulo, sinopse, data_lancamento, genero, duracao_minutos, classificacao_indicativa)
SELECT 'O Grande Hotel Budapeste', 'As aventuras de Gustave H, um lendário concierge, e seu jovem aprendiz.', '2014-03-27', 'Comédia Romantica', 100, '14'
    WHERE NOT EXISTS (SELECT 1 FROM filmes WHERE titulo = 'O Grande Hotel Budapeste');

INSERT INTO filmes (titulo, sinopse, data_lancamento, genero, duracao_minutos, classificacao_indicativa)
SELECT 'Seven: Os Sete Crimes Capitais', 'Dois detetives caçam um serial killer que baseia seus assassinatos nos sete pecados capitais.', '1995-12-08', 'Suspense', 127, '18'
    WHERE NOT EXISTS (SELECT 1 FROM filmes WHERE titulo = 'Seven: Os Sete Crimes Capitais');

INSERT INTO filmes (titulo, sinopse, data_lancamento, genero, duracao_minutos, classificacao_indicativa)
SELECT 'Mad Max: Estrada da Fúria', 'Em um futuro pós-apocalíptico, uma mulher se rebela contra um tirano em busca de sua terra natal.', '2015-05-14', 'Ação e Aventura', 120, '16'
    WHERE NOT EXISTS (SELECT 1 FROM filmes WHERE titulo = 'Mad Max: Estrada da Fúria');

INSERT INTO filmes (titulo, sinopse, data_lancamento, genero, duracao_minutos, classificacao_indicativa)
SELECT 'Interestelar', 'Exploradores viajam através de um buraco de minhoca em busca de um novo lar para a humanidade.', '2014-11-06', 'Ficção Científica', 169, '12'
    WHERE NOT EXISTS (SELECT 1 FROM filmes WHERE titulo = 'Interestelar');

-- =================================================================================
-- 4. INSERÇÃO DE SÉRIES
-- =================================================================================

INSERT INTO series (titulo, sinopse, data_lancamento, genero, numero_temporadas, classificacao_indicativa, em_andamento, duracao_minutos)
SELECT 'Valéria', 'Uma jovem escritora e suas três amigas vivem em Madrid, enfrentando desafios da vida e do amor.', '2024-03-20', 'Comédia Romantica', 3, '16', TRUE, 45
    WHERE NOT EXISTS (SELECT 1 FROM series WHERE titulo = 'Valéria');

INSERT INTO series (titulo, sinopse, data_lancamento, genero, numero_temporadas, classificacao_indicativa, em_andamento, duracao_minutos)
SELECT 'Dark', 'O desaparecimento de duas crianças expõe as relações fraturadas entre quatro famílias e revela um segredo de viagem no tempo.', '2017-12-01', 'Ficção Científica', 3, '16', FALSE, 60
    WHERE NOT EXISTS (SELECT 1 FROM series WHERE titulo = 'Dark');

INSERT INTO series (titulo, sinopse, data_lancamento, genero, numero_temporadas, classificacao_indicativa, em_andamento, duracao_minutos)
SELECT 'Eu odeio o Natal', 'Série italiana sobre uma jovem que finge ter um namorado para a família durante o Natal.', '2023-12-01', 'Comédia Romantica', 2, 'Livre', FALSE, 50
    WHERE NOT EXISTS (SELECT 1 FROM series WHERE titulo = 'Eu odeio o Natal');

INSERT INTO series (titulo, sinopse, data_lancamento, genero, numero_temporadas, classificacao_indicativa, em_andamento, duracao_minutos)
SELECT 'The Crown', 'A história do reinado da Rainha Elizabeth II.', '2016-11-04', 'Suspense', 6, 'Livre', TRUE, 55
    WHERE NOT EXISTS (SELECT 1 FROM series WHERE titulo = 'The Crown');

INSERT INTO series (titulo, sinopse, data_lancamento, genero, numero_temporadas, classificacao_indicativa, em_andamento, duracao_minutos)
SELECT 'Stranger Things', 'Jovens amigos em busca de respostas após o desaparecimento de um deles.', '2016-07-15', 'Ficção Científica', 5, '14', TRUE, 50
    WHERE NOT EXISTS (SELECT 1 FROM series WHERE titulo = 'Stranger Things');

INSERT INTO series (titulo, sinopse, data_lancamento, genero, numero_temporadas, classificacao_indicativa, em_andamento, duracao_minutos)
SELECT 'Round 6', 'Pessoas endividadas competem em jogos mortais infantis por um prêmio em dinheiro.', '2021-09-17', 'Suspense', 1, '18', FALSE, 65
    WHERE NOT EXISTS (SELECT 1 FROM series WHERE titulo = 'Round 6');

INSERT INTO series (titulo, sinopse, data_lancamento, genero, numero_temporadas, classificacao_indicativa, em_andamento, duracao_minutos)
SELECT 'Cobra Kai', 'Décadas após a rivalidade de seus senseis, Daniel LaRusso e Johnny Lawrence reabrem seus dojôs.', '2018-05-02', 'Ação e Aventura', 6, '12', TRUE, 35
    WHERE NOT EXISTS (SELECT 1 FROM series WHERE titulo = 'Cobra Kai');

INSERT INTO series (titulo, sinopse, data_lancamento, genero, numero_temporadas, classificacao_indicativa, em_andamento, duracao_minutos)
SELECT 'Bridgerton', 'Oito irmãos inseparáveis buscam amor e felicidade na alta sociedade de Londres.', '2020-12-25', 'Comédia Romantica', 3, '14', TRUE, 60
    WHERE NOT EXISTS (SELECT 1 FROM series WHERE titulo = 'Bridgerton');

INSERT INTO series (titulo, sinopse, data_lancamento, genero, numero_temporadas, classificacao_indicativa, em_andamento, duracao_minutos)
SELECT 'O Mandaloriano', 'As aventuras de um caçador de recompensas solitário nos confins da galáxia.', '2019-11-12', 'Ficção Científica', 3, '12', TRUE, 40
    WHERE NOT EXISTS (SELECT 1 FROM series WHERE titulo = 'O Mandaloriano');

INSERT INTO series (titulo, sinopse, data_lancamento, genero, numero_temporadas, classificacao_indicativa, em_andamento, duracao_minutos)
SELECT 'Chernobyl', 'A história verídica do desastre nuclear de 1986 na Ucrânia.', '2019-05-06', 'Suspense', 1, '18', FALSE, 60
    WHERE NOT EXISTS (SELECT 1 FROM series WHERE titulo = 'Chernobyl');




