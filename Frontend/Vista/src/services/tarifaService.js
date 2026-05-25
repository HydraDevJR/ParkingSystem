import { request } from './api';

export const tarifaService = {
  getAll: () => request('/tarifas'),
  getById: (id) => request(`/tarifas/${id}`),
  create: (data) => request('/tarifas', 'POST', data),
  update: (id, data) => request(`/tarifas/${id}`, 'PUT', data),
  delete: (id) => request(`/tarifas/${id}`, 'DELETE'),

  getActivas: () => request('/tarifas/activas'),
  findByTipo: (tipo) => request(`/tarifas/tipo/${tipo}`),
  findByTipoActivo: (tipo) => request(`/tarifas/tipo-activo/${tipo}`),
  findByValorMenorIgual: (maxValor) => request(`/tarifas/por-valor?maxValor=${maxValor}`),
};