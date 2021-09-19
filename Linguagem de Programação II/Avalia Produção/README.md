# Trabalho - Avalia Produção

Sua missão, implementar a auto avaliação de produção científica dos programas de pós graduação em Ciência da Computação da UFMA.

Para tanto, assim como no lab, você irá receber arquivos com a situação atual da produção dos professores. 
Você deve ler os arquivos, processar os dados, e realizar a avaliação da produção dos docentes.

No final, devem ser gerados os seguintes relatórios (para cada setor):
  * Produção, por professor, com o qualis do lado (formato: ano da produção, titulo da produção, local da produção, avaliação)*
  * Produção, por professor, com o qualis do lado, separada por "Trabalho em anais de evento" de "Artigo Publicado" (formato: ano da produção, titulo da produção, local da produção, avaliação)*
  * Síntese por professor do quantitativo da produção (similar ao apresentado no lab)**

*Ordenado por ano, do mais recente para o mais antigo

**Ordenado por ordem alfabética do nome do professor

Atenção: os relatórios devem ser gerados na forma de arquivos. Preferência pelo formato do excel (exemplo de biblioteca para se usar JExcel)

Adicionalmente, o usuário poderá selecionar o intervalo de tempo que desejar para fazer a análise. Por exemplo, de 2017 à 2021. Ou de 2020 à 2021. Somente produções desse intervalo de tempo devem ser consideradas para análise

### Regras:
Para encontrar o estrato de um artigo em periodico, procure pelo producao_issn nos dados de estrato_artigo_periodico
Para encontrar o estrato de um artigo em evento:
procure por sigla_evento (de estrato_artigo_em_evento) nos campos  nome_evento e titulo_anais_ou_proceedings
procure por nome_evento (de estrato_artigo_em_evento) nos campos  nome_evento e titulo_anais_ou_proceeding
Atenção, procure usando substrings.
Em qualquer um dos casos que encontrar, o resultado deve ser o estrato correspondente.


Metadados dos arquivos em anexo:
 setor {id, nome, sigla}
   id: identificador numérico do setor


 curriculo {id, nome_completo}
   id: identificador numérico do currículo


 curriculo_Setor {curriculo_id, setor_id}
   relacionamento de setor com currículo contendo o identificador numérico de cada um


 estrato_artigo_periodico {producao_issn,nome_periodico,estrato}
   producao_issn: identificador numérico da revista
   estrato: avaliação da revista (A1, A2, ...., SQ)


 estrato_artigo_em_evento {sigla_evento,nome_evento,estrato}
   sigla_evento: identificador textual do congresso
   nome_evento: identificador textual do congresso
   estrato: avaliação do congresso (A1, A2, ...., SQ)
 
 artigos_em_evento{curriculo_id,titulo_artigo,nome_evento,titulo_anais_ou_proceedings,ano_trabalho}
   curriculo_id: indentificador do curriculo que publicou o artigo
   nome_evento: campo informado pelo autor do artigo
   titulo_anais_ou_proceedings: campo informado pelo autor do artigo


 artigos_em_periodico{curriculo_id,titulo_artigo,producao_issn,nome_periodico,ano_trabalho}
   producao_issn: identificador numérico da revista
