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