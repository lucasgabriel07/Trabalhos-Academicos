#ifndef TREE_H_INCLUDED
#define TREE_H_INCLUDED
#define TRUE 1
#define FALSE 0

typedef struct _tnode_ TNode;

TNode *abpCreate();
TNode *abpInsert(TNode *t, void *data, int (*cmp)(void *, void *));
TNode *abpRemove(TNode *t, void *key, int (*cmp)(void *, void *), void **data);
TNode *abpRemoveMaior(TNode *t, void *key, int (*cmp)(void *, void *), void **data);
TNode *abpRemoveAll(TNode *t);
void *abpQuery(TNode *t, void *key, int (*cmp)(void *, void *));
void simetrico(TNode *t, void(*visit)(void *));
void preOrdem(TNode *t, void(*visit)(void *));
void posOrdem(TNode *t, void(*visit)(void *));
int abNumNos(TNode *t);

#endif // TREE_H_INCLUDED
