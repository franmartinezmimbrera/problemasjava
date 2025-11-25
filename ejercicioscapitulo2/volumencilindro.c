/* fichero volumencilindro.c */
/* Este programa calcula el volumen de un cilindro*/
#include <stdio.h>
#include <stdlib.h>

#define PI_CONST 3.141592653589793

int main(void) {
    
    double D, H, R, V;
    int valor_leido;

    printf ("Introduzca el diámetro, en metros: "); 
    valor_leido = scanf ("%lf", &D);
    
    if (valor_leido != 1 || D <= 0.0) {
        printf("Error: El diámetro debe ser un número positivo.\n");
        return EXIT_FAILURE;
    }
    
    printf ("Introduzca la altura, en metros: "); 
    valor_leido = scanf ("%lf", &H);

    if (valor_leido != 1 || H <= 0.0) {
        printf("Error: La altura debe ser un número positivo.\n");
        return EXIT_FAILURE;
    }
    
    R = D / 2.0; 
    V = PI_CONST * R * R * H; 
    printf ("El volumen del cilindro es de %.4lf metros cúbicos.\n", V);
    
    return EXIT_SUCCESS;
}