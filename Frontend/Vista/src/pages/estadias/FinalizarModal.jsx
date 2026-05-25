const FinalizarModal = ({ isOpen, onClose, onConfirm, estadia }) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-white rounded-xl shadow-xl w-full max-w-sm p-6">
        <h2 className="text-xl font-bold text-slate-900 mb-2">Finalizar estadía</h2>
        <p className="text-slate-600 mb-4">
          ¿Registrar salida del vehículo con placa <strong>{estadia?.vehiculo?.placa}</strong>?
        </p>
        <p className="text-sm text-slate-500 mb-4">
          Se calculará el valor según la tarifa y el tiempo transcurrido.
        </p>
        <div className="flex justify-end gap-2">
          <button onClick={onClose} className="px-4 py-2 border rounded-lg hover:bg-slate-50">Cancelar</button>
          <button onClick={onConfirm} className="px-4 py-2 bg-[#0A2647] text-white rounded-lg hover:bg-[#0A2647]/90">Confirmar salida</button>
        </div>
      </div>
    </div>
  );
};

export default FinalizarModal;