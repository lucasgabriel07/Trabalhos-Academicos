#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "colecao.h"

typedef struct  _produto_ {
    char nome[30];
    int codigo;
    float preco;
} Produto;

int cmpNome(void *key, void *item);
int cmpCodigo(void *key, void *item);
int cmpPreco(void *key, void *item);
int menu();
int escolherChave();
char *lerNome();
int lerCodigo();
float lerPreco();

int main() {
    Colecao *c = NULL;
    Produto *p = NULL;
    int opcao, n, i;
    char nome[30];
    int codigo;
    float preco;

    while (TRUE) {

        opcao = menu();

        switch(opcao) {

            case 1:
                // Criar colecao
                if (c == NULL) {
                    printf("\nInforme o numero maximo de itens: ");
                    scanf("%d", &n);
                    getchar();
                    c = colCreate(n);
                    if (c != NULL) {
                        printf("\nColecao criada com sucesso!\n");
                    } else {
                        printf("\nErro ao criar colecao.\n");
                    }
                } else {
                    printf("\nJa existe uma colecao criada.\n");
                }
                break;

            case 2:
                // Cadastrar novo item
                if (c != NULL) {
                    if (!colIsFull(c)) {
                        p = (Produto *)malloc(sizeof (Produto));
                        if (p != NULL) {
                            printf("\n");
                            strcpy(p->nome, lerNome());
                            p->codigo = lerCodigo();
                            p->preco = lerPreco();

                            if (colInsert(c, (void *)p) == TRUE) {
                                printf("\nAdicionado com sucesso!\n");
                            } else {
                                printf("\nOcorreu um erro.\n");
                            }
                        } else {
                            printf("\nOcorreu um erro.\n");
                        }
                    } else {
                        printf("\nNao e possivel adicionar novos itens, pois a colecao esta cheia.\n");
                    }
                } else {
                    printf("\nA colecao nao existe.\n");
                }
                break;

            case 3:
                // Remover item
                if (c != NULL) {
                    if (!colIsEmpty(c)) {
                        opcao = escolherChave();
                        while (opcao<1 || opcao>3) {
                            printf("\nOpcao invalida.\n");
                            opcao = escolherChave();
                        }
                        printf("\n");
                        if (opcao == 1) {
                            strcpy(nome, lerNome());
                            p = (Produto *)colRemove(c, (void *)nome, cmpNome);
                        }
                        else if (opcao == 2) {
                            codigo = lerCodigo();
                            p = (Produto *)colRemove(c, (void *)&codigo, cmpCodigo);
                        }
                        else if (opcao == 3) {
                            preco = lerPreco();
                            p = (Produto *)colRemove(c, (void *)&preco, cmpPreco);
                        }
                        if (p != NULL) {
                            printf("\nItem removido:\n");
                            printf("\nNome: %s \nCodigo: %d \nPreco: %.2f\n", p->nome, p->codigo, p->preco);
                        } else {
                            printf("\nItem nao encontrado.");
                        }
                    } else {
                        printf("\nNao ha itens na colecao.\n");
                    }
                } else {
                    printf("\nA colecao nao existe.\n");
                }
                break;

            case 4:
                // Consultar item
                if (c != NULL) {
                    if (!colIsEmpty(c)) {
                        opcao = escolherChave();
                        while (opcao<1 || opcao>3) {
                            printf("\nOpcao invalida.\n");
                            opcao = escolherChave();
                        }
                        if (opcao == 1) {
                            strcpy(nome, lerNome());
                            p = (Produto *)colQuery(c, (void *)nome, cmpNome);
                        }
                        else if (opcao == 2) {
                            codigo = lerCodigo();
                            p = (Produto *)colQuery(c, (void *)&codigo, cmpCodigo);
                        }
                        else if (opcao == 3) {
                            preco = lerPreco();
                            p = (Produto *)colQuery(c, (void *)&preco, cmpPreco);
                        }

                        if (p != NULL) {
                            printf("\nItem encontrado:\n");
                            printf("\nNome: %s \nCodigo: %d \nPreco: %.2f\n", p->nome, p->codigo, p->preco);
                        } else {
                            printf("\nItem nao encontrado.\n");
                        }
                    } else {
                        printf("\nNao ha itens na colecao.\n");
                    }
                } else {
                    printf("\nA colecao nao existe.\n");
                }
                break;

            case 5:
                // Listar itens
                if (c != NULL) {
                    if (!colIsEmpty(c)) {
                        i = 1;
                        p = (Produto *)colGetFirst(c);
                        printf("\nLista de Itens:\n");
                        while (p != NULL) {
                            printf("\n%d - Nome: %s \t Codigo: %d \t Preco: %.2f", i, p->nome, p->codigo, p->preco);
                            p = (Produto *)colGetNext(c);
                            i++;
                        }
                        printf("\n");
                    } else {
                        printf("\nNao ha itens na colecao.\n");
                    }
                } else {
                    printf("\nA colecao nao existe.\n");
                }
                break;

            case 6:
                // Esvaziar colecao
                if (c != NULL) {
                    if (!colIsEmpty(c)) {
                        colRemoveAll(c);
                        printf("\nColecao esvaziada com sucesso!\n");
                    } else {
                        printf("\nA colecao ja esta vazia.\n");
                    }
                } else {
                    printf("\nA colecao nao existe.\n");
                }
                break;

            case 7:
                // Destruir colecao
                if (c != NULL) {
                    if (colIsEmpty(c)) {
                        if (colDestroy(c) == TRUE) {
                            printf("\nColecao destruida com sucesso!\n");
                            c = NULL;
                        } else {
                            printf("\nOcorreu um erro.\n");
                        }
                    } else {
                        printf("\nNao foi possivel realizar a operacao, pois ainda ha itens na colecao.\n");
                    }
                } else {
                    printf("\nA colecao nao existe.\n");
                }
                break;

            case 8:
                // Sair
                printf("\nSaindo...\n");
                return 0;
                break;

            default:
                printf("\nOpcao invalida.\n");
                break;
        }
        printf("\n");
    }
    return 0;
}

