#include <stdio.h>
#include <stdlib.h>
#include "sllist.h"

typedef struct _slnode_ {
    void *data;
    struct _slnode_ *next;
} SLNode;

typedef struct _sllist_ {
    SLNode *first;
    SLNode *cur;
} SLList;

SLList *sllCreate() {
    SLList *l;
    l = (SLList *)malloc(sizeof(SLList));
    if (l != NULL) {
        l->first = NULL;
        l->cur = NULL;
        return l;
    }
}

int sllDestroy(SLList *l) {
    if (l != NULL) {
        if (l->first == NULL) {
            free(l);
            return TRUE;
        }
    }
    return FALSE;
}

int sllInsertFirst(SLList *l, void *data) {
    SLNode *newnode;
    if (l != NULL) {
        newnode = (SLNode *)malloc(sizeof(SLNode));
        if (newnode != NULL) {
            newnode->data = data;
            newnode->next = l->first;
            l->first = newnode;
            return TRUE;
        }
    }
    return FALSE;
}

int sllInsertAsLast(SLList *l, void *data) {
    SLNode *newnode, *last;
    if (l != NULL) {
        newnode = (SLNode *)malloc(sizeof(SLNode));
        if (newnode != NULL) {
            newnode->data = data;
            newnode->next = NULL;
            if (l->first != NULL) {
                last = l->first;
                while (last->next != NULL) {
                    last = last->next;
                }
                last->next = newnode;
            } else {
                l->first = newnode;
            }
            return TRUE;
        }
    }
    return FALSE;
}

void *sllRemoveFirst(SLList *l) {
    SLNode *first;
    void *data;
    if(l != NULL) {
        if (l->first != NULL) {
            first = l->first;
            data = first->data;
            l->first = first->next;
            free(first);
            return data;
        }
    }
    return NULL;
}

void *sllGetFirst(SLList *l) {
    if (l != NULL) {
        if (l->first != NULL) {
            l->cur = l->first;
            return l->first->data;
        }
    }
    return NULL;
}

void *sllGetNext(SLList *l) {
    SLNode *cur;
    if (l != NULL) {
        cur = l->cur;
        if (cur->next != NULL) {
            l->cur = cur->next;
            return l->cur->data;
        }
    }
    return NULL;
}

void *sllGetLast(SLList *l) {
    SLNode *cur;
    if (l != NULL) {
        if (l->first != NULL) {
            cur = l->first;
            while (cur->next != NULL) {
                cur = cur->next;
            }
            return cur->data;
        }
    }
    return NULL;
}

int sllGetNumElms(SLList *l) {
    SLNode *cur;
    int n;
    if (l != NULL) {
        if (l->first != NULL) {
            cur = l->first;
            n = 1;
            while (cur->next != NULL) {
                cur = cur->next;
                n++;
            }
            return n;
        }
        return 0;
    }
    return -1;
}

int sllInsertElmAsPesimo(SLList *l, void *data, int p) {
    SLNode *newnode, *cur;
    int n = 0;
    if (l != NULL) {
        newnode = (SLNode *)malloc(sizeof(SLNode));
        if (newnode != NULL) {
            newnode->data = data;
            if (l->first != NULL) {
                if (p > 0) {
                    cur = l->first;
                    while (cur->next != NULL && n < p-1) {
                        cur = cur->next;
                        n++;
                    }
                    newnode->next = cur->next;
                    cur->next = newnode;
                } else {
                    newnode->next = l->first;
                    l->first = newnode;
                }
            } else {
                newnode->next = l->first;
                l->first = newnode;
            }
            return TRUE;
        }
    }
    return FALSE;
}

void *sllGetDataSpec(SLList *l, void *key, int(*cmp)(void *, void *)) {
    SLNode *spec;
    int stat;
    if (l != NULL) {
        if (l->first != NULL) {
            spec = l->first;
            stat = cmp(key, spec->data);
            while (stat != TRUE && spec->next != NULL ) {
                spec = spec->next;
                stat = cmp(key, spec->data);
            }
            if (stat == TRUE) {
                return spec->data;
            }
        }
    }
    return NULL;
}

int sllInsertAfterSpec(SLList *l, void *key, void *data, int(*cmp)(void *, void *)) {
    SLNode *newnode, *spec;
    int stat;
    if (l != NULL) {
        if (l->first != NULL) {
            spec = l->first;
            stat = cmp(key, spec->data);
            while (spec->next != NULL && stat != TRUE) {
                spec = spec->next;
                stat = cmp(key, spec->data);
            }
            if (stat == TRUE) {
                newnode = (SLNode *)malloc(sizeof(SLNode));
                if (newnode != NULL) {
                    newnode->data = data;
                    newnode->next = spec->next;
                    spec->next = newnode;
                    return TRUE;
                }
            }
        }
    }
    return FALSE;
}

int sllInsertBeforeSpec(SLList *l, void *key, void *data, int (*cmp)(void *, void *)) {
    SLNode *newnode, *spec, *prev;
    int stat;
    if (l != NULL) {
        if (l->first != NULL) {
            prev = NULL;
            spec = l->first;
            stat = cmp(key, spec->data);
            while (spec != NULL && stat != TRUE) {
                prev = spec;
                spec = spec->next;
                stat = cmp(key, spec->data);
            }
            if (stat == TRUE) {
                newnode = (SLNode *)malloc(sizeof(SLNode));
                if (newnode != NULL) {
                    newnode->data = data;
                    newnode->next = spec;
                    if (prev != NULL) {
                        prev->next = newnode;
                    } else {
                        l->first = newnode;
                    }
                    return TRUE;
                }
            }
        }
    }
    return FALSE;
}

void *sllRemoveSpec(SLList *l, void *key, int(*cmp)(void*, void *)) {
    SLNode *cur, *prev;
    void *data;
    int stat;
    if (l != NULL) {
        if (l->first != NULL) {
            cur = l->first;
            prev = NULL;
            stat = cmp(key, cur->data);
            while (cur->next != NULL && stat != TRUE) {
                prev = cur;
                cur = cur->next;
                stat = cmp(key, cur->data);
            }
            if (stat == TRUE) {
                data = cur->data;
                if (prev == NULL) {
                    l->first = cur->next;
                } else {
                    prev->next = cur->next;
                }
                free(cur);
                return data;
            }
        }
    }
    return NULL;
}

void *sllRemoveAfterSpec(SLList *l, void *key, int (*cmp)(void *, void *)) {
    SLNode *spec, *del;
    void *data;
    int stat;
    if (l != NULL) {
        if (l->first != NULL) {
            spec = l->first;
            stat = cmp(key, spec->data);
            while (spec->next != NULL && stat != TRUE) {
                spec = spec->next;
                stat = cmp(key, spec->data);
            }
            if (stat == TRUE) {
                del = spec->next;
                if (del != NULL) {
                    data = del->data;
                    spec->next = del->next;
                    free(del);
                    return data;
                }
            }
        }
    }
    return NULL;
}