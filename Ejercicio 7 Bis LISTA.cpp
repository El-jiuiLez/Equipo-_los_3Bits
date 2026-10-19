#include <stdio.h>
#include <stdlib.h>

typedef struct Nodo {
    int dato;
    struct Nodo *siguiente;
} Nodo;

void inicializar(Nodo **cabeza) {
    *cabeza = NULL;
}

int estaVacia(Nodo *cabeza) {
    return cabeza == NULL;
}

Nodo *crearNodo(int valor) {
    Nodo *nuevo = (Nodo *)malloc(sizeof(Nodo));
    if (nuevo == NULL) {
        printf("Error: no hay memoria disponible.\n");
        return NULL;
    }
    nuevo->dato = valor;
    nuevo->siguiente = NULL;
    return nuevo;
}

void insertarInicio(Nodo **cabeza, int valor) {
    Nodo *nuevo = crearNodo(valor);
    if (nuevo == NULL) return;

    nuevo->siguiente = *cabeza;   
    *cabeza = nuevo;              
    printf("Elemento %d insertado al inicio.\n", valor);
}


void insertarFinal(Nodo **cabeza, int valor) {
    Nodo *nuevo = crearNodo(valor);
    if (nuevo == NULL) return;

    if (estaVacia(*cabeza)) {
        *cabeza = nuevo;          
    } else {
        Nodo *actual = *cabeza;
        while (actual->siguiente != NULL) {
            actual = actual->siguiente;
        }
        actual->siguiente = nuevo;
    }
    printf("Elemento %d insertado al final.\n", valor);
}

void insertarEnPosicion(Nodo **cabeza, int valor, int pos) {
    if (pos < 0) {
        printf("Error: posicion invalida.\n");
        return;
    }
    if (pos == 0) {
        insertarInicio(cabeza, valor);
        return;
    }

    Nodo *actual = *cabeza;
    for (int i = 0; i < pos - 1 && actual != NULL; i++) {
        actual = actual->siguiente;
    }
    if (actual == NULL) {
        printf("Error: posicion fuera de rango.\n");
        return;
    }

    Nodo *nuevo = crearNodo(valor);
    if (nuevo == NULL) return;

    nuevo->siguiente = actual->siguiente;
    actual->siguiente = nuevo;
    printf("Elemento %d insertado en la posicion %d.\n", valor, pos);
}

int eliminarEnPosicion(Nodo **cabeza, int pos, int *valor) {
    if (estaVacia(*cabeza)) {
        printf("Error: la lista esta vacia.\n");
        return 0;
    }
    if (pos < 0) {
        printf("Error: posicion invalida.\n");
        return 0;
    }

    Nodo *aux;
    if (pos == 0) {
        aux = *cabeza;
        *cabeza = aux->siguiente;     
    } else {
        Nodo *anterior = *cabeza;
        for (int i = 0; i < pos - 1 && anterior->siguiente != NULL; i++) {
            anterior = anterior->siguiente;
        }
        if (anterior->siguiente == NULL) {
            printf("Error: posicion fuera de rango.\n");
            return 0;
        }
        aux = anterior->siguiente;
        anterior->siguiente = aux->siguiente;  
    }
    *valor = aux->dato;
    free(aux);
    return 1;
}

int buscar(Nodo *cabeza, int valor) {
    int pos = 0;
    Nodo *actual = cabeza;
    while (actual != NULL) {
        if (actual->dato == valor)
            return pos;
        actual = actual->siguiente;
        pos++;
    }
    return -1;
}

int longitud(Nodo *cabeza) {
    int n = 0;
    while (cabeza != NULL) {
        n++;
        cabeza = cabeza->siguiente;
    }
    return n;
}

void mostrar(Nodo *cabeza) {
    if (estaVacia(cabeza)) {
        printf("La lista esta vacia.\n");
        return;
    }
    printf("Lista: ");
    Nodo *actual = cabeza;
    while (actual != NULL) {
        printf("[%d] -> ", actual->dato);
        actual = actual->siguiente;
    }
    printf("NULL\n");
}

void liberar(Nodo **cabeza) {
    Nodo *aux;
    while (*cabeza != NULL) {
        aux = *cabeza;
        *cabeza = aux->siguiente;
        free(aux);
    }
}

int main() {
    Nodo *lista;
    inicializar(&lista);
    int opcion, valor, pos;

    do {
        printf("\n---------- MENU LISTA DINAMICA ---------\n");
        printf("1. Insertar al inicio\n");
        printf("2. Insertar al final\n");
        printf("3. Insertar en posicion\n");
        printf("4. Eliminar en posicion\n");
        printf("5. Buscar valor\n");
        printf("6. Mostrar lista\n");
        printf("7. Salir\n");
        printf("Opcion: ");
        scanf("%d", &opcion);

        switch (opcion) {
            case 1:
                printf("Valor a insertar: ");
                scanf("%d", &valor);
                insertarInicio(&lista, valor);
                break;
            case 2:
                printf("Valor a insertar: ");
                scanf("%d", &valor);
                insertarFinal(&lista, valor);
                break;
            case 3:
                printf("Valor a insertar: ");
                scanf("%d", &valor);
                printf("Posicion (0 a %d): ", longitud(lista));
                scanf("%d", &pos);
                insertarEnPosicion(&lista, valor, pos);
                break;
            case 4:
                printf("Posicion a eliminar: ");
                scanf("%d", &pos);
                if (eliminarEnPosicion(&lista, pos, &valor))
                    printf("Elemento eliminado: %d\n", valor);
                break;
            case 5:
                printf("Valor a buscar: ");
                scanf("%d", &valor);
                pos = buscar(lista, valor);
                if (pos != -1)
                    printf("El valor %d esta en la posicion %d.\n", valor, pos);
                else
                    printf("El valor %d no se encontro.\n", valor);
                break;
            case 6:
                mostrar(lista);
                break;
            case 7:
                liberar(&lista);
                printf("Memoria liberada. Saliendo...\n");
                break;
            default:
                printf("Opcion invalida.\n");
        }
    } while (opcion != 7);

    return 0;
}
