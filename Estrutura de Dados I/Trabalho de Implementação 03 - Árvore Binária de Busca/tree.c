#include <stdio.h>
#include <stdlib.h>
#include "tree.h"

typedef struct _tnode_ {
    void *data;
    struct _tnode_ *left;
    struct _tnode_ *right;
} TNode;

TNode *abpCreate() {
    return NULL;
}

TNode *abpInsert(TNode *t, void *data, int (*cmp)(void *, void *)) {
    TNode *newnode;
    if (t != NULL) {
        if (cmp(data, t->data) < 0) {
            t->left = abpInsert(t->left, data, cmp);
        } else {
            t->right = abpInsert(t->right, data, cmp);
        }
        return t;
    } else {
        newnode = (TNode *)malloc(sizeof(TNode));
        if (newnode != NULL) {
            newnode->data = data;
            newnode->left = NULL;
            newnode->right = NULL;
            return newnode;
        } else {
            return NULL;
        }
    }
}

TNode *abpRemove(TNode *t, void *key, int (*cmp)(void *, void *), void **data) {
    TNode *aux;
    void *data2;
    int stat;
    if (t != NULL) {
        stat = cmp(key, t->data);
        if (stat < 0) {
            t->left = abpRemove(t->left, key, cmp, data);
            return t;
        } else if (stat > 0) {
            t->right = abpRemove(t->right, key, cmp, data);
            return t;
        } else {
            if (t->left == NULL && t->right == NULL) {
                *data = t->data;
                free(t);
                return NULL;
            } else if (t->left == NULL) {
                aux = t->right;
                *data = t->data;
                free(t);
                return aux;
            } else if (t->right == NULL) {
                aux = t->left;
                *data = t->data;
                free(t);
                return aux;
            } else {
                *data = t->data;
                t->left = abpRemoveMaior(t->left, key, cmp, &data2);
                t->data = data2;
                return t;
            }
        }
    }
    *data = NULL;
    return NULL;
}

TNode *abpRemoveMaior(TNode *t, void *key, int (*cmp)(void *, void *), void **data) {
    TNode *aux;
    void *data2;
    if (t != NULL) {
        if (t->right !=  NULL) {
            t->right = abpRemoveMaior(t->left, key, cmp, &data2);
        } else {
            if (t->left != NULL) {
                aux = t->left;
                data = t->data;
                free(t);
                return aux;
            } else {
                *data = t->data;
                free(t);
                return NULL;
            }
        }
    }
    *data = NULL;
    return NULL;
}

TNode *abpRemoveAll(TNode *t) {
    if (t != NULL) {
        abpRemoveAll(t->left);
        abpRemoveAll(t->right);
        free(t->data);
        free(t);
    }
    return NULL;
}

void *abpQuery(TNode *t, void *key, int (*cmp)(void *, void *)) {
    int stat;
    if (t != NULL) {
        stat = cmp(key, t->data);
        if (stat == 0) {
            return t->data;
        } else if (stat < 0) {
            return abpQuery(t->left, key, cmp);
        } else {
            return abpQuery(t->right, key, cmp);
        }
    }
    return NULL;
}

void preOrdem(TNode *t, void (*visit)(void *)) {
    if (t != NULL) {
        visit(t->data);
        preOrdem(t->left, visit);
        preOrdem(t->right, visit);
    }
}

void simetrico(TNode *t, void (*visit)(void *)) {
    if (t != NULL) {
        simetrico(t->left, visit);
        visit(t->data);
        simetrico(t->right, visit);
    }
}

void posOrdem(TNode *t, void (*visit)(void *)) {
    if (t != NULL) {
        posOrdem(t->left, visit);
        posOrdem(t->right, visit);
        visit(t->data);
    }
}

int abNumNos(TNode *t) {
    int n, nl, nr;
    if (t != NULL) {
        nl = abNumNos(t->left);
        n = 1;
        nr = abNumNos(t->right);
        return n+nl+nr;
    }
    return 0;
}
