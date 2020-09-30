# Trabajo de Diseño de Compiladores I 2020

## Grupo 6

### Temas particulares

- **Enteros**: Constantes enteras con valores entre –2^(15) y 2^(15)–1. Estas constantes llevarán el sufijo“**_i**”.Se debe incorporar a la lista de palabras reservadas la palabra **INTEGER**.
- **Flotantes**: Números reales con signo y parte exponencial. El exponente comienza con la letra f (minúscula) y llevarásigno. La parte exponencial puede estar ausente. 
Ejemplos válidos: 1. | .6 | -1.2 | 3.f–5 | 2.f+34 | 2.5f-1 | 15. | 0.
Considerar el rango 1.17549435f-38 < x < 3.40282347f+38 unión -3.40282347f+38 < x < -1.17549435f-38  unión 0.0  
Se debe incorporar a la lista de palabras reservadas la palabra **FLOAT**.
- Incorporar a la lista de palabras reservadas laspalabras **WHILE** y **LOOP**.
- **Comentarios multilínea**: Comentarios que comiencen con “/%” y terminen con “%/” (estos comentarios pueden ocupar más de una línea).
- **Cadenas multilínea**: Cadenas de caracteres que comiencen y terminen con “ “” . Estas cadenas pueden ocupar más de una línea, y en dicho caso, al finalde cada línea, excepto la última,debe aparecer un guión “ -”. (En la Tabla de símbolos se guardará la cadena sin el guión,y sin el salto de línea).
- **Sin conversiones**: Se explicará y resolverá en trabajos prácticos 3/4.(Grupos 2, 3, 6, 10, 11, 12, 15, 17,18, 19).
