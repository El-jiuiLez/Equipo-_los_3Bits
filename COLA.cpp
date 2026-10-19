#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Nodo {
    int dato;
    struct Nodo* siguiente;
} Nodo;

typedef struct {
    Nodo* frente;
    Nodo* final;
    int tamano;
} Cola;

void inicializarCola(Cola* cola) {
    cola->frente = NULL;
    cola->final = NULL;
    cola->tamano = 0;
}

bool estaVacia(Cola* cola) {
    return cola->frente == NULL;
}

void imprimirEstadoDeLaCola(Cola* cola) {
    printf("\n--- ESTADO ACTUAL DE LA COLA ---\n");
    
    if (estaVacia(cola)) {
        printf("[ La cola esta completamente vacia ]\n");
    } else {
        printf("PROXIMO A SALIR [FRENTE] -> ");
        
        Nodo* actual = cola->frente;
        while (actual != NULL) {
            printf("|%d| ", actual->dato);
            actual = actual->siguiente;
        }
        
        printf("<- ULTIMO EN ENTRAR [FINAL]\n");
    }
    printf("--------------------------------\n\n");
}

int main() {
    Cola miCola;
    inicializarCola(&miCola);
    
    int opcion, numeroAEncolar;

    do {
        imprimirEstadoDeLaCola(&miCola);

        printf("--- MENU DE OPERACIONES EN C ---\n");
        printf("1. Ingresar un nuevo elemento al final (malloc)\n");
        printf("2. Atender/Sacar el primer elemento (free)\n");
        printf("3. Ver detalles de la cola (Frente, Final y Tamano)\n");
        printf("4. Salir del programa\n");
        printf("Elige una opcion: ");
        
        if (scanf("%d", &opcion) != 1) {
            while(getchar() != '\n');
            opcion = 0;
        }

        switch(opcion) {
            case 1:
                printf("Ingresa el numero que formara fila: ");
                scanf("%d", &numeroAEncolar);
                
                printf("-> Reservando memoria y formando el numero %d...\n", numeroAEncolar);
                
                Nodo* nuevoNodo = (Nodo*)malloc(sizeof(Nodo));
                nuevoNodo->dato = numeroAEncolar;
                nuevoNodo->siguiente = NULL;
                
                if (estaVacia(&miCola)) {
                    miCola.frente = nuevoNodo;
                    miCola.final = nuevoNodo;
                } else {
                    miCola.final->siguiente = nuevoNodo;
                    miCola.final = nuevoNodo;
                }
                miCola.tamano++;
                break;
                
            case 2:
                if (!estaVacia(&miCola)) {
                    Nodo* nodoASalir = miCola.frente;
                    int elementoQueSale = nodoASalir->dato;
                    
                    printf("-> Desencolando... El elemento [%d] sale de la fila.\n", elementoQueSale);
                    
                    miCola.frente = miCola.frente->siguiente; 
                    
                    if (miCola.frente == NULL) {
                        miCola.final = NULL;
                    }
                    
                    free(nodoASalir); 
                    miCola.tamano--;
                    printf("-> Memoria liberada correctamente.\n");
                } else {
                    printf("-> ERROR: No puedes sacar a nadie, la cola ya esta vacia.\n");
                }
                break;
                
            case 3:
                if (!estaVacia(&miCola)) {
                    printf("\n--- DETALLES EXACTOS ---\n");
                    printf("Elemento al frente (siguiente en salir): %d\n", miCola.frente->dato);
                    printf("Elemento al final (el ultimo en llegar): %d\n", miCola.final->dato);
                    printf("Total de elementos formados: %d\n", miCola.tamano);
                    printf("------------------------\n\n");
                } else {
                    printf("-> La cola esta vacia, no hay detalles que mostrar.\n");
                }
                break;
                
            case 4:
                printf("-> Vaciando la memoria restante y saliendo del programa...\n");
                while (!estaVacia(&miCola)) {
                    Nodo* temporal = miCola.frente;
                    miCola.frente = miCola.frente->siguiente;
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
