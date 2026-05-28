import { analyticsBase, request } from './api';

export const analiticaService = {
  regenerarGraficos: () => fetch(`${analyticsBase}/analytics/refresh`, { method: 'POST' }).then(res => {
    if (!res.ok) throw new Error('Error al regenerar gráficos');
    return res.text();
  }),
};