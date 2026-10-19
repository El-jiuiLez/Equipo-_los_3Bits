#include <iostream>
#include <stack>

using namespace std;

int main() {
    // Declaración de una pila dinámica que almacena enteros
    stack<int> miPila;
    int opcion, valor;

    do {
        cout << "\n--- Manejo de Pila Dinamica (std::stack) ---" << endl;
        cout << "1. Apilar elemento (Push)" << endl;
        cout << "2. Desapilar elemento (Pop)" << endl;
        cout << "3. Ver elemento en el tope (Top)" << endl;
        cout << "4. Ver cantidad de elementos (Size)" << endl;
        cout << "5. Verificar si esta vacia (Empty)" << endl;
        cout << "6. Salir" << endl;
        cout << "Elige una opcion: ";
        cin >> opcion;

        switch(opcion) {
            case 1:
                cout << "Ingresa un numero entero para apilar: ";
                cin >> valor;
                miPila.push(valor); // Agrega el elemento a la cima
                cout << "Elemento " << valor << " agregado a la pila." << endl;
                break;
                
            case 2:
                if (!miPila.empty()) {
                    cout << "Elemento " << miPila.top() << " eliminado de la pila." << endl;
                    miPila.pop(); // Elimina el elemento de la cima (no devuelve el valor)
                } else {
                    cout << "Error: La pila ya esta vacia." << endl;
                }
                break;
                
            case 3:
                if (!miPila.empty()) {
                    cout << "El elemento en el tope de la pila es: " << miPila.top() << endl;
                } else {
                    cout << "La pila esta vacia. No hay tope." << endl;
                }
                break;
                
            case 4:
                cout << "La pila contiene " << miPila.size() << " elemento(s)." << endl;
                break;
                
            case 5:
                if (miPila.empty()) {
                    cout << "La pila se encuentra vacia." << endl;
                } else {
                    cout << "La pila NO esta vacia." << endl;
                }
                break;
                
            case 6:
                cout << "Saliendo del programa..." << endl;
                break;
                
            default:
                cout << "Opcion invalida. Por favor, intenta de nuevo." << endl;
        }
    } while (opcion != 6);

    return 0;
}
