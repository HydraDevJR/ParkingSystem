const VehiculosList = ({ vehiculos, onEdit, onDelete }) => {
  if (vehiculos.length === 0) {
    return (
      <div className="rounded-xl border border-slate-200 bg-white p-8 text-center text-slate-500">
        No hay vehículos registrados.
      </div>
    );
  }

  return (
    <div className="rounded-xl border border-slate-200 bg-white shadow-sm overflow-hidden">
      <div className="overflow-x-auto">
        <table className="w-full text-sm">
          <thead className="bg-slate-50 border-b border-slate-200">
            <tr>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Placa</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Marca</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Modelo</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Color</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Tipo</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Propietario</th>
              <th className="px-5 py-3 text-center text-slate-600 font-semibold">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {vehiculos.map((v) => (
              <tr key={v.id} className="border-b border-slate-100 hover:bg-slate-50">
                <td className="px-5 py-3 font-medium">{v.placa}</td>
                <td className="px-5 py-3">{v.marca}</td>
                <td className="px-5 py-3">{v.modelo}</td>
                <td className="px-5 py-3">{v.color}</td>
                <td className="px-5 py-3">{v.tipoVehiculo}</td>
                <td className="px-5 py-3">{v.usuario?.nombre} {v.usuario?.apellido}</td>
                <td className="px-5 py-3 text-center space-x-2">
                  <button
                    onClick={() => onEdit(v)}
                    className="text-blue-600 hover:text-blue-800 transition-colors"
                    title="Editar"
                  >
                    ✏️
                  </button>
                  <button
                    onClick={() => onDelete(v.id)}
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

export default VehiculosList;