import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { getLocalStorage } from "../helpers/local-storage";
import { urlAPI } from "../services/api";
import { formatCurrency, formatDateTime } from "../utils/formatters";
import { showHttpErrorAlert } from "../helpers/alerts";

const Dashboard = () => {
    const [user, setUser] = useState(null);
    const [resumenCeldas, setResumenCeldas] = useState({
        total: 0,
        ocupadas: 0,
        disponibles: 0,
    });
    const [estadiasActivas, setEstadiasActivas] = useState(0);
    const [ingresosHoy, setIngresosHoy] = useState(0);
    const [ingresosMes, setIngresosMes] = useState(0);
    const [estadiasRecientes, setEstadiasRecientes] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const storedUser = getLocalStorage("user");
        setUser(storedUser);
    }, []);

    useEffect(() => {
        const fetchDashboardData = async () => {
            setLoading(true);
            try {
                const [celdas, estadias] = await Promise.all([
                    fetch(`${urlAPI.celdas}`).then((res) => res.json()),
                    fetch(`${urlAPI.estadias}`).then((res) => res.json()),
                ]);

                const ocupadas = celdas.filter((c) => c.estado === 'OCUPADA').length;
                setResumenCeldas({
                    total: celdas.length,
                    ocupadas,
                    disponibles: celdas.length - ocupadas,
                });

                const activas = estadias.filter((e) => e.estado === 'EN_CURSO');
                setEstadiasActivas(activas.length);

                const hoy = new Date().toDateString();
                const ingresosHoy = estadias
                    .filter((e) => e.fechaFin && new Date(e.fechaFin).toDateString() === hoy)
                    .reduce((sum, e) => sum + (e.valorTotal ?? 0), 0);
                setIngresosHoy(ingresosHoy);

                const ahora = new Date();
                const ingresosMes = estadias
                    .filter((e) => {
                        if (!e.fechaFin) return false;
                        const fecha = new Date(e.fechaFin);
                        return (
                            fecha.getMonth() === ahora.getMonth() &&
                            fecha.getFullYear() === ahora.getFullYear()
                        );
                    })
                    .reduce((sum, e) => sum + (e.valorTotal ?? 0), 0);
                setIngresosMes(ingresosMes);

                const recientes = [...estadias]
                    .sort((a, b) => new Date(b.fechaInicio) - new Date(a.fechaInicio))
                    .slice(0, 5)
                    .map(e => ({
                        ...e,
                        placa: e.vehiculo?.placa || 'N/A',
                        valor: e.valorTotal
                    }));
                setEstadiasRecientes(recientes);
            } catch (error) {
                console.error("Error cargando datos del dashboard:", error);
                showHttpErrorAlert(error, "No se pudieron cargar los datos del dashboard");
            } finally {
                setLoading(false);
            }
        };

        fetchDashboardData();
    }, []);

    if (loading) {
        return (
            <div className="flex justify-center items-center h-64">
                <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-[#3498DB]"></div>
            </div>
        );
    }

    return (
        <div className="space-y-8">
            <section className="flex flex-col gap-2">
                <h1 className="text-slate-900 text-2xl font-bold tracking-tight">
                    Resumen operativo del parqueadero
                </h1>
            </section>

            <section className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
                {/* Tarjeta Celdas disponibles */}
                <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
                    <div className="flex items-start justify-between">
                        <div>
                            <p className="text-slate-600 text-sm font-semibold">Celdas disponibles</p>
                            <p className="text-slate-900 text-3xl font-bold tracking-tight mt-1">
                                {resumenCeldas.disponibles}
                            </p>
                        </div>
                        <div className="size-10 rounded-lg bg-[#3498DB]/10 flex items-center justify-center">
                            <svg className="size-5 text-[#3498DB]" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                            </svg>
                        </div>
                    </div>
                    <p className="mt-2 text-xs text-slate-500">De {resumenCeldas.total} totales</p>
                </div>

                {/* Tarjeta Estadías activas */}
                <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
                    <div className="flex items-start justify-between">
                        <div>
                            <p className="text-slate-600 text-sm font-semibold">Estadías activas</p>
                            <p className="text-slate-900 text-3xl font-bold tracking-tight mt-1">{estadiasActivas}</p>
                        </div>
                        <div className="size-10 rounded-lg bg-[#3498DB]/10 flex items-center justify-center">
                            <svg className="size-5 text-[#3498DB]" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                            </svg>
                        </div>
                    </div>
                    <p className="mt-2 text-xs text-slate-500">Vehículos estacionados ahora</p>
                </div>

                {/* Tarjeta Ingresos hoy */}
                <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
                    <div className="flex items-start justify-between">
                        <div>
                            <p className="text-slate-600 text-sm font-semibold">Ingresos hoy</p>
                            <p className="text-slate-900 text-3xl font-bold tracking-tight mt-1">
                                {formatCurrency(ingresosHoy)}
                            </p>
                        </div>
                        <div className="size-10 rounded-lg bg-[#3498DB]/10 flex items-center justify-center">
                            <svg className="size-5 text-[#3498DB]" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                            </svg>
                        </div>
                    </div>
                    <p className="mt-2 text-xs text-slate-500">Actualizado hoy</p>
                </div>

                {/* Tarjeta Ingresos del mes */}
                <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
                    <div className="flex items-start justify-between">
                        <div>
                            <p className="text-slate-600 text-sm font-semibold">Ingresos del mes</p>
                            <p className="text-slate-900 text-3xl font-bold tracking-tight mt-1">
                                {formatCurrency(ingresosMes)}
                            </p>
                        </div>
                        <div className="size-10 rounded-lg bg-[#3498DB]/10 flex items-center justify-center">
                            <svg className="size-5 text-[#3498DB]" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
                            </svg>
                        </div>
                    </div>
                    <p className="mt-2 text-xs text-slate-500">Proyección vs mes anterior</p>
                </div>
            </section>

            {/* Tabla de últimas estadías y acciones rápidas */}
            <section className="grid grid-cols-1 lg:grid-cols-3 gap-4">
                <div className="lg:col-span-2 rounded-xl border border-slate-200 bg-white shadow-sm">
                    <div className="px-5 py-4 border-b border-slate-200 flex items-center justify-between">
                        <div>
                            <h2 className="text-slate-900 text-base font-bold">Estadías recientes</h2>
                            <p className="text-slate-600 text-sm">Últimos movimientos registrados</p>
                        </div>
                        <Link to="/estadias" className="inline-flex items-center justify-center rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm font-semibold text-slate-700 hover:bg-slate-50 transition-colors">
                            Ver todas
                        </Link>
                    </div>
                    <div className="divide-y divide-slate-200">
                        {estadiasRecientes.length === 0 ? (
                            <div className="px-5 py-8 text-center text-slate-500">No hay estadías recientes</div>
                        ) : (
                            estadiasRecientes.map((estadia) => (
                                <div key={estadia.id} className="px-5 py-4 flex items-center justify-between gap-4">
                                    <div className="flex items-center gap-3">
                                        <div className="size-10 rounded-lg bg-slate-100 flex items-center justify-center">
                                            <span className="text-slate-700 text-sm font-bold">🚗</span>
                                        </div>
                                        <div className="flex flex-col">
                                            <p className="text-slate-900 text-sm font-semibold">{estadia.placa}</p>
                                            <p className="text-slate-600 text-xs">Entrada: {formatDateTime(estadia.fechaInicio)}</p>
                                        </div>
                                    </div>
                                    <p className="text-[#0A2647] text-sm font-bold">{formatCurrency(estadia.valor)}</p>
                                </div>
                            ))
                        )}
                    </div>
                </div>

                {/* Acciones rápidas - versión unificada */}
                <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm flex flex-col gap-4">
                    <div className="flex items-center justify-between">
                        <div>
                            <h2 className="text-slate-900 text-base font-bold">Acciones rápidas</h2>
                            <p className="text-slate-600 text-sm">Atajos para tareas comunes</p>
                        </div>
                    </div>
                    <div className="grid grid-cols-1 gap-3">
                        <Link to="/estadias" className="inline-flex items-center justify-center rounded-lg border border-slate-200 bg-white px-4 py-3 text-sm font-semibold text-slate-700 hover:bg-slate-50 transition-colors">
                            Registrar entrada / salida
                        </Link>
                        <Link to="/dashboard/ver-mapa-celdas" className="inline-flex items-center justify-center rounded-lg bg-[#0A2647] px-4 py-3 text-sm font-bold text-white hover:bg-[#0A2647]/90 transition-colors">
                            Ver mapa de celdas
                        </Link>
                    </div>
                    <div className="rounded-lg border border-slate-200 p-4 bg-slate-50">
                        <p className="text-slate-900 text-sm font-semibold">Consejo</p>
                        <p className="text-slate-600 text-sm mt-1">
                            Mantén actualizadas las tarifas para evitar errores en el cobro.
                        </p>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default Dashboard;