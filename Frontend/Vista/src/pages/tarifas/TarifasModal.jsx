import { useState, useEffect } from 'react';

const initialForm = {
  tipo: '',        // será llenado dinámicamente
  valor: '',
  activo: true,
};

const TarifasModal = ({ isOpen, onClose, onSave, initialData, tiposTarifa }) => {
  const [form, setForm] = useState(initialForm);

  useEffect(() => {
    if (isOpen) {
      if (initialData) {
        setForm({
          tipo: initialData.tipo || (tiposTarifa[0] || ''),
          valor: initialData.valor || '',
          activo: initialData.activo !== undefined ? initialData.activo : true,
        });
      } else {
        setForm({
          ...initialForm,
          tipo: tiposTarifa[0] || '',
          activo: true,
        });
      }
    }
  }, [initialData, tiposTarifa, isOpen]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm({
      ...form,
      [name]: type === 'checkbox' ? checked : value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const dataToSend = {
      ...form,
      valor: parseFloat(form.valor),
    };
    onSave(dataToSend);
  };

  // Función para formatear visualmente (ej. "POR_HORA" -> "Por hora")
  const formatTipo = (tipo) => {
    if (!tipo) return '';
    return tipo
      .toLowerCase()
      .replace(/_/g, ' ')
      .replace(/\b\w/g, (char) => char.toUpperCase());
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-white rounded-xl shadow-xl w-full max-w-md p-6">
        <h2 className="text-xl font-bold text-slate-900 mb-4">
          {initialData ? 'Editar tarifa' : 'Nueva tarifa'}
        </h2>
        <form onSubmit={handleSubmit} className="space-y-3">
          <select
            name="tipo"
            value={form.tipo}
            onChange={handleChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          >
            <option value="">Seleccione tipo de tarifa</option>
            {tiposTarifa.map((tipo) => (
              <option key={tipo} value={tipo}>
                {formatTipo(tipo)}
              </option>
            ))}
          </select>

          <input
            name="valor"
            type="number"
            step="0.01"
            placeholder="Valor en pesos (ej. 5000)"
            value={form.valor}
            onChange={handleChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          />

          <label className="flex items-center gap-2">
            <input
              type="checkbox"
              name="activo"
              checked={form.activo}
              onChange={handleChange}
              className="rounded border-slate-300"
            />
            <span className="text-sm text-slate-700">Activo</span>
          </label>

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

export default TarifasModal;