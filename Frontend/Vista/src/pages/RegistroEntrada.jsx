import { useState } from "react";
import { Link } from "react-router-dom";
import { urlAPI } from "../services/api";
import { redirectAlert } from "../helpers/alerts";

const RegistroEntrada = () => {
    const [placa, setPlaca] = useState("");
    const [tipoVehiculo, setTipoVehiculo] = useState("carro");
    const [celda, setCelda] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!placa.trim()) {
            redirectAlert("Error", "La placa es obligatoria", "error", "/dashboard/registro-entrada");
            return;
        }

        try {
            const response = await fetch(urlAPI.estadias, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    placa: placa.toUpperCase(),
                    tipoVehiculo,
                    celda: celda || null,
                    horaEntrada: new Date().toISOString(),
                }),
            });

            if (response.ok) {
                redirectAlert(
                    "¡Entrada registrada!",
                    `Vehículo ${placa.toUpperCase()} ingresó correctamente. Serás redirigido al panel principal`,
                    "success",
                    "/dashboard"
                );
            } else {
                const errorData = await response.json();
                redirectAlert("Error", errorData.message || "No se pudo registrar la entrada", "error", "/dashboard/registro-entrada");
            }
        } catch (error) {
            redirectAlert("Error de conexión", "Intente más tarde", "error", "/dashboard/registro-entrada");
        }
    };

    return (
        <div className="max-w-md mx-auto bg-white p-6 rounded-xl shadow-md">
            <h2 className="text-2xl font-bold text-[#0A2647] mb-4">Registrar entrada</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                    <label className="block text-sm font-semibold text-slate-700">Placa *</label>
                    <input
                        type="text"
                        value={placa}
                        onChange={(e) => setPlaca(e.target.value)}
                        className="w-full rounded-lg border border-slate-200 bg-white px-4 py-2 text-base focus:ring-2 focus:ring-[#3498DB] focus:outline-none"
                        placeholder="ABC123"
                    />
                </div>
                <div>
                    <label className="block text-sm font-semibold text-slate-700">Tipo de vehículo</label>
                    <select
                        value={tipoVehiculo}
                        onChange={(e) => setTipoVehiculo(e.target.value)}
                        className="w-full rounded-lg border border-slate-200 bg-white px-4 py-2 text-base focus:ring-2 focus:ring-[#3498DB] focus:outline-none"
                    >
                        <option value="carro">Carro</option>
                        <option value="moto">Moto</option>
                    </select>
                </div>
                <div>
                    <label className="block text-sm font-semibold text-slate-700">Celda (opcional)</label>
                    <input
                        type="text"
                        value={celda}
                        onChange={(e) => setCelda(e.target.value)}
                        className="w-full rounded-lg border border-slate-200 bg-white px-4 py-2 text-base focus:ring-2 focus:ring-[#3498DB] focus:outline-none"
                        placeholder="A1"
                    />
                </div>

                {/* Botones en fila */}
                <div className="flex gap-3 pt-2">
                    <button
                        type="submit"
                        className="flex-1 bg-[#0A2647] text-white py-2 rounded-lg font-bold hover:bg-[#0A2647]/90 transition-colors"
                    >
                        Registrar entrada
                    </button>
                    <Link
                        to="/dashboard"
                        className="flex-1 bg-gray-300 text-slate-700 py-2 rounded-lg font-bold hover:bg-gray-400 transition-colors text-center"
                    >
                        Cancelar
                    </Link>
                </div>
            </form>
        </div>
    );
};

export default RegistroEntrada;