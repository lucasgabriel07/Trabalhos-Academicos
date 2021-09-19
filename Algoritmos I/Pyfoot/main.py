from perguntas_e_respostas import perguntas, respostas
from random import randint, shuffle
from time import sleep
import os


def limpar_tela():
    """https://www.geeksforgeeks.org/clear-screen-python/"""
    if os.name == 'nt':  # Windows
        os.system('cls')
    else:
        os.system('clear')


def ajuda():
    comandos = f"""
>> ajuda
Exibe novamente os comandos.

>> j
Exibe a tabela de jogos.

>> c
Exibe a tabela de classificação.

>> p
Inicia o próximo jogo."""

    return comandos


def iniciar_rodada(rodada):
    for i, jogo in enumerate(lista_de_jogos[rodada]):
        time1, time2 = jogo[0], jogo[1]

        if time_player == time1:
            gols1, gols2 = partida(time2)
        elif time_player == time2:
            gols2, gols1 = partida(time1)
        else:
            gols1 = randint(0, 4)
            gols2 = randint(0, 4)

        lista_de_jogos[rodada][i][2] = gols1
        lista_de_jogos[rodada][i][3] = gols2


def partida(adversario):
    gols_player = 0
    gols_adversario = 0

    print(f'\nComeça o jogo! {time_player} x {adversario} pela {rodada_atual+1}ª Rodada.')
    print(60*'-')

    for i in range(4):
        sleep(0.5)

        time = pergunta(adversario)  # Time que marcou gol

        if time == time_player:
            gols_player += 1
        else:
            gols_adversario += 1

        if time == time_player:
            print('\033[1;32m' + f'\nGOOOOOOOOL DO {time}!' + '\033[0;0m')
        else:
            print('\033[1;31m' + f'\nGOOOOOOOOL DO {time}!' + '\033[0;0m')

        if index % 4 != 0 or index == 0:  # Não exibir na pergunta final de cada jogo
            print(f'\nPlacar atual: {time_player} {gols_player} x {gols_adversario} {adversario}')
            print(60 * '-')

    print(f'\nFim de jogo! Placar Final: {time_player} {gols_player} x {gols_adversario} {adversario}')
    print(60 * '-')

    return gols_player, gols_adversario


def pergunta(adversario):
    global index

    pergunta = perguntas[index]
    resposta = respostas[index]

    while True:
        print(pergunta)
        entrada = input('\nO que será exibido na tela? >> ')
        if entrada == '' or entrada.isspace():
            print('\033[1;31m' + '\nEntrada inválida!' + '\033[0;0m')
        else:
            break

    index += 1

    if entrada == resposta:
        return time_player
    return adversario


