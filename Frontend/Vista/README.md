# Frontend - Sistema de Parqueadero

Este repositorio contiene la interfaz de usuario para el sistema de gestión de parqueaderos. Está desarrollada con **React** y **Vite**, y se conecta con un backend en **Spring Boot** para manejar la autenticación, reservas, tickets y facturación.

## Stack Tecnológico

- **Framework:** [React](https://es.react.dev/)
- **Herramienta de Build y Servidor de Desarrollo:** [Vite](https://vitejs.dev/)
- **Linting:** [ESLint](https://eslint.org/) (configuración en `eslint.config.js`)

## Requisitos Previos

Asegúrate de tener instalado lo siguiente en tu máquina:

- **Node.js:** Versión **16** o superior, pero **menor a 21** (se recomienda usar la versión 18 o 20 para mayor estabilidad).
- **Gestor de paquetes:** `npm`, `yarn` o `pnpm`.
- **Backend:** El servidor de backend (Spring Boot) debe estar en ejecución, por defecto en `http://localhost:8080`.

## Configuración y Ejecución

Sigue estos pasos para levantar el proyecto en tu entorno local:

1.  **Clonar el repositorio:**
    ```bash
    git clone [URL_DEL_REPOSITORIO]
    cd [NOMBRE_DEL_PROYECTO]
    ```

2.  **Instalar dependencias:**
    Usa tu gestor de paquetes favorito:
    ```bash
    npm install
    # o
    yarn install
    # o
    pnpm install
    ```

3.  **Configurar variables de entorno:**
    Crea un archivo `.env` en la raíz del proyecto y define la URL de tu backend. Puedes basarte en el archivo `.env.example` (si existe).
    ```env
    VITE_API_URL=http://localhost:8080
    ```

4.  **Ejecutar el servidor de desarrollo:**
    ```bash
    npm run dev
    # o
    yarn dev
    # o
    pnpm dev
    ```

    La aplicación estará disponible típicamente en `http://localhost:5173`.