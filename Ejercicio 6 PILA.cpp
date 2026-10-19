#include <stdio.h>
#include <stdlib.h>

typedef struct Nodo {
    int dato;
    struct Nodo *siguiente;
} Nodo;


void inicializar(Nodo **cima) {
    *cima = NULL;
}

int estaVacia(Nodo *cima) {
    return cima == NULL;
}

void apilar(Nodo **cima, int valor) {
    Nodo *nuevo = (Nodo *)malloc(sizeof(Nodo));
    if (nuevo == NULL) {
        printf("Error: no hay memoria disponible.\n");
        return;
    }
    nuevo->dato = valor;
    nuevo->siguiente = *cima;   
    *cima = nuevo;              
    printf("Elemento %d apilado.\n", valor);
}


int desapilar(Nodo **cima, int *valor) {
    if (estaVacia(*cima)) {
        printf("Error: la pila esta vacia.\n");
        return 0;
    }
    Nodo *aux = *cima;
    *valor = aux->dato;
    *cima = aux->siguiente;     
    free(aux);                  
    return 1;
}

int verCima(Nodo *cima, int *valor) {
    if (estaVacia(cima)) {
        printf("La pila esta vacia.\n");
        return 0;
    }
    *valor = cima->dato;
    return 1;
}

void mostrar(Nodo *cima) {
    if (estaVacia(cima)) {
        printf("La pila esta vacia.\n");
        return;
    }
    printf("Pila (cima -> base):\n");
    Nodo *actual = cima;
    while (actual != NULL) {
        printf("  | %d |\n", actual->dato);
        actual = actual->siguiente;
    }
    printf("  -----\n");
}


void liberar(Nodo **cima) {
    Nodo *aux;
    while (*cima != NULL) {
        aux = *cima;
        *cima = aux->siguiente;
        free(aux);
    }
}

int main() {
    Nodo *pila;
    inicializar(&pila);
    int opcion, valor;

    do {
        printf("\n===== MENU PILA DINAMICA =====\n");
        printf("1. Apilar (push)\n");
        printf("2. Desapilar (pop)\n");
        printf("3. Ver cima\n");
        printf("4. Mostrar pila\n");
        printf("5. Salir\n");
        printf("Opcion: ");
        scanf("%d", &opcion);

        switch (opcion) {
            case 1:
                printf("Valor a apilar: ");
                scanf("%d", &valor);
                apilar(&pila, valor);
                break;
            case 2:
                if (desapilar(&pila, &valor))
                    printf("Elemento desapilado: %d\n", valor);
                break;
            case 3:
                if (verCima(pila, &valor))
                    printf("Elemento en la cima: %d\n", valor);
                break;
            case 4:
                mostrar(pila);
                break;
            case 5:
                liberar(&pila);
                printf("Memoria liberada. Saliendo...\n");
                break;
            default:
                printf("Opcion invalida.\n");
        }
    } while (opcion != 5);

    return 0;
}
