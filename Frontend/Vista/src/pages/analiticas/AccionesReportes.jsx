import { useState } from 'react';
import { showSuccessAlert, showHttpErrorAlert } from '../../helpers/alerts';
import { analyticsBase } from '../../services/api';

const AccionesReportes = () => {
  const [downloading, setDownloading] = useState({ html: false, pdf: false });

  const handleDownload = async (tipo, filename) => {
    setDownloading(prev => ({ ...prev, [tipo]: true }));
    try {
      const response = await fetch(`${analyticsBase}/analytics/${filename}`);
      if (!response.ok) throw new Error(`Error ${response.status}`);
      const blob = await response.blob();
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = filename;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
      showSuccessAlert(`Reporte ${filename} descargado correctamente`);
    } catch (error) {
      showHttpErrorAlert(error, `No se pudo descargar ${filename}`);
    } finally {
      setDownloading(prev => ({ ...prev, [tipo]: false }));
    }
  };

  return (
    <div className="bg-white rounded-xl border border-slate-200 p-5 shadow-sm">
      <h2 className="text-slate-900 text-base font-bold mb-3">Descarga de reportes</h2>
      <div className="flex flex-wrap gap-4">
        <button
          onClick={() => handleDownload('html', 'reporte.html')}
          disabled={downloading.html}
          className="inline-flex items-center justify-center rounded-lg bg-[#0A2647] px-4 py-2 text-sm font-semibold text-white hover:bg-[#0A2647]/90 disabled:opacity-50"
        >
          {downloading.html ? 'Descargando...' : '📄 Descargar reporte HTML'}
        </button>
        <button
          onClick={() => handleDownload('pdf', 'reporte.pdf')}
          disabled={downloading.pdf}
          className="inline-flex items-center justify-center rounded-lg bg-[#0A2647] px-4 py-2 text-sm font-semibold text-white hover:bg-[#0A2647]/90 disabled:opacity-50"
        >
          {downloading.pdf ? 'Descargando...' : '📑 Descargar reporte PDF'}
        </button>
      </div>
      <p className="text-xs text-slate-500 mt-3">
        Los reportes se generan con los datos más recientes. Puedes actualizar los gráficos usando el botón "Actualizar gráficos".
      </p>
    </div>
  );
};

export default AccionesReportes;