import { useState, useEffect } from 'react';

const initialForm = {
  codigo: '',
  tipoVehiculo: '',   // se llenará dinámicamente
  estado: '',         // se llenará dinámicamente
};

const CeldasModal = ({ isOpen, onClose, onSave, initialData, tiposVehiculo, estadosCelda }) => {
  const [form, setForm] = useState(initialForm);

  useEffect(() => {
    if (isOpen) {
      if (initialData) {
        setForm({
          codigo: initialData.codigo || '',
          tipoVehiculo: initialData.tipoVehiculo || (tiposVehiculo[0] || ''),
          estado: initialData.estado || (estadosCelda[0] || ''),
        });
      } else {
        setForm({
          ...initialForm,
          tipoVehiculo: tiposVehiculo[0] || '',
          estado: estadosCelda[0] || '',
        });
      }
    }
  }, [initialData, tiposVehiculo, estadosCelda, isOpen]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(form);
  };

  // Función para formatear visualmente los valores del enum
  const formatEnum = (value) => {
    if (!value) return '';
    return value
      .toLowerCase()
      .replace(/_/g, ' ')
      .replace(/\b\w/g, (char) => char.toUpperCase());
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-white rounded-xl shadow-xl w-full max-w-md p-6">
        <h2 className="text-xl font-bold text-slate-900 mb-4">
          {initialData ? 'Editar celda' : 'Nueva celda'}
        </h2>
        <form onSubmit={handleSubmit} className="space-y-3">
          <input
            name="codigo"
            placeholder="Código (ej. A1, B2)"
            value={form.codigo}
            onChange={handleChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          />

          {/* Select dinámico para tipoVehiculo (desde enumService) */}
          <select
            name="tipoVehiculo"
            value={form.tipoVehiculo}
            onChange={handleChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          >
            <option value="">Seleccione tipo vehículo</option>
            {tiposVehiculo.map((tipo) => (
              <option key={tipo} value={tipo}>
                {formatEnum(tipo)}
              </option>
            ))}
          </select>

          {/* Select dinámico para estado (desde enumService) */}
          <select
            name="estado"
            value={form.estado}
            onChange={handleChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          >
            <option value="">Seleccione estado</option>
            {estadosCelda.map((estado) => (
              <option key={estado} value={estado}>
                {formatEnum(estado)}
              </option>
            ))}
          </select>

          <div className="flex justify-end gap-2 mt-4">
            <button type="button" onClick={onClose} className="px-4 py-2 border rounded-lg hover:bg-slate-50">
              Cancelar
            </button>
            <button type="submit" className="px-4 py-2 bg-[#0A2647] text-white rounded-lg hover:bg-[#0A2647]/90">
              Guardar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CeldasModal;