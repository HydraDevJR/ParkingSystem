import { request } from './api';

export const estadiaService = {
  getAll: () => request('/estadias'),
  getById: (id) => request(`/estadias/${id}`),
  create: (data) => request('/estadias', 'POST', data),
  update: (id, data) => request(`/estadias/${id}`, 'PUT', data),
  delete: (id) => request(`/estadias/${id}`, 'DELETE'),

  finalizar: (id) => request(`/estadias/${id}/finalizar`, 'PATCH'),
  getActivas: () => request('/estadias/activas'),
  getByVehiculo: (vehiculoId) => request(`/estadias/vehiculo/${vehiculoId}`),
  getByCelda: (celdaId) => request(`/estadias/celda/${celdaId}`),
  getByEstado: (estado) => request(`/estadias/estado/${estado}`),
  getByRangoFechas: (inicio, fin) => request(`/estadias/rango-fechas?inicio=${inicio}&fin=${fin}`),
  getFinalizadasPorRango: (inicio, fin) => request(`/estadias/finalizadas-rango?inicio=${inicio}&fin=${fin}`),
  getActivasPorVehiculo: (vehiculoId) => request(`/estadias/vehiculo/${vehiculoId}/activas`),
  getByTarifa: (tarifaId) => request(`/estadias/tarifa/${tarifaId}`),
};