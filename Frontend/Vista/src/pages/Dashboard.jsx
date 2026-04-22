import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getLocalStorage } from '../helpers/local-storage';
import { urlAPI } from '../services/api';
import { redirectAlert } from '../helpers/alerts';

const Dashboard = () => {
    const [user, setUser] = useState(null);
    const [resumenCeldas, setResumenCeldas] = useState({ total: 0, ocupadas: 0, disponibles: 0 });
    const [estadiasActivas, setEstadiasActivas] = useState(0);
    const [ingresosHoy, setIngresosHoy] = useState(0);
    const [ingresosMes, setIngresosMes] = useState(0);
    const [estadiasRecientes, setEstadiasRecientes] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const storedUser = getLocalStorage('user');
        setUser(storedUser);
    }, []);

    useEffect(() => {
        const fetchDashboardData = async () => {
            setLoading(true);
            try {
                const [resCeldas, resEstadiasActivas, resIngresosHoy, resIngresosMes, resRecientes] = await Promise.all([
                    fetch(`${urlAPI.celdas}/resumen`).then(res => {
                        if (!res.ok) throw new Error('Error al cargar resumen de celdas');
                        return res.json();
                    }),
                    fetch(`${urlAPI.estadias}/activas/count`).then(res => {
                        if (!res.ok) throw new Error('Error al cargar estadías activas');
                        return res.json();
                    }),
                    fetch(`${urlAPI.estadias}/ingresos/hoy`).then(res => {
                        if (!res.ok) throw new Error('Error al cargar ingresos de hoy');
                        return res.json();
                    }),
                    fetch(`${urlAPI.estadias}/ingresos/mes`).then(res => {
                        if (!res.ok) throw new Error('Error al cargar ingresos del mes');
                        return res.json();
                    }),
                    fetch(`${urlAPI.estadias}/recientes?limit=5`).then(res => {
                        if (!res.ok) throw new Error('Error al cargar estadías recientes');
                        return res.json();
                    })
                ]);

                setResumenCeldas(resCeldas);
                const activas = typeof resEstadiasActivas === 'number' ? resEstadiasActivas : (resEstadiasActivas?.count ?? 0);
                setEstadiasActivas(activas);
                const hoy = typeof resIngresosHoy === 'number' ? resIngresosHoy : (resIngresosHoy?.ingresos ?? 0);
                setIngresosHoy(hoy);
                const mes = typeof resIngresosMes === 'number' ? resIngresosMes : (resIngresosMes?.ingresos ?? 0);
                setIngresosMes(mes);
                setEstadiasRecientes(Array.isArray(resRecientes) ? resRecientes : []);
            } catch (error) {
                console.error('Error cargando datos del dashboard:', error);
                redirectAlert('Error al cargar datos', error.message || 'No se pudieron cargar los datos. Intente nuevamente.', 'error', '/dashboard');
            } finally {
                setLoading(false);
            }
        };

        fetchDashboardData();
    }, []);

    const formatCurrency = (value) => {
        if (value === undefined || value === null) return '$ 0';
        return new Intl.NumberFormat('es-CO', { style: 'currency', currency: 'COP', minimumFractionDigits: 0 }).format(value);
    };

    const formatDateTime = (dateString) => {
        if (!dateString) return 'Fecha no disponible';
        const date = new Date(dateString);
        if (isNaN(date.getTime())) return 'Fecha inválida';
        return date.toLocaleString('es-CO', { hour12: true });
    };

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

            {/* Tarjetas de métricas */}
            <section className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
                <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
                    <div className="flex items-start justify-between">
                        <div>
                            <p className="text-slate-600 text-sm font-semibold">Celdas disponibles</p>
                            <p className="text-slate-900 text-3xl font-bold tracking-tight mt-1">{resumenCeldas.disponibles}</p>
                        </div>
                        <div className="size-10 rounded-lg bg-[#3498DB]/10 flex items-center justify-center">
                            <svg className="size-5 text-[#3498DB]" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                            </svg>
                        </div>
                    </div>
                    <p className="mt-2 text-xs text-slate-500">De {resumenCeldas.total} totales</p>
                </div>

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

                <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
                    <div className="flex items-start justify-between">
                        <div>
                            <p className="text-slate-600 text-sm font-semibold">Ingresos hoy</p>
                            <p className="text-slate-900 text-3xl font-bold tracking-tight mt-1">{formatCurrency(ingresosHoy)}</p>
                        </div>
                        <div className="size-10 rounded-lg bg-[#3498DB]/10 flex items-center justify-center">
                            <svg className="size-5 text-[#3498DB]" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                            </svg>
                        </div>
                    </div>
                    <p className="mt-2 text-xs text-slate-500">Actualizado hoy</p>
                </div>

                <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
                    <div className="flex items-start justify-between">
                        <div>
                            <p className="text-slate-600 text-sm font-semibold">Ingresos del mes</p>
                            <p className="text-slate-900 text-3xl font-bold tracking-tight mt-1">{formatCurrency(ingresosMes)}</p>
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

            {/* Tabla de últimas estadías */}
            <section className="grid grid-cols-1 lg:grid-cols-3 gap-4">
                <div className="lg:col-span-2 rounded-xl border border-slate-200 bg-white shadow-sm">
                    <div className="px-5 py-4 border-b border-slate-200 flex items-center justify-between">
                        <div>
                            <h2 className="text-slate-900 text-base font-bold">Estadías recientes</h2>
                            <p className="text-slate-600 text-sm">Últimos movimientos registrados</p>
                        </div>
                        <button className="inline-flex items-center justify-center rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm font-semibold text-slate-700 hover:bg-slate-50 transition-colors">
                            Ver todas
                        </button>
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
                                            <p className="text-slate-900 text-sm font-semibold">{estadia.placa || 'N/A'}</p>
                                            <p className="text-slate-600 text-xs">Entrada: {formatDateTime(estadia.horaEntrada)}</p>
                                        </div>
                                    </div>
                                    <p className="text-[#0A2647] text-sm font-bold">{formatCurrency(estadia.valorPagado ?? estadia.valor)}</p>
                                </div>
                            ))
                        )}
                    </div>
                </div>

                {/* Acciones rápidas */}
                <div className="rounded-xl border border-slate-200 bg-white p-5 shadow-sm flex flex-col gap-4">
                    <div className="flex items-center justify-between">
                        <div>
                            <h2 className="text-slate-900 text-base font-bold">Acciones rápidas</h2>
                            <p className="text-slate-600 text-sm">Atajos para tareas comunes</p>
                        </div>
                    </div>
                    <div className="grid grid-cols-1 gap-3">
                        <Link to="/dashboard/registro-entrada" className="inline-flex items-center justify-center rounded-lg border border-slate-200 bg-white px-4 py-3 text-sm font-semibold text-slate-700 hover:bg-slate-50 transition-colors">
                            Registrar entrada
                        </Link>
                        <Link to="/dashboard/registro-salida" className="inline-flex items-center justify-center rounded-lg border border-slate-200 bg-white px-4 py-3 text-sm font-semibold text-slate-700 hover:bg-slate-50 transition-colors">
                            Registrar salida
                        </Link>
                        <Link to="/dashboard/ver-mapa-celdas" className="inline-flex items-center justify-center rounded-lg bg-[#0A2647] px-4 py-3 text-sm font-bold text-white hover:bg-[#0A2647]/90 transition-colors">
                            Ver mapa de celdas
                        </Link>
                    </div>
                    <div className="rounded-lg border border-slate-200 p-4 bg-slate-50">
                        <p className="text-slate-900 text-sm font-semibold">Consejo</p>
                        <p className="text-slate-600 text-sm mt-1">Mantén actualizadas las tarifas para evitar errores en el cobro.</p>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default Dashboard;