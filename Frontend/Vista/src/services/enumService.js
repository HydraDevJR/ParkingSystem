import { request } from './api';

export const enumService = {
  getTiposUsuario: () => request('/enums/tipos-usuario'),
  getTiposVehiculo: () => request('/enums/tipos-vehiculo'),
  getEstadosCelda: () => request('/enums/estados-celda'),
  getTiposTarifa: () => request('/enums/tipos-tarifa'),
  getEstadosEstadia: () => request('/enums/estados-estadia'),
  getTiposDocumento: () => request('/enums/tipos-documento'),
};