#ifndef SLLIST_H_INCLUDED
#define SLLIST_H_INCLUDED
#define TRUE 1
#define FALSE 0

typedef struct _slnode_ SLNode;
typedef struct _sllist_ SLList;

SLList *sllCreate();
int sllDestroy(SLList *l);
int sllInsertFirst(SLList *l, void *data);
int sllInsertAsLast(SLList *l, void *data);
int sllGetNumElms(SLList *l);
int sllInsertElmAsPesimo(SLList *l, void *data, int p);
int sllInsertAfterSpec(SLList *l, void *key, void *data, int(*cmp)(void *, void *));
int sllInsertBeforeSpec(SLList *l, void *key, void *data, int (*cmp)(void *, void *));
void *sllRemoveFirst(SLList *l);
void *sllGetFirst(SLList *l);
void *sllGetNext(SLList *l);
void *sllGetLast(SLList *l);
void *sllGetDataSpec(SLList *l, void *key, int(*cmp)(void *, void *));
void *sllRemoveSpec(SLList *l, void *key, int(*cmp)(void*, void *));
void *sllRemoveAfterSpec(SLList *l, void *key, int (*cmp)(void *, void *));

#endif // SLLIST_H_INCLUDED
