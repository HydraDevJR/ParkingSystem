import { useState, useEffect } from 'react';

const initialForm = {
  vehiculo: null,
  celda: null,
  tarifa: null,
  fechaInicio: new Date().toISOString().slice(0, 16), // formato local datetime-local
  estado: '', // se llenará dinámicamente
};

const EstadiasModal = ({ isOpen, onClose, onSave, vehiculos, celdas, tarifas, estadosEstadia }) => {
  const [form, setForm] = useState(initialForm);
  const [selectedVehiculoId, setSelectedVehiculoId] = useState('');
  const [selectedCeldaId, setSelectedCeldaId] = useState('');
  const [selectedTarifaId, setSelectedTarifaId] = useState('');

  // Resetear formulario cuando se abre el modal
  useEffect(() => {
    if (isOpen) {
      setForm({
        ...initialForm,
        estado: estadosEstadia[0] || 'EN_CURSO', // primer estado disponible
      });
      setSelectedVehiculoId('');
      setSelectedCeldaId('');
      setSelectedTarifaId('');
    }
  }, [isOpen, estadosEstadia]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleVehiculoChange = (e) => {
    const id = e.target.value;
    setSelectedVehiculoId(id);
    const vehiculo = vehiculos.find(v => v.id.toString() === id);
    setForm({ ...form, vehiculo: vehiculo || null });
  };

  const handleCeldaChange = (e) => {
    const id = e.target.value;
    setSelectedCeldaId(id);
    const celda = celdas.find(c => c.id.toString() === id);
    setForm({ ...form, celda: celda || null });
  };

  const handleTarifaChange = (e) => {
    const id = e.target.value;
    setSelectedTarifaId(id);
    const tarifa = tarifas.find(t => t.id.toString() === id);
    setForm({ ...form, tarifa: tarifa || null });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.vehiculo || !form.celda || !form.tarifa) {
      alert('Debe completar todos los campos');
      return;
    }
    const dataToSend = {
      vehiculo: { id: form.vehiculo.id },
      celda: { id: form.celda.id },
      tarifa: { id: form.tarifa.id },
      fechaInicio: form.fechaInicio,
      estado: form.estado,
    };
    onSave(dataToSend);
  };

  // Función para formatear valores de enum (ej. "EN_CURSO" -> "En curso")
  const formatEstado = (estado) => {
    if (!estado) return '';
    return estado
      .toLowerCase()
      .replace(/_/g, ' ')
      .replace(/\b\w/g, (char) => char.toUpperCase());
  };

  // Formatear tipo de vehículo para mostrar en opciones de celda (opcional)
  const formatTipoVehiculo = (tipo) => {
    if (!tipo) return '';
    return tipo.charAt(0).toUpperCase() + tipo.slice(1).toLowerCase();
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-white rounded-xl shadow-xl w-full max-w-md p-6">
        <h2 className="text-xl font-bold text-slate-900 mb-4">Registrar ingreso</h2>
        <form onSubmit={handleSubmit} className="space-y-3">
          <select value={selectedVehiculoId} onChange={handleVehiculoChange} className="w-full border rounded-lg p-2" required>
            <option value="">Seleccione vehículo</option>
            {vehiculos.map(v => (
              <option key={v.id} value={v.id}>{v.placa} - {v.marca} {v.modelo}</option>
            ))}
          </select>

          <select value={selectedCeldaId} onChange={handleCeldaChange} className="w-full border rounded-lg p-2" required>
            <option value="">Seleccione celda</option>
            {celdas.filter(c => c.estado === 'DISPONIBLE').map(c => (
              <option key={c.id} value={c.id}>{c.codigo} ({formatTipoVehiculo(c.tipoVehiculo)})</option>
            ))}
          </select>

          <select value={selectedTarifaId} onChange={handleTarifaChange} className="w-full border rounded-lg p-2" required>
            <option value="">Seleccione tarifa</option>
            {tarifas.filter(t => t.activo).map(t => (
              <option key={t.id} value={t.id}>{t.tipo} - ${t.valor}/hora</option>
            ))}
          </select>

          {/* Selector de estado de estadía (dinámico desde enumService) */}
          <select
            name="estado"
            value={form.estado}
            onChange={handleChange}
            className="w-full border rounded-lg p-2"
            required
          >
            <option value="">Seleccione estado</option>
            {estadosEstadia.map(estado => (
              <option key={estado} value={estado}>
                {formatEstado(estado)}
              </option>
            ))}
          </select>

          <input
            type="datetime-local"
            name="fechaInicio"
            value={form.fechaInicio}
            onChange={handleChange}
            className="w-full border rounded-lg p-2"
            required
          />

          <div className="flex justify-end gap-2 mt-4">
            <button type="button" onClick={onClose} className="px-4 py-2 border rounded-lg hover:bg-slate-50">
              Cancelar
            </button>
            <button type="submit" className="px-4 py-2 bg-[#0A2647] text-white rounded-lg hover:bg-[#0A2647]/90">
              Guardar ingreso
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EstadiasModal;