int cmpNome(void *key, void *item) {
    char *nome;
    Produto  *p;
    nome = (char *)key;
    p = (Produto *)item;
    if (strcmp(nome, p->nome) == 0) {
        return TRUE;
    }
    return FALSE;
}

int cmpCodigo(void *key, void *item) {
    int *codigo;
    Produto *p;
    codigo = (int *)key;
    p =  (Produto *)item;
    if (*codigo == p->codigo) {
        return TRUE;
    }
    return FALSE;
}

int cmpPreco(void *key, void *item) {
    float *preco;
    Produto  *p;
    preco = (float *)key;
    p =  (Produto *)item;
    if (*preco == p->preco) {
        return TRUE;
    }
    return FALSE;
}

int menu() {
    int opcao;
    printf("OPCOES:\n");
    printf("(1) Criar colecao\n");
    printf("(2) Cadastrar novo item\n");
    printf("(3) Remover item\n");
    printf("(4) Consultar item\n");
    printf("(5) Listar itens\n");
    printf("(6) Esvaziar colecao\n");
    printf("(7) Destruir colecao\n");
    printf("(8) Sair\n");
    printf("Sua opcao: ");
    scanf("%d", &opcao);
    getchar();
    return opcao;
}

int escolherChave() {
    int opcao;
    printf("\nBuscar por:\n");
    printf("(1) Nome\n");
    printf("(2) Codigo\n");
    printf("(3) Preco\n");
    printf("Sua opcao: ");
    scanf("%d", &opcao);
    getchar();
    return opcao;
}

char *lerNome() {
    char *nome;
    nome = (char *)malloc(sizeof(char)*30);
	if  (nome != NULL) {
		printf("Informe o nome: ");
		fgets (nome, 30, stdin);
	    nome[strlen(nome)-1] = '\0';
		return nome;
	}
	return NULL;
}

int lerCodigo() {
    int codigo;
    printf("Informe o codigo: ");
    scanf("%d", &codigo);
    getchar();
    return codigo;
}

float lerPreco() {
    float preco;
    printf("Informe o preco: ");
    scanf("%f", &preco);
    getchar();
    return preco;
}