def gerar_jogos():
    """Baseado em https://pt.wikipedia.org/wiki/Competi%C3%A7%C3%B5es_de_todos_contra_todos"""
    lista_auxiliar = lista_de_times[:]
    shuffle(lista_auxiliar)
    jogos = []
    for rodada in range(len(lista_de_times) - 1):  # Número de rodadas
        jogos_na_rodada = []
        for i in range(len(lista_de_times)//2):  # Jogos por rodada
            time1 = lista_auxiliar[i]
            time2 = lista_auxiliar[-i-1]
            jogos_na_rodada.append([time1, time2, '', ''])

        jogos.append(jogos_na_rodada)

        lista_auxiliar.insert(1, lista_auxiliar.pop())

    return jogos


def atualizar_pontuacao(rodada):
    for i in range(len(lista_de_times)):  # Número de times
        time = lista_de_times[i]
        for j in range(len(lista_de_times)//2):  # Jogos por rodada
            time1, time2, gols1, gols2 = lista_de_jogos[rodada][j]
            if time == time1:
                if gols1 > gols2:
                    pontuacao[i][0] += 3          # Pontos
                    pontuacao[i][2] += 1          # Vitórias
                elif gols1 == gols2:
                    pontuacao[i][0] += 1          # Pontos
                    pontuacao[i][3] += 1          # Empates
                else:
                    pontuacao[i][4] += 1          # Derrotas

                pontuacao[i][1] += 1              # Jogos
                pontuacao[i][5] += gols1          # Gols Feitos
                pontuacao[i][6] += gols2          # Gols Sofridos
                pontuacao[i][7] += gols1 - gols2  # Saldo de Gols

            elif time == time2:
                if gols2 > gols1:
                    pontuacao[i][0] += 3          # Pontos
                    pontuacao[i][2] += 1          # Vitórias
                elif gols1 == gols2:
                    pontuacao[i][0] += 1          # Pontos
                    pontuacao[i][3] += 1          # Empates
                else:
                    pontuacao[i][4] += 1          # Derrotas

                pontuacao[i][1] += 1              # Jogos
                pontuacao[i][5] += gols2          # Gols Feitos
                pontuacao[i][6] += gols1          # Gols Sofridos
                pontuacao[i][7] += gols2 - gols1  # Saldo de Gols


def atualizar_classificacao():
    for i in range(len(lista_de_times)):  # Número de times

        for j in range(i+1, len(lista_de_times)):  # Número de times

            if pontuacao[i][0] < pontuacao[j][0]:  # Pontos
                lista_de_times[i], lista_de_times[j] = lista_de_times[j],  lista_de_times[i]
                pontuacao[i], pontuacao[j] = pontuacao[j], pontuacao[i]

            elif pontuacao[i][0] == pontuacao[j][0]:

                if pontuacao[i][7] < pontuacao[j][7]:  # Saldo de Gols
                    lista_de_times[i], lista_de_times[j] = lista_de_times[j], lista_de_times[i]
                    pontuacao[i], pontuacao[j] = pontuacao[j], pontuacao[i]

                elif pontuacao[i][7] == pontuacao[j][7]:

                    if pontuacao[i][5] < pontuacao[j][5]:  # Gols Feitos
                        lista_de_times[i], lista_de_times[j] = lista_de_times[j], lista_de_times[i]
                        pontuacao[i], pontuacao[j] = pontuacao[j], pontuacao[i]


def tabela_de_jogos():
    print('\n' + 60*'-')
    print('TABELA DE JOGOS'.center(60))
    for rodada in range(len(lista_de_jogos)):  # Número de rodadas
        print('\033[;1m' + 60*'=' + '\033[0;0m')
        print(f'{rodada+1}ª RODADA'.center(60))
        print('\033[;1m' + 60*'=' + '\033[0;0m')

        for i in range(len(lista_de_times)//2):  # Jogos por rodada
            time1, time2, gols_time1, gols_time2 = lista_de_jogos[rodada][i]
            print(f'{time1:^20} {gols_time1:>1} x {gols_time2:<1} {time2:^20}'.center(60))
            if i < len(lista_de_times)//2 - 1:
                print(60 * '-')

    print(60 * '-')


def classificacao():
    print('\n' + 60 * '-')
    print('CLASSIFICAÇÃO'.center(60))
    print(60 * '-')
    print(f"{'#':^3} {'TIMES':^24} {'PG':^3} {'J':^3} {'V':^3} {'E':^3} {'D':^3} {'GP':^3} {'GC':^3} {'SG':^3}".center(60))
    print(60*'-')
    for i in range(len(lista_de_times)):  # Número de times
        time = lista_de_times[i]
        pg, j, v, e, d, gp, gc, sg = pontuacao[i]
        print(f'{i+1:^3} {time:<24} {pg:^3} {j:^3} {v:^3} {e:^3} {d:^3} {gp:^3} {gc:^3} {sg:^3}'.center(60))
        print(60*'-')


print("""
Você é um treinador de futebol iniciante e recebeu propostas
dos 8 times do Campeonato Maranhense de 2020, e precisa 
escolher qual time deseja comandar.

    0) CORDINO
    1) IMPERATRIZ
    2) JUVENTUDE
    3) MARANHÃO
    4) MOTO CLUB
    5) PINHEIRO
    6) SAMPAIO CORRÊA
    7) SÃO JOSÉ""")

lista_de_times = ['CORDINO', 'IMPERATRIZ', 'JUVENTUDE', 'MARANHÃO',
                  'MOTO CLUB', 'PINHEIRO', 'SAMPAIO CORRÊA', 'SÃO JOSÉ']

lista_de_jogos = gerar_jogos()
pontuacao = []
for i in range(len(lista_de_times)):  # Número de times
    pontuacao.append([0, 0, 0, 0, 0, 0, 0, 0])  # PG, J, V, E, D, GP, GC, SG

while True:
    try:
        index = int(input('\nQual time você deseja comandar? (Inteiro de 0 a 7) >> '))
        time_player = lista_de_times[index]
        break
    except (ValueError, IndexError):
        print('\033[1;31m \n' + 'Entrada inválida! Entre com um inteiro de 0 a 7.' + '\033[0;0m')

sleep(0.5)
limpar_tela()

print('\n' + 24*'-' + ' INSTRUÇÕES ' + 24*'-')

print(f"""
Parabéns! Você é o novo treinador do {time_player}!
Esperamos que você honre nossa camisa e nos leve ao título!

O jogo consiste em ler códigos em Python e responder qual 
informação será impressa na tela. Se você acertar, marcará
um gol, mas se errar sofrerá um gol. Serão respondidas 4 
questões por jogo. O campeonato será por pontos corridos,
no sistema todos contra todos, e em turno único.

Ao final de cada rodada, você pode optar por ver a 
classificação, a tabela de jogos, ou jogar a próxima 
partida, usando os seguintes comandos:""")

print(ajuda())
print('\n' + 60*'-')

rodada_atual = 0
index = 0

while rodada_atual <= len(lista_de_jogos) - 1:

    entrada = input('\nO que deseja fazer? >> ').lower()

    if entrada == 'ajuda':
        limpar_tela()
        print()
        print(60*'-')
        print('LISTA DE COMANDOS'.center(60))
        print(60*'-')
        print(ajuda())
    elif entrada == 'c':
        limpar_tela()
        classificacao()
    elif entrada == 'j':
        limpar_tela()
        tabela_de_jogos()
    elif entrada == 'p':
        limpar_tela()
        iniciar_rodada(rodada_atual)
        atualizar_pontuacao(rodada_atual)
        atualizar_classificacao()
        rodada_atual += 1
    else:
        print('\033[1;31m \n' + 'Comando inválido!' + '\033[0;0m')

sleep(1.5)
limpar_tela()

print('\n' + 21*'-' + ' RESULTADO FINAL ' + 22*'-')
sleep(1.5)
tabela_de_jogos()
sleep(1.5)
classificacao()
sleep(1.5)

campeao = lista_de_times[0]

if campeao == time_player:
    print('\033[1;32m' + f'\nPARABÉNS! SEU TIME, {time_player}, É O NOVO CAMPEÃO!' + '\033[0;0m')
else:
    i = 0
    while i <= 8:  # Número de times
        if lista_de_times[i] == time_player:
            break
        i += 1

    print('\033[1;31m' + f'\nO {campeao} FOI CAMPEÃO. SEU TIME, {time_player}, FICOU NA\n'
          f'{i+1}ª COLOCAÇÃO.' + '\033[0;0m')
