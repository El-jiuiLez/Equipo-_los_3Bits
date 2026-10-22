#include <stdio.h>
#include <stdlib.h>

typedef struct Auto {
    float precio;
    int anio;
    struct Auto *sig;      
} Auto;

typedef struct Persona {
    char nombre[30], ap[30], am[30];
    char genero;
    int edad;
    struct Persona *sig;   
} Persona;


Auto* agregarAuto(Auto *inicio, float precio, int anio) {
    Auto *nuevo = (Auto*) malloc(sizeof(Auto));
    nuevo->precio = precio;
    nuevo->anio = anio;
    nuevo->sig = NULL;
    if (inicio == NULL) return nuevo;
    Auto *aux = inicio;
    while (aux->sig != NULL) aux = aux->sig;
    aux->sig = nuevo;
    return inicio;
}

void mostrarAutos(Auto *inicio) {
    int i = 1;
    for (Auto *p = inicio; p != NULL; p = p->sig)
        printf("Auto %d -> Precio: %.2f | Anio: %d\n", i++, p->precio, p->anio);
}


Persona* agregarPersona(Persona *inicio) {
    Persona *nuevo = (Persona*) malloc(sizeof(Persona));
    printf("Nombre: ");   scanf("%29s", nuevo->nombre);
    printf("Apellido paterno: "); scanf("%29s", nuevo->ap);
    printf("Apellido materno: "); scanf("%29s", nuevo->am);
    printf("Sexo (Masculino/Femenino): ");     scanf(" %c", &nuevo->genero);
    printf("Edad: ");             scanf("%d", &nuevo->edad);
    nuevo->sig = NULL;
    if (inicio == NULL) return nuevo;
    Persona *aux = inicio;
    while (aux->sig != NULL) aux = aux->sig;
    aux->sig = nuevo;
    return inicio;
}

void mostrarPersonas(Persona *inicio) {
    int i = 1;
    for (Persona *p = inicio; p != NULL; p = p->sig)
        printf("Persona %d -> %s %s %s | Genero: %c | Edad: %d\n",
               i++, p->nombre, p->ap, p->am, p->genero, p->edad);
}

void liberarAutos(Auto *p)       { while (p) { Auto *t = p; p = p->sig; free(t); } }
void liberarPersonas(Persona *p) { while (p) { Persona *t = p; p = p->sig; free(t); } }

int main() {
    Auto *autos = NULL;
    Persona *personas = NULL;
    int op, anio;
    float precio;

    do {
        printf("\n1. Agregar auto\n2. Mostrar autos\n3. Agregar persona\n4. Mostrar personas\n0. Salir\nOpcion: ");
        scanf("%d", &op);
        switch (op) {
            case 1:
                printf("Precio: "); scanf("%f", &precio);
                printf("Anio: ");   scanf("%d", &anio);
                autos = agregarAuto(autos, precio, anio);
                break;
            case 2: mostrarAutos(autos); break;
            case 3: personas = agregarPersona(personas); break;
            case 4: mostrarPersonas(personas); break;
        }
    } while (op != 0);

    liberarAutos(autos);
    liberarPersonas(personas);
    return 0;
}
