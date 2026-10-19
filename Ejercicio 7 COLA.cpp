#include <stdio.h>
#include <stdlib.h>


typedef struct Nodo {
    int dato;
    struct Nodo *siguiente;
} Nodo;


void inicializar(Nodo **frente, Nodo **final) {
    *frente = NULL;
    *final = NULL;
}

int estaVacia(Nodo *frente) {
    return frente == NULL;
}


void encolar(Nodo **frente, Nodo **final, int valor) {
    Nodo *nuevo = (Nodo *)malloc(sizeof(Nodo));
    if (nuevo == NULL) {
        printf("Error: no hay memoria disponible.\n");
        return;
    }
    nuevo->dato = valor;
    nuevo->siguiente = NULL;        

    if (estaVacia(*frente)) {
        *frente = nuevo;            
    } else {
        (*final)->siguiente = nuevo; 
    }
    *final = nuevo;                 
    printf("Elemento %d encolado.\n", valor);
}


int desencolar(Nodo **frente, Nodo **final, int *valor) {
    if (estaVacia(*frente)) {
        printf("Error: la cola esta vacia.\n");
        return 0;
    }
    Nodo *aux = *frente;
    *valor = aux->dato;
    *frente = aux->siguiente;       

    if (*frente == NULL) {
        *final = NULL;              
    }
    free(aux);                     
    return 1;
}


int verFrente(Nodo *frente, int *valor) {
    if (estaVacia(frente)) {
        printf("La cola esta vacia.\n");
        return 0;
    }
    *valor = frente->dato;
    return 1;
}

void mostrar(Nodo *frente) {
    if (estaVacia(frente)) {
        printf("La cola esta vacia.\n");
        return;
    }
    printf("Cola (frente -> final): ");
    Nodo *actual = frente;
    while (actual != NULL) {
        printf("%d ", actual->dato);
        actual = actual->siguiente;
    }
    printf("\n");
}


void liberar(Nodo **frente, Nodo **final) {
    Nodo *aux;
    while (*frente != NULL) {
        aux = *frente;
        *frente = aux->siguiente;
        free(aux);
    }
    *final = NULL;
}

int main() {
    Nodo *frente, *final;
    inicializar(&frente, &final);
    int opcion, valor;

    do {
        printf("\n------ MENU COLA ------ \n");
        printf("1. Encolar\n");
        printf("2. Desencolar\n");
        printf("3. Ver frente\n");
        printf("4. Mostrar cola\n");
        printf("5. Salir\n");
        printf("Opcion: ");
        scanf("%d", &opcion);

        switch (opcion) {
            case 1:
                printf("Valor a encolar: ");
                scanf("%d", &valor);
                encolar(&frente, &final, valor);
                break;
            case 2:
                if (desencolar(&frente, &final, &valor))
                    printf("Elemento desencolado: %d\n", valor);
                break;
            case 3:
                if (verFrente(frente, &valor))
                    printf("Elemento al frente: %d\n", valor);
                break;
            case 4:
                mostrar(frente);
                break;
            case 5:
                liberar(&frente, &final);
                printf("Memoria liberada. Saliendo...\n");
                break;
            default:
                printf("Opcion invalida.\n");
        }
    } while (opcion != 5);

    return 0;
}
