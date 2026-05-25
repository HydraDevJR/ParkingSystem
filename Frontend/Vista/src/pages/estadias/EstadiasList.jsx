// src/pages/estadias/EstadiasList.jsx
import { formatCurrency, formatDateTime } from '../../utils/formatters';

// Función para formatear el estado de forma legible
const formatEstado = (estado) => {
  if (!estado) return '';
  return estado
    .toLowerCase()
    .replace(/_/g, ' ')
    .replace(/\b\w/g, (char) => char.toUpperCase());
};

// Función para obtener el color según el estado (usando los valores reales del enum)
const getEstadoColor = (estado) => {
  switch (estado) {
    case 'EN_CURSO':
      return 'bg-green-100 text-green-800';
    case 'FINALIZADA':
      return 'bg-blue-100 text-blue-800';
    case 'CANCELADA':
      return 'bg-red-100 text-red-800';
    default:
      return 'bg-gray-100 text-gray-800';
  }
};

const EstadiasList = ({ estadias, onFinalizar }) => {
  if (estadias.length === 0) {
    return (
      <div className="rounded-xl border border-slate-200 bg-white p-8 text-center text-slate-500">
        No hay estadías registradas.
      </div>
    );
  }

  return (
    <div className="rounded-xl border border-slate-200 bg-white shadow-sm overflow-hidden">
      <div className="overflow-x-auto">
        <table className="w-full text-sm">
          <thead className="bg-slate-50 border-b border-slate-200">
            <tr>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Vehículo</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Celda</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Ingreso</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Salida</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Valor</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Estado</th>
              <th className="px-5 py-3 text-center text-slate-600 font-semibold">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {estadias.map((e) => (
              <tr key={e.id} className="border-b border-slate-100 hover:bg-slate-50">
                <td className="px-5 py-3">{e.vehiculo?.placa || 'N/A'} ({e.vehiculo?.tipoVehiculo})</td>
                <td className="px-5 py-3">{e.celda?.codigo || 'N/A'}</td>
                <td className="px-5 py-3">{formatDateTime(e.fechaInicio)}</td>
                <td className="px-5 py-3">{e.fechaFin ? formatDateTime(e.fechaFin) : '—'}</td>
                <td className="px-5 py-3 font-medium">{e.valorTotal ? formatCurrency(e.valorTotal) : '—'}</td>
                <td className="px-5 py-3">
                  <span className={`px-2 py-1 rounded-full text-xs font-semibold ${getEstadoColor(e.estado)}`}>
                    {formatEstado(e.estado)}
                  </span>
                </td>
                <td className="px-5 py-3 text-center">
                  {e.estado === 'EN_CURSO' && (
                    <button
                      onClick={() => onFinalizar(e)}
                      className="text-[#0A2647] hover:text-[#0A2647]/80 font-semibold text-xs bg-slate-100 px-3 py-1 rounded-full transition-colors"
                    >
                      Finalizar
                    </button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default EstadiasList;