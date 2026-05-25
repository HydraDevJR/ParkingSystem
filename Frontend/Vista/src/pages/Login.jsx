import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { urlAPI } from "../services/api";
import { saveLocalStorage } from "../helpers/local-storage";
import { redirectAlert, showErrorAlert } from "../helpers/alerts";

const Login = () => {
  const [getEmail, setEmail] = useState("");
  const [getPassword, setPassword] = useState("");
  const [users, setUsers] = useState([]);

  function getUsers() {
    fetch(urlAPI.usuarios)
      .then((response) => response.json())
      .then((data) => setUsers(data))
      .catch((error) => {
        console.error("Error cargando usuarios:", error);
        showErrorAlert("No se pudieron cargar los usuarios. Verifica la conexión con el servidor.");
      });
  }

  useEffect(() => {
    getUsers();
  }, []);

  function findUser() {
    return users.find((item) => getEmail === item.email && getPassword === item.password);
  }

  function signIn() {
    if (!getEmail || !getPassword) {
      showErrorAlert("Debes completar ambos campos (email y contraseña).");
      return;
    }

    let user = findUser();
    if (user) {
      // Guardar usuario (puedes guardar solo lo necesario, ej. id, nombre, email, rol)
      saveLocalStorage("user", user);
      const nombreCompleto = `${user.nombre} ${user.apellido}`;
      redirectAlert(
        `Bienvenido ${nombreCompleto}`,
        "Iniciando sesión...",
        "success",
        "/dashboard",
        2000
      );
    } else {
      redirectAlert(
        "Credenciales inválidas",
        "Verifica tu email y contraseña",
        "error",
        "/",
        2000
      );
    }
  }

  return (
    <div>
      <div className="relative flex min-h-screen w-full flex-col overflow-x-hidden">
        <div className="layout-container flex h-full grow flex-col items-center justify-center px-4">
          <div className="w-full max-w-[440px] flex flex-col gap-8">
            <header className="flex flex-col items-center gap-6">
              <div className="size-12 flex items-center justify-center rounded-xl bg-[#3498DB]/10">
                <svg className="text-[#3498DB] size-8" fill="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                  <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.85 7h10.29l1.04 3H5.81l1.04-3zM19 17H5v-4.5h14V17zM7.5 14c-.83 0-1.5-.67-1.5-1.5S6.67 11 7.5 11s1.5.67 1.5 1.5S8.33 14 7.5 14zm9 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5z" />
                </svg>
              </div>
              <div className="flex flex-col gap-2 text-center">
                <h1 className="text-slate-900 dark:text-slate-400 text-3xl font-bold tracking-tight">Parking System</h1>
                <p className="text-slate-300 dark:text-slate-500 text-base">Ingresa tus credenciales para acceder</p>
              </div>
            </header>
            <div className="flex flex-col gap-5">
              <div className="flex flex-col gap-2">
                <div className="flex items-center justify-between">
                  <label className="text-slate-700 dark:text-slate-300 text-sm font-semibold leading-none">Email</label>
                </div>
                <input
                  onChange={(e) => setEmail(e.target.value)}
                  className="flex w-full rounded-lg border border-slate-200 bg-white px-4 py-2 text-lg ring-offset-white placeholder:text-slate-500 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-[#3498DB] focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 min-h-[40px]"
                  placeholder="Email"
                  type="email"
                  value={getEmail}
                />
              </div>
              <div className="flex flex-col gap-2">
                <div className="flex items-center justify-between">
                  <label className="text-slate-700 dark:text-slate-300 text-sm font-semibold leading-none">Contraseña</label>
                </div>
                <input
                  onChange={(e) => setPassword(e.target.value)}
                  className="flex w-full rounded-lg border border-slate-200 bg-white px-4 py-2 text-lg ring-offset-white placeholder:text-slate-500 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-[#3498DB] focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 min-h-[40px]"
                  placeholder="••••••••"
                  type="password"
                  value={getPassword}
                />
              </div>
              <div className="flex flex-col gap-4 pt-4">
                <button
                  onClick={signIn}
                  type="button"
                  className="inline-flex items-center justify-center rounded-lg bg-[#0A2647] px-4 py-2 text-lg font-bold text-white transition-colors hover:bg-[#0A2647]/90 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-[#0A2647] focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 min-h-[40px]"
                >
                  Iniciar sesión
                </button>
              </div>
            </div>
            <footer className="text-center">
              <p className="text-sm text-slate-300 dark:text-slate-400">
                ¿No tienes cuenta?
                <Link className="font-semibold text-[#3498DB] hover:underline ml-1" to="/register">
                  Regístrate gratis
                </Link>
              </p>
            </footer>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;