# Java_Votaciones

Este programa está desarrollado en Java y permite obtener información sobre las votaciones de partidos políticos de Cataluña mediante la aplicación de varios filtros. Los datos se importan desde un archivo CSV utilizando la clase ImportarCSV ubicada en el paquete controller.

# Requisitos
- Java JDK 11 o superior

# Instalación
1. Clonar el repositorio

```
git clone https://github.com/polanmarc/Java_Votaciones.git
```
```
cd Java_Votaciones
```
2. Compilar el proyecto
Navega a la raíz del proyecto y ejecuta el siguiente comando para compilar:
```
javac -d bin src/**/*.java
```
# Estructura del Proyecto
- controller: Contiene dos archivos principales:
  - **ImportarCSV**: Este archivo se encarga de importar los datos desde el CSV llamado *votacions.csv*.
  - **LeerCSV**: Sirve para comprobar si los datos se han importado correctamente.
- excepcion: Contiene excepciones personalizadas para hacer más comprensibles los posibles errores del programa.
- gui: Contiene todos los componentes de la interfaz gráfica del usuario (GUI) del programa. Estos componentes están organizados por secciones para facilitar la lectura y el entendimiento del código.
- model: Contiene todos los modelos necesarios para el funcionamiento del programa.

# Contacto
Para cualquier duda o sugerencia, por favor contacta a polanmarc19@gmail.com.

---

¡Gracias por utilizar mi programa para analizar las votaciones de partidos políticos en Cataluña!
