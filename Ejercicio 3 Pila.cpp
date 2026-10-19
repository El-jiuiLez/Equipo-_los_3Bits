#include <stdio.h>
#define MAX 5            
 
typedef struct {
    int datos[MAX];      
    int tope;           
} Pila;
 

void inicializar(Pila *p) {
    p->tope = -1;
}
 
int pilaVacia(Pila *p) {
    return p->tope == -1;
}
 
int pilaLlena(Pila *p) {
    return p->tope == MAX - 1;
}
 
void push(Pila *p, int valor) {
    if (pilaLlena(p)) {
        printf("Error: desbordamiento (overflow), la pila esta llena.\n");
        return;
    }
    p->datos[++(p->tope)] = valor;
    printf("Se inserto %d en la pila.\n", valor);
}
 

int pop(Pila *p, int *valor) {
    if (pilaVacia(p)) {
        printf("Error: subdesbordamiento (underflow), la pila esta vacia.\n");
        return 0;
    }
    *valor = p->datos[(p->tope)--];
    return 1;
}
 
int peek(Pila *p, int *valor) {
    if (pilaVacia(p)) {
        printf("La pila esta vacia.\n");
        return 0;
    }
    *valor = p->datos[p->tope];
    return 1;
}
 

void mostrar(Pila *p) {
    if (pilaVacia(p)) {
        printf("La pila esta vacia.\n");
        return;
    }
    printf("\n--- PILA (tope -> base) ---\n");
    for (int i = p->tope; i >= 0; i--) {
        printf("| %d |%s\n", p->datos[i], i == p->tope ? "  <- tope" : "");
    }
    printf("---------------------------\n");
}
 
int main(void) {
    Pila pila;
    int opcion, valor;
 
    inicializar(&pila);
 
    do {
        printf("\n===== MENU PILA (max %d) =====\n", MAX);
        printf("1. Push (insertar)\n");
        printf("2. Pop (eliminar)\n");
        printf("3. Peek (ver tope)\n");
        printf("4. Mostrar pila\n");
        printf("5. Salir\n");
        printf("Opcion: ");
        scanf("%d", &opcion);
 
        switch (opcion) {
            case 1:
                printf("Valor a insertar: ");
                scanf("%d", &valor);
                push(&pila, valor);
                break;
            case 2:
                if (pop(&pila, &valor))
                    printf("Elemento eliminado: %d\n", valor);
                break;
            case 3:
                if (peek(&pila, &valor))
                    printf("Elemento en el tope: %d\n", valor);
                break;
            case 4:
                mostrar(&pila);
                break;
            case 5:
                printf("Saliendo...\n");
                break;
            default:
                printf("Opcion no valida.\n");
        }
    } while (opcion != 5);
 
    return 0;
}
 
