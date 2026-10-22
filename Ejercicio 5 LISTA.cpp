#include <stdio.h>
#include <stdlib.h>

#define MAX 10

typedef struct {
    int datos[MAX];
    int cantidad;  
} Lista;

void inicializar(Lista *l) {
    l->cantidad = 0;
}

int estaVacia(Lista *l) {
    return l->cantidad == 0;
}

int estaLlena(Lista *l) {
    return l->cantidad == MAX;
}


void insertarFinal(Lista *l, int valor) {
    if (estaLlena(l)) {
        printf("Error: la lista esta llena.\n");
        return;
    }
    l->datos[l->cantidad] = valor;
    l->cantidad++;
    printf("Elemento %d insertado al final.\n", valor);
}


void insertarEnPosicion(Lista *l, int valor, int pos) {
    if (estaLlena(l)) {
        printf("Error: la lista esta llena.\n");
        return;
    }
    if (pos < 0 || pos > l->cantidad) {
        printf("Error: posicion invalida.\n");
        return;
    }
    for (int i = l->cantidad; i > pos; i--) {
        l->datos[i] = l->datos[i - 1];
    }
    l->datos[pos] = valor;
    l->cantidad++;
    printf("Elemento %d insertado en la posicion %d.\n", valor, pos);
}

int eliminarEnPosicion(Lista *l, int pos) {
    if (estaVacia(l)) {
        printf("Error: la lista esta vacia.\n");
        return -1;
    }
    if (pos < 0 || pos >= l->cantidad) {
        printf("Error: posicion invalida.\n");
        return -1;
    }
    int valor = l->datos[pos];
    for (int i = pos; i < l->cantidad - 1; i++) {
        l->datos[i] = l->datos[i + 1];
    }
    l->cantidad--;
    return valor;
}


int buscar(Lista *l, int valor) {
    for (int i = 0; i < l->cantidad; i++) {
        if (l->datos[i] == valor)
            return i;
    }
    return -1;
}

void mostrar(Lista *l) {
    if (estaVacia(l)) {
        printf("La lista esta vacia.\n");
        return;
    }
    printf("Lista: ");
    for (int i = 0; i < l->cantidad; i++) {
        printf("[%d]=%d  ", i, l->datos[i]);
    }
    printf("\n");
}

int main() {
    Lista lista;
    inicializar(&lista);
    int opcion, valor, pos, n;

    do {
        printf("\n===== MENU LISTA =====\n");
        printf("1. Insertar al final\n");
        printf("2. Insertar en posicion\n");
        printf("3. Eliminar en posicion\n");
        printf("4. Buscar valor\n");
        printf("5. Mostrar lista\n");
        printf("6. Insertar varios al final\n");
        printf("7. Salir\n");
        printf("Opcion: ");
        scanf("%d", &opcion);

        switch (opcion) {
            case 1:
                printf("Valor a insertar: ");
                scanf("%d", &valor);
                insertarFinal(&lista, valor);
                break;
            case 2:
                printf("Valor a insertar: ");
                scanf("%d", &valor);
                printf("Posicion (0 a %d): ", lista.cantidad);
                scanf("%d", &pos);
                insertarEnPosicion(&lista, valor, pos);
                break;
            case 3:
                printf("Posicion a eliminar: ");
                scanf("%d", &pos);
                valor = eliminarEnPosicion(&lista, pos);
                if (valor != -1)
                    printf("Elemento eliminado: %d\n", valor);
                break;
            case 4:
                printf("Valor a buscar: ");
                scanf("%d", &valor);
                pos = buscar(&lista, valor);
                if (pos != -1)
                    printf("El valor %d esta en la posicion %d.\n", valor, pos);
                else
                    printf("El valor %d no se encontro.\n", valor);
                break;
            case 5:
                mostrar(&lista);
                break;
            case 7:
                printf("Saliendo...\n");
                break;
            case 6:
                printf("Cuantos elementos desea insertar al final? ");
                scanf("%d", &n);
                if (n < 1) {
                    printf("Cantidad invalida.\n");
                    break;
                }
                for (int i = 0; i < n; i++) {
                    if (estaLlena(&lista)) {
                        printf("Lista llena: solo se agregaron %d elementos.\n", i);
                        break;
                    }
                    printf("Valor %d de %d: ", i + 1, n);
                    scanf("%d", &valor);
                    insertarFinal(&lista, valor);
                }
                break;
            default:
                printf("Opcion invalida.\n");
        }
    } while (opcion != 7);

    return 0;
}
