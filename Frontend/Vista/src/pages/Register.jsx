import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { urlAPI } from "../services/api";
import { enumService } from "../services/enumService";
import { redirectAlert, showErrorAlert, showHttpErrorAlert } from "../helpers/alerts";
import { saveLocalStorage } from "../helpers/local-storage";

const Register = () => {
  const navigate = useNavigate();

  // Estado del formulario
  const [documento, setDocumento] = useState("");
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [telefono, setTelefono] = useState("");
  const [fechaNacimiento, setFechaNacimiento] = useState("");
  const [genero, setGenero] = useState("");
  const [tipoDocumento, setTipoDocumento] = useState("");

  // Enums desde backend
  const [tiposDocumento, setTiposDocumento] = useState([]);
  const [enumsLoading, setEnumsLoading] = useState(true);

  // Cargar tipos de documento desde el backend
  useEffect(() => {
    const loadEnums = async () => {
      try {
        const tipos = await enumService.getTiposDocumento();
        setTiposDocumento(tipos);
        if (tipos.length > 0) setTipoDocumento(tipos[0]);
      } catch (error) {
        console.error("Error cargando tipos de documento", error);
        showHttpErrorAlert(error, "No se pudieron cargar los tipos de documento");
      } finally {
        setEnumsLoading(false);
      }
    };
    loadEnums();
  }, []);

  // Validar y enviar
  const handleRegister = async () => {
    // Validaciones básicas
    if (!documento || !nombre || !apellido || !email || !password || !confirmPassword || !telefono || !fechaNacimiento || !genero || !tipoDocumento) {
      showErrorAlert("Por favor completa todos los campos");
      return;
    }
    if (password !== confirmPassword) {
      showErrorAlert("Las contraseñas no coinciden");
      return;
    }

    const userData = {
      documento,
      nombre,
      apellido,
      email,
      password,
      telefono,
      fechaNacimiento,
      genero,
      tipoDocumento,
      tipoUsuario: "CLIENTE", // Por defecto, todos los registros nuevos son CLIENTE
    };

    try {
      const response = await fetch(urlAPI.usuarios, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        const newUser = await response.json();
        saveLocalStorage("user", newUser);
        redirectAlert(
          "Registro exitoso",
          `Bienvenido ${nombre} ${apellido}`,
          "success",
          "/dashboard",
          2000
        );
      } else {
        let errorMsg = "No se pudo crear la cuenta";
        try {
          const errorData = await response.json();
          errorMsg = errorData.message || errorMsg;
        } catch (e) { }
        showErrorAlert(errorMsg);
      }
    } catch (error) {
      console.error("Error en registro:", error);
      showHttpErrorAlert(error, "Error de conexión. Intenta más tarde.");
    }
  };

  if (enumsLoading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-[#3498DB]"></div>
      </div>
    );
  }

  return (
    <div>
      <div className="relative flex min-h-screen w-full flex-col overflow-x-hidden">
        <div className="layout-container flex h-full grow flex-col items-center justify-center px-4 py-8">
          <div className="w-full max-w-[500px] flex flex-col gap-6 bg-white p-6 rounded-xl shadow-md">
            <header className="flex flex-col items-center gap-4">
              <div className="size-12 flex items-center justify-center rounded-xl bg-[#3498DB]/10">
                <svg className="text-[#3498DB] size-8" fill="currentColor" viewBox="0 0 24 24">
                  <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.85 7h10.29l1.04 3H5.81l1.04-3zM19 17H5v-4.5h14V17zM7.5 14c-.83 0-1.5-.67-1.5-1.5S6.67 11 7.5 11s1.5.67 1.5 1.5S8.33 14 7.5 14zm9 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5z" />
                </svg>
              </div>
              <div className="text-center">
                <h1 className="text-slate-900 text-2xl font-bold">Crear cuenta</h1>
                <p className="text-slate-600 text-sm">Ingresa tus datos para registrarte</p>
              </div>
            </header>

            <div className="flex flex-col gap-4">
              {/* Documento */}
              <div>
                <label className="text-slate-700 text-sm font-semibold">Documento *</label>
                <input value={documento} onChange={(e) => setDocumento(e.target.value)} className="w-full rounded-lg border border-slate-200 px-3 py-2" placeholder="Número de identificación" />
              </div>
              <div className="grid grid-cols-2 gap-3">
                <div>
                  <label className="text-slate-700 text-sm font-semibold">Nombre *</label>
                  <input value={nombre} onChange={(e) => setNombre(e.target.value)} className="w-full rounded-lg border border-slate-200 px-3 py-2" placeholder="Nombre" />
                </div>
                <div>
                  <label className="text-slate-700 text-sm font-semibold">Apellido *</label>
                  <input value={apellido} onChange={(e) => setApellido(e.target.value)} className="w-full rounded-lg border border-slate-200 px-3 py-2" placeholder="Apellido" />
                </div>
              </div>
              <div>
                <label className="text-slate-700 text-sm font-semibold">Email *</label>
                <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} className="w-full rounded-lg border border-slate-200 px-3 py-2" placeholder="correo@ejemplo.com" />
              </div>
              <div className="grid grid-cols-2 gap-3">
                <div>
                  <label className="text-slate-700 text-sm font-semibold">Contraseña *</label>
                  <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} className="w-full rounded-lg border border-slate-200 px-3 py-2" placeholder="••••••" />
                </div>
                <div>
                  <label className="text-slate-700 text-sm font-semibold">Confirmar *</label>
                  <input type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} className="w-full rounded-lg border border-slate-200 px-3 py-2" placeholder="••••••" />
                </div>
              </div>
              <div>
                <label className="text-slate-700 text-sm font-semibold">Teléfono *</label>
                <input value={telefono} onChange={(e) => setTelefono(e.target.value)} className="w-full rounded-lg border border-slate-200 px-3 py-2" placeholder="Número de contacto" />
              </div>
              <div>
                <label className="text-slate-700 text-sm font-semibold">Fecha de nacimiento *</label>
                <input type="date" value={fechaNacimiento} onChange={(e) => setFechaNacimiento(e.target.value)} className="w-full rounded-lg border border-slate-200 px-3 py-2" />
              </div>
              <div>
                <label className="text-slate-700 text-sm font-semibold">Género *</label>
                <select value={genero} onChange={(e) => setGenero(e.target.value)} className="w-full rounded-lg border border-slate-200 px-3 py-2">
                  <option value="">Seleccione</option>
                  <option value="Masculino">Masculino</option>
                  <option value="Femenino">Femenino</option>
                  <option value="Otro">Otro</option>
                </select>
              </div>
              <div>
                <label className="text-slate-700 text-sm font-semibold">Tipo de documento *</label>
                <select value={tipoDocumento} onChange={(e) => setTipoDocumento(e.target.value)} className="w-full rounded-lg border border-slate-200 px-3 py-2">
                  <option value="">Seleccione</option>
                  {tiposDocumento.map(tipo => (
                    <option key={tipo} value={tipo}>{tipo.replace(/_/g, ' ')}</option>
                  ))}
                </select>
              </div>
              <button onClick={handleRegister} className="mt-2 rounded-lg bg-[#0A2647] py-2 text-white font-bold hover:bg-[#0A2647]/90">
                Registrarse
              </button>
            </div>

            <footer className="text-center text-sm">
              ¿Ya tienes cuenta?
              <Link className="font-semibold text-[#3498DB] hover:underline ml-1" to="/">Iniciar sesión</Link>
            </footer>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;