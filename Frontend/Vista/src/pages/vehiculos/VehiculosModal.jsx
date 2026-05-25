import { useState, useEffect } from 'react';

const initialForm = {
  placa: '',
  marca: '',
  modelo: '',
  color: '',
  tipoVehiculo: '', // será llenado dinámicamente
  usuario: null,
};

const VehiculosModal = ({ isOpen, onClose, onSave, initialData, usuarios, tiposVehiculo }) => {
  const [form, setForm] = useState(initialForm);
  const [selectedUserId, setSelectedUserId] = useState('');

  // Efecto para resetear el formulario cuando se abre el modal o cambian las props
  useEffect(() => {
    if (isOpen) {
      if (initialData) {
        setForm({
          placa: initialData.placa || '',
          marca: initialData.marca || '',
          modelo: initialData.modelo || '',
          color: initialData.color || '',
          tipoVehiculo: initialData.tipoVehiculo || (tiposVehiculo[0] || ''),
          usuario: initialData.usuario || null,
        });
        setSelectedUserId(initialData.usuario?.id?.toString() || '');
      } else {
        setForm({
          ...initialForm,
          tipoVehiculo: tiposVehiculo[0] || '',
          usuario: null,
        });
        setSelectedUserId('');
      }
    }
  }, [initialData, tiposVehiculo, isOpen]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleUserChange = (e) => {
    const userId = e.target.value;
    setSelectedUserId(userId);
    const usuarioSeleccionado = usuarios.find(u => u.id.toString() === userId);
    setForm({ ...form, usuario: usuarioSeleccionado || null });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.usuario) {
      alert('Debe seleccionar un propietario.');
      return;
    }
    const dataToSend = {
      ...form,
      usuario: { id: form.usuario.id },
    };
    onSave(dataToSend);
  };

  if (!isOpen) return null;

  // Función opcional para formatear los valores del enum (ej. "CARRO" -> "Carro")
  const formatTipo = (value) => {
    if (!value) return '';
    return value.charAt(0).toUpperCase() + value.slice(1).toLowerCase();
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-white rounded-xl shadow-xl w-full max-w-md p-6 max-h-[90vh] overflow-y-auto">
        <h2 className="text-xl font-bold text-slate-900 mb-4">
          {initialData ? 'Editar vehículo' : 'Nuevo vehículo'}
        </h2>
        <form onSubmit={handleSubmit} className="space-y-3">
          <input
            name="placa"
            placeholder="Placa (ej. ABC123)"
            value={form.placa}
            onChange={handleChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          />
          <input
            name="marca"
            placeholder="Marca"
            value={form.marca}
            onChange={handleChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          />
          <input
            name="modelo"
            placeholder="Modelo"
            value={form.modelo}
            onChange={handleChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          />
          <input
            name="color"
            placeholder="Color"
            value={form.color}
            onChange={handleChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          />

          {/* Select dinámico para tipoVehiculo (consumido del backend) */}
          <select
            name="tipoVehiculo"
            value={form.tipoVehiculo}
            onChange={handleChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          >
            <option value="">Seleccione tipo</option>
            {tiposVehiculo.map(tipo => (
              <option key={tipo} value={tipo}>
                {formatTipo(tipo)}
              </option>
            ))}
          </select>

          {/* Selector de propietario */}
          <select
            value={selectedUserId}
            onChange={handleUserChange}
            className="w-full border border-slate-200 rounded-lg p-2"
            required
          >
            <option value="">Seleccione propietario</option>
            {usuarios.map(u => (
              <option key={u.id} value={u.id}>
                {u.documento} - {u.nombre} {u.apellido}
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

export default VehiculosModal;