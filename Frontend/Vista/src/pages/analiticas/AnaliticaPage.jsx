import { useState, useEffect } from 'react';
import { showHttpErrorAlert, showSuccessAlert } from '../../helpers/alerts';
import { analiticaService } from '../../services/analiticaService';
import GraficosView from './GraficosView';
import AccionesReportes from './AccionesReportes';
import EstadisticasResumen from './EstadisticasResumen';

const AnaliticaPage = () => {
    const [loading, setLoading] = useState(true);
    const [estadisticas, setEstadisticas] = useState(null);
    const [refreshKey, setRefreshKey] = useState(0); // para forzar recarga de imágenes

    useEffect(() => {
        cargarEstadisticas();
    }, []);

    const cargarEstadisticas = async () => {
        setLoading(true);
        try {
            // Si el backend tiene un endpoint de estadísticas (opcional)
            const data = await analiticaService.getEstadisticas();
            setEstadisticas(data);
        } catch (error) {
            // Si no hay endpoint, no mostramos error, solo lo omitimos
            console.warn("No se pudieron cargar estadísticas adicionales", error);
        } finally {
            setLoading(false);
        }
    };

    const handleRefresh = async () => {
        try {
            // Si tienes endpoint para regenerar gráficos
            await analiticaService.regenerarGraficos();
            showSuccessAlert('Gráficos actualizados correctamente');
            setRefreshKey(prev => prev + 1); // fuerza recarga de imágenes
        } catch (error) {
            showHttpErrorAlert(error, 'No se pudieron regenerar los gráficos');
        }
    };

    if (loading && estadisticas === null) {
        return (
            <div className="flex justify-center items-center h-64">
                <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-[#3498DB]"></div>
            </div>
        );
    }

    return (
        <div className="space-y-6">
            <div className="flex justify-between items-center">
                <div>
                    <h1 className="text-slate-900 text-2xl font-bold">Analítica de Datos</h1>
                    <p className="text-slate-600">Visualización de ingresos y comportamiento del parqueadero</p>
                </div>
                <button
                    onClick={handleRefresh}
                    className="bg-[#0A2647] text-white px-4 py-2 rounded-lg text-sm font-semibold hover:bg-[#0A2647]/90 flex items-center gap-2"
                >
                    <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
                    </svg>
                    Actualizar gráficos
                </button>
            </div>

            {/* Resumen de estadísticas (opcional, si tienes datos) */}
            {estadisticas && <EstadisticasResumen data={estadisticas} />}

            {/* Sección de gráficos */}
            <GraficosView refreshKey={refreshKey} />

            {/* Botones de descarga de reportes */}
            <AccionesReportes />
        </div>
    );
};

export default AnaliticaPage;