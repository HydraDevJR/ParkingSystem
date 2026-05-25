import { request } from './api';

export const usuarioService = {
  getAll: () => request('/usuarios'),
  getById: (id) => request(`/usuarios/${id}`),
  create: (data) => request('/usuarios', 'POST', data),
  update: (id, data) => request(`/usuarios/${id}`, 'PUT', data),
  delete: (id) => request(`/usuarios/${id}`, 'DELETE'),
};