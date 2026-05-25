const CeldasList = ({ celdas, onEdit, onDelete }) => {
  const getEstadoBadge = (estado) => {
    if (estado === 'DISPONIBLE') return <span className="px-2 py-1 text-xs rounded-full bg-green-100 text-green-800">Disponible</span>;
    if (estado === 'OCUPADO') return <span className="px-2 py-1 text-xs rounded-full bg-red-100 text-red-800">Ocupado</span>;
    return <span className="px-2 py-1 text-xs rounded-full bg-gray-100">{estado}</span>;
  };

  if (celdas.length === 0) {
    return (
      <div className="rounded-xl border border-slate-200 bg-white p-8 text-center text-slate-500">
        No hay celdas registradas.
      </div>
    );
  }

  return (
    <div className="rounded-xl border border-slate-200 bg-white shadow-sm overflow-hidden">
      <div className="overflow-x-auto">
        <table className="w-full text-sm">
          <thead className="bg-slate-50 border-b border-slate-200">
            <tr>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Código</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Tipo vehículo</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Estado</th>
              <th className="px-5 py-3 text-center text-slate-600 font-semibold">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {celdas.map((celda) => (
              <tr key={celda.id} className="border-b border-slate-100 hover:bg-slate-50">
                <td className="px-5 py-3 font-medium">{celda.codigo}</td>
                <td className="px-5 py-3">{celda.tipoVehiculo}</td>
                <td className="px-5 py-3">{getEstadoBadge(celda.estado)}</td>
                <td className="px-5 py-3 text-center space-x-2">
                  <button
                    onClick={() => onEdit(celda)}
                    className="text-blue-600 hover:text-blue-800 transition-colors"
                    title="Editar"
                  >
                    ✏️
                  </button>
                  <button
                    onClick={() => onDelete(celda.id)}
                    className="text-red-600 hover:text-red-800 transition-colors"
                    title="Eliminar"
                  >
                    🗑️
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default CeldasList;