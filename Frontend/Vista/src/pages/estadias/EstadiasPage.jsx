import { useState, useEffect } from 'react';
import { estadiaService } from '../../services/estadiaService';
import { vehiculoService } from '../../services/vehiculoService';
import { celdaService } from '../../services/celdaService';
import { tarifaService } from '../../services/tarifaService';
import { enumService } from '../../services/enumService';
import EstadiasList from './EstadiasList';
import EstadiasModal from './EstadiasModal';
import FinalizarModal from './FinalizarModal';
import {
  showSuccessAlert,
  showHttpErrorAlert,
  showErrorAlert,
  showInfoAlert
} from '../../helpers/alerts';

const EstadiasPage = () => {
  const [estadias, setEstadias] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [finalizarOpen, setFinalizarOpen] = useState(false);
  const [selectedEstadia, setSelectedEstadia] = useState(null);

  // Datos para los selects del formulario de creación
  const [vehiculos, setVehiculos] = useState([]);
  const [celdas, setCeldas] = useState([]);
  const [tarifas, setTarifas] = useState([]);
  const [estadosEstadia, setEstadosEstadia] = useState([]);
  const [enumsLoading, setEnumsLoading] = useState(true);

  useEffect(() => {
    cargarEstadias();
    cargarDatosAuxiliares();
    cargarEnums();
  }, []);

  const cargarEstadias = async () => {
    setLoading(true);
    try {
      const data = await estadiaService.getAll();
      setEstadias(data);
    } catch (error) {
      console.error(error);
      showHttpErrorAlert(error, 'No se pudieron cargar las estadías');
    } finally {
      setLoading(false);
    }
  };

  const cargarDatosAuxiliares = async () => {
    try {
      const [veh, cel, tar] = await Promise.all([
        vehiculoService.getAll(),
        celdaService.getAll(),
        tarifaService.getAll(),
      ]);
      setVehiculos(veh);
      setCeldas(cel);
      setTarifas(tar);
    } catch (error) {
      console.error('Error cargando datos auxiliares', error);
      showHttpErrorAlert(error, 'No se pudieron cargar los datos necesarios (vehículos, celdas, tarifas). Recarga la página.');
    }
  };

  const cargarEnums = async () => {
    setEnumsLoading(true);
    try {
      const estados = await enumService.getEstadosEstadia();
      setEstadosEstadia(estados);
    } catch (error) {
      console.error('Error cargando estados de estadía', error);
      showHttpErrorAlert(error, 'No se pudieron cargar los estados de estadía');
    } finally {
      setEnumsLoading(false);
    }
  };

  const handleCreate = () => {
    setSelectedEstadia(null);
    setModalOpen(true);
  };

  const handleFinalizar = (estadia) => {
    if (estadia.estado !== 'EN_CURSO') {
      showErrorAlert('Solo se pueden finalizar estadías en curso');
      return;
    }
    setSelectedEstadia(estadia);
    setFinalizarOpen(true);
  };

  const confirmarFinalizar = async () => {
    if (!selectedEstadia) return;
    try {
      await estadiaService.finalizar(selectedEstadia.id);
      setFinalizarOpen(false);
      showSuccessAlert('Estadía finalizada correctamente. Se ha calculado el valor total.');
      cargarEstadias();
    } catch (error) {
      showHttpErrorAlert(error);
    }
  };

  const handleSave = async (estadiaData) => {
    try {
      await estadiaService.create(estadiaData);
      setModalOpen(false);
      showSuccessAlert('Ingreso registrado correctamente');
      cargarEstadias();
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
          <h1 className="text-slate-900 text-2xl font-bold">Estadías</h1>
          <p className="text-slate-600">Registro de ingresos y salidas del parqueadero</p>
        </div>
        <button
          onClick={handleCreate}
          className="bg-[#0A2647] text-white px-4 py-2 rounded-lg text-sm font-semibold hover:bg-[#0A2647]/90"
        >
          + Registrar ingreso
        </button>
      </div>

      <EstadiasList
        estadias={estadias}
        onFinalizar={handleFinalizar}
      />

      <EstadiasModal
        isOpen={modalOpen}
        onClose={() => setModalOpen(false)}
        onSave={handleSave}
        vehiculos={vehiculos}
        celdas={celdas}
        tarifas={tarifas}
        estadosEstadia={estadosEstadia}
      />

      <FinalizarModal
        isOpen={finalizarOpen}
        onClose={() => setFinalizarOpen(false)}
        onConfirm={confirmarFinalizar}
        estadia={selectedEstadia}
      />
    </div>
  );
};

export default EstadiasPage;