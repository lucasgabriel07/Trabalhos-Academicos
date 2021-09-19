#include <stdio.h>
#include <stdlib.h>
#include "colecao.h"

typedef struct _colecao_ {
    int numItens;
    int maxItens;
    int current;
    void **item;
} Colecao;

Colecao *colCreate(int n) {
    Colecao *c;
    if (n > 0) {
        c = (Colecao *)malloc(sizeof(Colecao));
        if (c != NULL) {
            c->item = (void **)malloc(sizeof(void*)*n);
            if (c->item != NULL) {
                c->numItens = 0;
                c->current = 0;
                c->maxItens = n;
                return c;
            }
            free(c);
        }
    }
    return NULL;
}

int colInsert(Colecao *c, void *item) {
    if (c != NULL) {
        if (c->numItens < c->maxItens) {
            c->item[c->numItens] = item;
            c->numItens++;
            return TRUE;
        }
    }
    return FALSE;
}

void *colRemove(Colecao *c, void *key, int(*cmp)(void*,void*)) {
    int i, j;
    void *data;
    if (c != NULL) {
        if (c->numItens > 0) {
            for (i=0; i<c->numItens; i++) {
                if (cmp(key, c->item[i]) == TRUE) {
                    data = c->item[i];
                    for (j=i; j<c->numItens; j++) {
                        c->item[j] = c->item[j+1];
                    }
                    c->numItens--;
                    return data;
                }
            }
        }
    }
    return NULL;
}

void *colQuery(Colecao *c, void *key, int(*cmp)(void*,void*)) {
    int i;
    if (c != NULL) {
        if (c->numItens > 0) {
            for (i=0; i<c->numItens; i++) {
                if (cmp(key, c->item[i]) == TRUE) {
                    return c->item[i];
                }
            }
        }
    }
    return NULL;
}

void *colGetFirst(Colecao *c) {
    if (c != NULL) {
        c->current = 0;
        return c->item[0];
    }
    return NULL;
}

void *colGetNext(Colecao *c) {
    if (c != NULL) {
        c->current++;
        if (c->current < c->numItens) {
            return c->item[c->current];
        }
    }
    return NULL;
}

int colIsEmpty(Colecao *c) {
    if (c != NULL) {
        if (c->numItens == 0) {
            return TRUE;
        }
    }
    return FALSE;
}

int colIsFull(Colecao *c) {
    if (c != NULL) {
        if (c->numItens == c->maxItens) {
            return TRUE;
        }
    }
    return FALSE;
}

int colRemoveAll(Colecao *c) {
    if (c != NULL) {
        c->numItens = 0;
        return TRUE;
    }
    return FALSE;
}

int colDestroy(Colecao *c) {
    if (c != NULL) {
        if (c->numItens == 0) {
            free(c->item);
            free(c);
            return TRUE;
        }
    }
    return FALSE;
}
