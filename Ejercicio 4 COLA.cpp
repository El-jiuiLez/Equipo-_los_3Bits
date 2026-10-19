#include <stdio.h>
#include <stdlib.h>

#define MAX 5

typedef struct {
    int datos[MAX];
    int frente;     
    int final;      
    int cantidad;   
} Cola;

void inicializar(Cola *c) {
    c->frente = 0;
    c->final = 0;
    c->cantidad = 0;
}

int estaVacia(Cola *c) {
    return c->cantidad == 0;
}

int estaLlena(Cola *c) {
    return c->cantidad == MAX;
}

void encolar(Cola *c, int valor) {
    if (estaLlena(c)) {
        printf("Error: la cola esta llena (desbordamiento).\n");
        return;
    }
    c->datos[c->final] = valor;
    c->final = (c->final + 1) % MAX;
    c->cantidad++;
    printf("Elemento %d encolado.\n", valor);
}

int desencolar(Cola *c) {
    if (estaVacia(c)) {
        printf("Error: la cola esta vacia.\n");
        return -1;
    }
    int valor = c->datos[c->frente];
    c->frente = (c->frente + 1) % MAX;
    c->cantidad--;
    return valor;
}

int verFrente(Cola *c) {
    if (estaVacia(c)) {
        printf("La cola esta vacia.\n");
        return -1;
    }
    return c->datos[c->frente];
}

void mostrar(Cola *c) {
    if (estaVacia(c)) {
        printf("La cola esta vacia.\n");
        return;
    }
    printf("Cola (frente -> final): ");
    int i = c->frente;
    for (int k = 0; k < c->cantidad; k++) {
        printf("%d ", c->datos[i]);
        i = (i + 1) % MAX;
    }
    printf("\n");
}

int main() {
    Cola cola;
    inicializar(&cola);
    int opcion, valor;

    do {
        printf("\n------ MENU COLA -------\n");
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
                encolar(&cola, valor);
                break;
            case 2:
                valor = desencolar(&cola);
                if (valor != -1)
                    printf("Elemento desencolado: %d\n", valor);
                break;
            case 3:
                valor = verFrente(&cola);
                if (valor != -1)
                    printf("Elemento al frente: %d\n", valor);
                break;
            case 4:
                mostrar(&cola);
                break;
            case 5:
                printf("Saliendo...\n");
                break;
            default:
                printf("Opcion invalida.\n");
        }
    } while (opcion != 5);

    return 0;
}
