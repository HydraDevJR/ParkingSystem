import { useState, useEffect } from 'react';
import { tarifaService } from '../../services/tarifaService';
import { enumService } from '../../services/enumService';
import TarifasList from './TarifasList';
import TarifasModal from './TarifasModal';
import {
  showSuccessAlert,
  showHttpErrorAlert,
  showConfirmAlert
} from '../../helpers/alerts';

const TarifasPage = () => {
  const [tarifas, setTarifas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [editingTarifa, setEditingTarifa] = useState(null);
  const [tiposTarifa, setTiposTarifa] = useState([]);
  const [enumsLoading, setEnumsLoading] = useState(true);

  useEffect(() => {
    cargarTarifas();
    cargarEnums();
  }, []);

  const cargarTarifas = async () => {
    setLoading(true);
    try {
      const data = await tarifaService.getAll();
      setTarifas(data);
    } catch (error) {
      console.error(error);
      showHttpErrorAlert(error, 'No se pudieron cargar las tarifas');
    } finally {
      setLoading(false);
    }
  };

  const cargarEnums = async () => {
    setEnumsLoading(true);
    try {
      const tipos = await enumService.getTiposTarifa();
      setTiposTarifa(tipos);
    } catch (error) {
      console.error('Error cargando tipos de tarifa', error);
      showHttpErrorAlert(error, 'No se pudieron cargar los tipos de tarifa');
    } finally {
      setEnumsLoading(false);
    }
  };

  const handleCreate = () => {
    setEditingTarifa(null);
    setModalOpen(true);
  };

  const handleEdit = (tarifa) => {
    setEditingTarifa(tarifa);
    setModalOpen(true);
  };

  const handleDelete = async (id) => {
    const confirmed = await showConfirmAlert(
      '¿Eliminar esta tarifa? Podría afectar estadías existentes.',
      'Eliminar tarifa'
    );
    if (!confirmed) return;

    try {
      await tarifaService.delete(id);
      showSuccessAlert('Tarifa eliminada correctamente');
      cargarTarifas();
    } catch (error) {
      showHttpErrorAlert(error);
    }
  };

  const handleSave = async (tarifaData) => {
    try {
      if (editingTarifa) {
        await tarifaService.update(editingTarifa.id, tarifaData);
        showSuccessAlert('Tarifa actualizada correctamente');
      } else {
        await tarifaService.create(tarifaData);
        showSuccessAlert('Tarifa creada correctamente');
      }
      setModalOpen(false);
      cargarTarifas();
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
          <h1 className="text-slate-900 text-2xl font-bold">Tarifas</h1>
          <p className="text-slate-600">Definición de precios por hora, día o mes</p>
        </div>
        <button
          onClick={handleCreate}
          className="bg-[#0A2647] text-white px-4 py-2 rounded-lg text-sm font-semibold hover:bg-[#0A2647]/90"
        >
          + Nueva tarifa
        </button>
      </div>

      <TarifasList
        tarifas={tarifas}
        onEdit={handleEdit}
        onDelete={handleDelete}
      />

      <TarifasModal
        isOpen={modalOpen}
        onClose={() => setModalOpen(false)}
        onSave={handleSave}
        initialData={editingTarifa}
        tiposTarifa={tiposTarifa}
      />
    </div>
  );
};

export default TarifasPage;