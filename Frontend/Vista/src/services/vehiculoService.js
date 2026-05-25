import { request } from './api';

export const vehiculoService = {
  getAll: () => request('/vehiculos'),
  getById: (id) => request(`/vehiculos/${id}`),
  create: (data) => request('/vehiculos', 'POST', data),
  update: (id, data) => request(`/vehiculos/${id}`, 'PUT', data),
  delete: (id) => request(`/vehiculos/${id}`, 'DELETE'),

  findByPlaca: (placa) => request(`/vehiculos/placa/${placa}`),
  findByMarca: (marca) => request(`/vehiculos/marca/${marca}`),
  findByModelo: (modelo) => request(`/vehiculos/modelo/${modelo}`),
  findByPlacaContaining: (placa) => request(`/vehiculos/buscar?placa=${placa}`),
};