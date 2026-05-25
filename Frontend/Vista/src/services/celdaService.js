import { request } from './api';

export const celdaService = {
  getAll: () => request('/celdas'),
  getById: (id) => request(`/celdas/${id}`),
  create: (data) => request('/celdas', 'POST', data),
  update: (id, data) => request(`/celdas/${id}`, 'PUT', data),
  delete: (id) => request(`/celdas/${id}`, 'DELETE'),

  findByCodigo: (codigo) => request(`/celdas/codigo/${codigo}`),
  findByEstado: (estado) => request(`/celdas/estado/${estado}`),
  findByTipoVehiculo: (tipo) => request(`/celdas/tipo/${tipo}`),
  findByCodigoAndEstado: (codigo, estado) => request(`/celdas/buscar?codigo=${codigo}&estado=${estado}`),
  getDisponiblesByTipo: (tipo) => request(`/celdas/disponibles/${tipo}`),
};