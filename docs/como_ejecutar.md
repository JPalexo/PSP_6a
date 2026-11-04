# Program 6 - como ejecutarlo

Siempre parado en la carpeta `PSP\Program6`.

## Compilar

```bat
mvn -q clean compile
```

## Una prueba a la vez (1, 2 o 3)

```bat
java -cp target/classes App 2
```

Sale:

```
Test 2  p=0.450  dof=15  x=1.75305
```

Sin numero corre las tres: `java -cp target/classes App`.
Cada prueba deja su resultado en `data/out_testN.txt`.

## Todas las pruebas JUnit

```bat
mvn test
```

Debe decir `Tests run: 29, Failures: 0, Errors: 0`.

El mensaje "Ocurrio un error al escribir el archivo" que sale en medio NO es una falla:
es una prueba que provoca el error a proposito para probar el `catch` de `Output`.

## Cobertura al 100 %

```bat
mvn verify
```

Debe decir `All coverage checks have been met` y `BUILD SUCCESS`.
El reporte queda en `target/site/jacoco/index.html`.

## Clases

| Clase | Que hace |
|---|---|
| `App` | `main`: corre la prueba 1, 2 o 3, o las tres |
| `Logic` | Tiene los 3 casos; integra con Simpson hasta que el error sea menor a 0.00001 |
| `SearchX` | Busca el x: suma o resta d y parte d a la mitad cuando el error cambia de signo |
| `SimpsonIntegration` | Una pasada de Simpson (igual que en el 5a) |
| `GammaFunction` | Gamma para enteros y para mitades |
| `Output` | Escribe el resultado en un archivo |

## Los 3 tests (Tabla 1 del 6a)

| Test | p | dof | x esperado | x del programa |
|---|---|---|---|---|
| 1 | 0.20 | 6 | 0.55338 | 0.55341 |
| 2 | 0.45 | 15 | 1.75305 | 1.75305 |
| 3 | 0.495 | 4 | 4.60409 | 4.60156 |

El test 3 no da igual porque ahi la curva ya casi no sube: con el error de 0.00001
del PDF, un error chico en p deja un error grande en x.

## Jenkins

El pipeline (`Jenkinsfile`) tiene tres etapas: Build compila, Test corre
`mvn verify` y Deliver corre el programa. Se ve en `http://localhost:8080`.
