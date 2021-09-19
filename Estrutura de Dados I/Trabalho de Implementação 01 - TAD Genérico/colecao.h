#ifndef COLECAO_H_INCLUDED
#define COLECAO_H_INCLUDED
#define TRUE 1
#define FALSE 0

typedef struct _colecao_ Colecao;

Colecao *colCreate(int n);
int colInsert(Colecao *c, void *item);
void *colRemove(Colecao *c, void *key, int (*cmp)(void*,void*));
void *colQuery(Colecao *c, void *key, int (*cmp)(void*,void*));
void *colGetFirst(Colecao *c);
void *colGetNext(Colecao *c);
int colIsEmpty(Colecao *c);
int colIsFull(Colecao *c);
int colRemoveAll(Colecao *c);
int colDestroy(Colecao *c);

#endif // COLECAO_H_INCLUDED
