// src/pages/celdas/CeldasPage.jsx
import { useState, useEffect } from 'react';
import { celdaService } from '../../services/celdaService';
import { enumService } from '../../services/enumService';
import CeldasList from './CeldasList';
import CeldasModal from './CeldasModal';
import {
  showSuccessAlert,
  showHttpErrorAlert,
  showConfirmAlert
} from '../../helpers/alerts';

const CeldasPage = () => {
  const [celdas, setCeldas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [editingCelda, setEditingCelda] = useState(null);
  const [tiposVehiculo, setTiposVehiculo] = useState([]);
  const [estadosCelda, setEstadosCelda] = useState([]);
  const [enumsLoading, setEnumsLoading] = useState(true);

  useEffect(() => {
    cargarCeldas();
    cargarEnums();
  }, []);

  const cargarCeldas = async () => {
    setLoading(true);
    try {
      const data = await celdaService.getAll();
      setCeldas(data);
    } catch (error) {
      console.error(error);
      showHttpErrorAlert(error, 'No se pudieron cargar las celdas');
    } finally {
      setLoading(false);
    }
  };

  const cargarEnums = async () => {
    setEnumsLoading(true);
    try {
      const [tipos, estados] = await Promise.all([
        enumService.getTiposVehiculo(),
        enumService.getEstadosCelda(),
      ]);
      setTiposVehiculo(tipos);
      setEstadosCelda(estados);
    } catch (error) {
      console.error('Error cargando enums para celdas', error);
      showHttpErrorAlert(error, 'No se pudieron cargar los tipos de vehículo o estados de celda');
    } finally {
      setEnumsLoading(false);
    }
  };

  const handleCreate = () => {
    setEditingCelda(null);
    setModalOpen(true);
  };

  const handleEdit = (celda) => {
    setEditingCelda(celda);
    setModalOpen(true);
  };

  const handleDelete = async (id) => {
    const confirmed = await showConfirmAlert(
      '¿Eliminar esta celda? Si tiene estadías asociadas no se podrá eliminar.',
      'Eliminar celda'
    );
    if (!confirmed) return;

    try {
      await celdaService.delete(id);
      showSuccessAlert('Celda eliminada correctamente');
      cargarCeldas();
    } catch (error) {
      showHttpErrorAlert(error);
    }
  };

  const handleSave = async (celdaData) => {
    try {
      if (editingCelda) {
        await celdaService.update(editingCelda.id, celdaData);
        showSuccessAlert('Celda actualizada correctamente');
      } else {
        await celdaService.create(celdaData);
        showSuccessAlert('Celda creada correctamente');
      }
      setModalOpen(false);
      cargarCeldas();
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

  const disponibles = celdas.filter(c => c.estado === 'DISPONIBLE').length;
  const ocupadas = celdas.filter(c => c.estado === 'OCUPADA').length;
  const reservadas = celdas.filter(c => c.estado === 'RESERVADA').length;

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <div>
          <h1 className="text-slate-900 text-2xl font-bold">Celdas</h1>
          <p className="text-slate-600">Gestión de espacios de parqueadero</p>
        </div>
        <button
          onClick={handleCreate}
          className="bg-[#0A2647] text-white px-4 py-2 rounded-lg text-sm font-semibold hover:bg-[#0A2647]/90"
        >
          + Nueva celda
        </button>
      </div>

      {/* Resumen de celdas usando los valores reales de los enums */}
      <div className="grid grid-cols-3 gap-4">
        <div className="bg-white rounded-xl border border-slate-200 p-3 text-center">
          <p className="text-xs text-slate-500">Total</p>
          <p className="text-xl font-bold">{celdas.length}</p>
        </div>
        <div className="bg-white rounded-xl border border-slate-200 p-3 text-center">
          <p className="text-xs text-slate-500">Disponibles</p>
          <p className="text-xl font-bold text-green-600">{disponibles}</p>
        </div>
        <div className="bg-white rounded-xl border border-slate-200 p-3 text-center">
          <p className="text-xs text-slate-500">Ocupadas</p>
          <p className="text-xl font-bold text-red-600">{ocupadas}</p>
        </div>
        {reservadas > 0 && (
          <div className="bg-white rounded-xl border border-slate-200 p-3 text-center">
            <p className="text-xs text-slate-500">Reservadas</p>
            <p className="text-xl font-bold text-yellow-600">{reservadas}</p>
          </div>
        )}
      </div>

      <CeldasList
        celdas={celdas}
        onEdit={handleEdit}
        onDelete={handleDelete}
      />

      <CeldasModal
        isOpen={modalOpen}
        onClose={() => setModalOpen(false)}
        onSave={handleSave}
        initialData={editingCelda}
        tiposVehiculo={tiposVehiculo}
        estadosCelda={estadosCelda}
      />
    </div>
  );
};

export default CeldasPage;