#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Nodo {
    int dato;
    struct Nodo* siguiente;
} Nodo;

typedef struct {
    Nodo* cabeza;
    int tamano;
} Lista;

void inicializarLista(Lista* lista) {
    lista->cabeza = NULL;
    lista->tamano = 0;
}

bool estaVacia(Lista* lista) {
    return lista->cabeza == NULL;
}

void imprimirEstadoDeLaLista(Lista* lista) {
    printf("\n--- ESTADO ACTUAL DE LA LISTA ---\n");
    
    if (estaVacia(lista)) {
        printf("[ La lista esta completamente vacia ]\n");
    } else {
        printf("INICIO -> ");
        
        Nodo* actual = lista->cabeza;
        while (actual != NULL) {
            printf("[%d] -> ", actual->dato);
            actual = actual->siguiente;
        }
        
        printf("NULL\n");
    }
    printf("---------------------------------\n\n");
}

int main() {
    Lista miLista;
    inicializarLista(&miLista);
    
    int opcion, valor;

    do {
        imprimirEstadoDeLaLista(&miLista);

        printf("--- MENU DE OPERACIONES DE LISTA EN C ---\n");
        printf("1. Agregar un elemento al final de la lista\n");
        printf("2. Buscar y eliminar un elemento especifico\n");
        printf("3. Ver detalles de la lista\n");
        printf("4. Salir del programa\n");
        printf("Elige una opcion: ");
        
        if (scanf("%d", &opcion) != 1) {
            while(getchar() != '\n');
            opcion = 0;
        }

        switch(opcion) {
            case 1:
                printf("Ingresa el numero a insertar: ");
                scanf("%d", &valor);
                
                Nodo* nuevoNodo = (Nodo*)malloc(sizeof(Nodo));
                nuevoNodo->dato = valor;
                nuevoNodo->siguiente = NULL;
                
                if (estaVacia(&miLista)) {
                    miLista.cabeza = nuevoNodo;
                } else {
                    Nodo* actual = miLista.cabeza;
                    while (actual->siguiente != NULL) {
                        actual = actual->siguiente;
                    }
                    actual->siguiente = nuevoNodo;
                }
                miLista.tamano++;
                printf("-> El elemento %d fue agregado a la lista.\n", valor);
                break;
                
            case 2:
                if (!estaVacia(&miLista)) {
                    printf("Ingresa el numero exacto que deseas eliminar: ");
                    scanf("%d", &valor);
                    
                    Nodo* actual = miLista.cabeza;
                    Nodo* anterior = NULL;
                    bool encontrado = false;
                    
                    while (actual != NULL) {
                        if (actual->dato == valor) {
                            encontrado = true;
                            break;
                        }
                        anterior = actual;
                        actual = actual->siguiente;
                    }
                    
                    if (encontrado) {
                        if (anterior == NULL) {
                            miLista.cabeza = actual->siguiente;
                        } else {
                            anterior->siguiente = actual->siguiente;
                        }
                        free(actual);
                        miLista.tamano--;
                        printf("-> El elemento [%d] fue eliminado y su memoria liberada.\n", valor);
                    } else {
                        printf("-> ERROR: El numero %d no se encuentra en la lista.\n", valor);
                    }
                } else {
                    printf("-> ERROR: La lista esta vacia.\n");
                }
                break;
                
            case 3:
                if (!estaVacia(&miLista)) {
                    printf("\n--- DETALLES DE LA LISTA ---\n");
                    printf("Elemento en el inicio: %d\n", miLista.cabeza->dato);
                    printf("Total de nodos en memoria: %d\n", miLista.tamano);
                    printf("----------------------------\n\n");
                } else {
                    printf("-> La lista esta vacia, no hay detalles.\n");
                }
                break;
                
            case 4:
                printf("-> Liberando toda la memoria dinamica y saliendo...\n");
                while (!estaVacia(&miLista)) {
                    Nodo* temporal = miLista.cabeza;
                    miLista.cabeza = miLista.cabeza->siguiente;
                    free(temporal);
                }
                break;
                
            default:
                printf("-> Opcion no valida. Intenta de nuevo.\n");
        }
        
        printf("\nPresiona Enter para continuar...");
        while(getchar() != '\n');
        getchar();
        
        printf("\n\n\n");

    } while (opcion != 4);

    return 0;
}
