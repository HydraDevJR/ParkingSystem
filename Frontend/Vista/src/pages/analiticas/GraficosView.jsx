import { useState, useEffect } from 'react';
import { showErrorAlert } from '../../helpers/alerts';
import { analyticsBase } from '../../services/api';

const GraficosView = ({ refreshKey }) => {
  const [imgLineaError, setImgLineaError] = useState(false);
  const [imgBarrasError, setImgBarrasError] = useState(false);
  const [imgTimestamp, setImgTimestamp] = useState(Date.now());

  useEffect(() => {
    setImgTimestamp(Date.now());
    setImgLineaError(false);
    setImgBarrasError(false);
  }, [refreshKey]);

  const lineaUrl = `${analyticsBase}/analytics/grafico_linea.png?t=${imgTimestamp}`;
  const barrasUrl = `${analyticsBase}/analytics/grafico_barras.png?t=${imgTimestamp}`;

  const handleLineaError = () => {
    if (!imgLineaError) {
      setImgLineaError(true);
      showErrorAlert('No se pudo cargar el gráfico de evolución de ingresos. Verifica que el análisis se haya ejecutado.');
    }
  };

  const handleBarrasError = () => {
    if (!imgBarrasError) {
      setImgBarrasError(true);
      showErrorAlert('No se pudo cargar el gráfico de promedio por tipo de vehículo.');
    }
  };

  return (
    <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <div className="bg-white rounded-xl border border-slate-200 p-4 shadow-sm">
        <h3 className="text-slate-700 font-semibold mb-3">Evolución de ingresos diarios</h3>
        <div className="flex justify-center">
          {!imgLineaError ? (
            <img
              src={lineaUrl}
              alt="Ingresos diarios"
              className="w-full h-auto rounded-lg"
              onError={handleLineaError}
            />
          ) : (
            <div className="text-center text-slate-500 py-8">
              Gráfico no disponible. Ejecute el análisis para generarlo.
            </div>
          )}
        </div>
      </div>
      <div className="bg-white rounded-xl border border-slate-200 p-4 shadow-sm">
        <h3 className="text-slate-700 font-semibold mb-3">Promedio de ingresos por tipo de vehículo</h3>
        <div className="flex justify-center">
          {!imgBarrasError ? (
            <img
              src={barrasUrl}
              alt="Promedio por tipo de vehículo"
              className="w-full h-auto rounded-lg"
              onError={handleBarrasError}
            />
          ) : (
            <div className="text-center text-slate-500 py-8">
              Gráfico no disponible. Ejecute el análisis para generarlo.
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default GraficosView;