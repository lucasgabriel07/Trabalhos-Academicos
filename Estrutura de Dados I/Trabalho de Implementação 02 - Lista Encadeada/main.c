#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sllist.h"
#define TAM 39

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
char *linha(int tam);
char *mensagem(char *str, int tam);

int main() {
    SLList *l = NULL;
    Produto *p = NULL;
    int opcao, i;
    char nome[30];
    int codigo;
    float preco;

    while (TRUE) {
        opcao = menu();
        switch(opcao) {
            case 1:
                // Criar lista
                if (l == NULL) {
                    l = sllCreate();
                    if (l != NULL) {
                        printf("\n>> Lista criada com sucesso!\n");
                    } else {
                        printf("\n>> Erro ao criar lista.\n");
                    }
                } else {
                    printf("\n>> Ja existe uma lista criada.\n");
                }
                break;

            case 2:
                // Cadastrar novo item
                if (l != NULL) {
                    p = (Produto *)malloc(sizeof (Produto));
                    if (p != NULL) {
                        printf("\n");
                        strcpy(p->nome, lerNome());
                        p->codigo = lerCodigo();
                        p->preco = lerPreco();
                        if (sllInsertAsLast(l, (void *)p) == TRUE) {
                            printf("\n>> Adicionado com sucesso!\n");
                        } else {
                            printf("\n>> Ocorreu um erro.\n");
                        }
                    } else {
                        printf("\n>> Ocorreu um erro.\n");
                    }
                } else {
                    printf("\n>> A lista nao existe.\n");
                }
                break;

            case 3:
                // Remover item
                if (l != NULL) {
                    if (sllGetNumElms(l) > 0) {
                        opcao = escolherChave();
                        while (opcao<1 || opcao>3) {
                            printf("\n>> Opcao invalida.\n");
                            opcao = escolherChave();
                        }
                        printf("\n");
                        if (opcao == 1) {
                            strcpy(nome, lerNome());
                            p = (Produto *)sllRemoveSpec(l, (void *)nome, cmpNome);
                        }
                        else if (opcao == 2) {
                            codigo = lerCodigo();
                            p = (Produto *)sllRemoveSpec(l, (void *)&codigo, cmpCodigo);
                        }
                        else if (opcao == 3) {
                            preco = lerPreco();
                            p = (Produto *)sllRemoveSpec(l, (void *)&preco, cmpPreco);
                        }
                        if (p != NULL) {
                            printf("\n>> Item removido:\n");
                            printf("%s\n", linha(TAM));
                            printf(" Nome: %s \n Codigo: %d \n Preco: %.2f\n", p->nome, p->codigo, p->preco);
                            printf("%s\n", linha(TAM));
                        } else {
                            printf("\n>> Item nao encontrado.\n");
                        }
                    } else {
                        printf("\n>> Nao ha itens na lista.\n");
                    }
                } else {
                    printf("\n>> A lista nao existe.\n");
                }
                break;

            case 4:
                // Consultar item
                if (l != NULL) {
                    if (sllGetNumElms(l) > 0) {
                        opcao = escolherChave();
                        while (opcao<1 || opcao>3) {
                            printf("\n>> Opcao invalida.\n");
                            opcao = escolherChave();
                        }
                        printf("\n");
                        if (opcao == 1) {
                            strcpy(nome, lerNome());
                            p = (Produto *)sllGetDataSpec(l, (void *)nome, cmpNome);
                        }
                        else if (opcao == 2) {
                            codigo = lerCodigo();
                            p = (Produto *)sllGetDataSpec(l, (void *)&codigo, cmpCodigo);
                        }
                        else if (opcao == 3) {
                            preco = lerPreco();
                            p = (Produto *)sllGetDataSpec(l, (void *)&preco, cmpPreco);
                        }
                        if (p != NULL) {
                            printf("\n>> Item encontrado:\n");
                            printf("%s\n", linha(TAM));
                            printf(" Nome: %s \n Codigo: %d \n Preco: %.2f\n", p->nome, p->codigo, p->preco);
                            printf("%s\n", linha(TAM));
                        } else {
                            printf("\n>> Item nao encontrado.\n");
                        }
                    } else {
                        printf("\n>> Nao ha itens na lista.\n");
                    }
                } else {
                    printf("\n>> A lista nao existe.\n");
                }
                break;

            case 5:
                // Listar itens
                if (l != NULL) {
                    if (sllGetNumElms(l) > 0) {
                        i = 1;
                        p = (Produto *)sllGetFirst(l);
                        printf("\n%s\n", mensagem("Lista de itens", TAM));
                        while (p != NULL) {
                            printf(" #%d \n Nome: %s \n Codigo: %d \n Preco: %.2f\n", i, p->nome, p->codigo, p->preco);
                            printf("%s\n", linha(TAM));
                            p = (Produto *)sllGetNext(l);
                            i++;
                        }
                    } else {
                        printf("\n>> Nao ha itens na lista.\n");
                    }
                } else {
                    printf("\n>> A lista nao existe.\n");
                }
                break;

            case 6:
                // Esvaziar lista
                if (l != NULL) {
                    if (sllGetNumElms(l) > 0) {
                        p = sllRemoveFirst(l);
                        while (p != NULL) {
                            p = sllRemoveFirst(l);
                        }
                        printf("\n>> Lista esvaziada com sucesso!\n");
                    } else {
                        printf("\n>> A lista ja esta vazia.\n");
                    }
                } else {
                    printf("\n>> A lista nao existe.\n");
                }
                break;

            case 7:
                // Destruir lista
                if (l != NULL) {
                    if (sllGetNumElms(l) == 0) {
                        if (sllDestroy(l) == TRUE) {
                            printf("\n>> Lista destruida com sucesso!\n");
                            l = NULL;
                        } else {
                            printf("\n>> Ocorreu um erro.\n");
                        }
                    } else {
                        printf("\n>> Erro! Ainda ha itens na lista.\n");
                    }
                } else {
                    printf("\n>> A lista nao existe.\n");
                }
                break;

            case 8:
                // Sair
                printf("\n>> Saindo...\n");
                return 0;
                break;

            default:
                printf("\n>> Opcao invalida.\n");
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
    printf("%s\n", mensagem("OPCOES", TAM));
    printf(
        "(1) Criar lista\n"
        "(2) Cadastrar novo item\n"
        "(3) Remover item\n"
        "(4) Consultar item\n"
        "(5) Listar itens\n"
        "(6) Esvaziar lista\n"
        "(7) Destruir lista\n"
        "(8) Sair\n"
        "%s\n"
        "Sua opcao: ",
        linha(TAM)
    );
    scanf("%d", &opcao);
    getchar();
    return opcao;
}

int escolherChave() {
    int opcao;
    printf(
        "\nBuscar por:\n"
        "(1) Nome\n"
        "(2) Codigo\n"
        "(3) Preco\n"
        "Sua opcao: "
    );
    scanf("%d", &opcao);
    getchar();
    return opcao;
}

char *lerNome() {
    char *nome;
    nome = (char *)malloc(sizeof(char)*30);
	if  (nome != NULL) {
		printf("Informe o nome: ");
		fgets(nome, 30, stdin);
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

char *linha(int tam) {
    char *line;
    int i;
    line = (char *)malloc(sizeof(char)*tam);
    if (line != NULL) {
        strcpy(line, "");
        for (i=0; i<tam; i++) {
            if (i % 2 == 0) {
                strcat(line, "=");
            } else {
                strcat(line, "~");
            }
        }
        return line;
    }
    return NULL;
}

char *mensagem(char *str, int tam) {
    char *msg;
    int space, i;
    space = (tam - strlen(str))/2;
    msg = (char *)malloc(sizeof(char)*(3*tam+3));
    if (msg != NULL) {
        strcpy(msg, linha(tam));
        strcat(msg, "\n");
        for (i=0; i<space; i++) {
            strcat(msg, " ");
        }
        strcat(msg, str);
        strcat(msg, "\n");
        strcat(msg, linha(tam));
        return msg;
    }
    return NULL;
}