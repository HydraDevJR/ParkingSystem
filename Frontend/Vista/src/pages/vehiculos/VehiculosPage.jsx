import { useState, useEffect } from 'react';
import { vehiculoService } from '../../services/vehiculoService';
import { usuarioService } from '../../services/usuarioService';
import { enumService } from '../../services/enumService';
import VehiculosList from './VehiculosList';
import VehiculosModal from './VehiculosModal';
import {
  showSuccessAlert,
  showHttpErrorAlert,
  showConfirmAlert
} from '../../helpers/alerts';

const VehiculosPage = () => {
  const [vehiculos, setVehiculos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [editingVehiculo, setEditingVehiculo] = useState(null);
  const [usuarios, setUsuarios] = useState([]);
  const [tiposVehiculo, setTiposVehiculo] = useState([]);
  const [enumsLoading, setEnumsLoading] = useState(true);

  useEffect(() => {
    cargarVehiculos();
    cargarUsuarios();
    cargarEnums();
  }, []);

  const cargarVehiculos = async () => {
    setLoading(true);
    try {
      const data = await vehiculoService.getAll();
      setVehiculos(data);
    } catch (error) {
      console.error(error);
      showHttpErrorAlert(error, 'No se pudieron cargar los vehículos');
    } finally {
      setLoading(false);
    }
  };

  const cargarUsuarios = async () => {
    try {
      const users = await usuarioService.getAll();
      setUsuarios(users);
    } catch (error) {
      console.error('Error cargando usuarios', error);
      showHttpErrorAlert(error, 'No se pudieron cargar los usuarios propietarios');
    }
  };

  const cargarEnums = async () => {
    setEnumsLoading(true);
    try {
      const tipos = await enumService.getTiposVehiculo();
      setTiposVehiculo(tipos);
    } catch (error) {
      console.error('Error cargando tipos de vehículo', error);
      showHttpErrorAlert(error, 'No se pudieron cargar los tipos de vehículo');
    } finally {
      setEnumsLoading(false);
    }
  };

  const handleCreate = () => {
    setEditingVehiculo(null);
    setModalOpen(true);
  };

  const handleEdit = (vehiculo) => {
    setEditingVehiculo(vehiculo);
    setModalOpen(true);
  };

  const handleDelete = async (id) => {
    const confirmed = await showConfirmAlert(
      '¿Eliminar este vehículo? Perderás el registro de sus estadías asociadas.',
      'Eliminar vehículo'
    );
    if (!confirmed) return;

    try {
      await vehiculoService.delete(id);
      showSuccessAlert('Vehículo eliminado correctamente');
      cargarVehiculos();
    } catch (error) {
      showHttpErrorAlert(error);
    }
  };

  const handleSave = async (vehiculoData) => {
    try {
      if (editingVehiculo) {
        await vehiculoService.update(editingVehiculo.id, vehiculoData);
        showSuccessAlert('Vehículo actualizado correctamente');
      } else {
        await vehiculoService.create(vehiculoData);
        showSuccessAlert('Vehículo creado correctamente');
      }
      setModalOpen(false);
      cargarVehiculos();
    } catch (error) {
      showHttpErrorAlert(error);
    }
  };

  if (loading || enumsLoading) {
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
          <h1 className="text-slate-900 text-2xl font-bold">Vehículos</h1>
          <p className="text-slate-600">Gestión de parque automotor</p>
        </div>
        <button
          onClick={handleCreate}
          className="bg-[#0A2647] text-white px-4 py-2 rounded-lg text-sm font-semibold hover:bg-[#0A2647]/90"
        >
          + Nuevo vehículo
        </button>
      </div>

      <VehiculosList
        vehiculos={vehiculos}
        onEdit={handleEdit}
        onDelete={handleDelete}
      />

      <VehiculosModal
        isOpen={modalOpen}
        onClose={() => setModalOpen(false)}
        onSave={handleSave}
        initialData={editingVehiculo}
        usuarios={usuarios}
        tiposVehiculo={tiposVehiculo}
      />
    </div>
  );
};

export default VehiculosPage;