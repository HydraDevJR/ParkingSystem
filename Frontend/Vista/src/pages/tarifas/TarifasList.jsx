const TarifasList = ({ tarifas, onEdit, onDelete }) => {
  if (tarifas.length === 0) {
    return (
      <div className="rounded-xl border border-slate-200 bg-white p-8 text-center text-slate-500">
        No hay tarifas registradas.
      </div>
    );
  }

  const formatCurrency = (value) => {
    return new Intl.NumberFormat('es-CO', { style: 'currency', currency: 'COP', minimumFractionDigits: 0 }).format(value || 0);
  };

  return (
    <div className="rounded-xl border border-slate-200 bg-white shadow-sm overflow-hidden">
      <div className="overflow-x-auto">
        <table className="w-full text-sm">
          <thead className="bg-slate-50 border-b border-slate-200">
            <tr>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Tipo</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Valor</th>
              <th className="px-5 py-3 text-left text-slate-600 font-semibold">Activo</th>
              <th className="px-5 py-3 text-center text-slate-600 font-semibold">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {tarifas.map((t) => (
              <tr key={t.id} className="border-b border-slate-100 hover:bg-slate-50">
                <td className="px-5 py-3">{t.tipo?.replace('_', ' ') || t.tipo}</td>
                <td className="px-5 py-3">{formatCurrency(t.valor)}</td>
                <td className="px-5 py-3">
                  {t.activo ? (
                    <span className="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-green-100 text-green-800">
                      Activo
                    </span>
                  ) : (
                    <span className="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-red-100 text-red-800">
                      Inactivo
                    </span>
                  )}
                </td>
                <td className="px-5 py-3 text-center space-x-2">
                  <button
                    onClick={() => onEdit(t)}
                    className="text-blue-600 hover:text-blue-800 transition-colors"
                    title="Editar"
                  >
                    ✏️
                  </button>
                  <button
                    onClick={() => onDelete(t.id)}
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

export default TarifasList;