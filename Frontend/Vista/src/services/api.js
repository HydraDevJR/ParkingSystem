const URL_BASE = "http://localhost:8080/parkingsystem/v1";
const ANALYTICS_BASE = "http://localhost:8080"; // porque el controlador de analytics está en la raíz

export const request = async (endpoint, method = 'GET', body = null) => {
  const url = `${URL_BASE}${endpoint}`;
  const options = {
    method,
    headers: { 'Content-Type': 'application/json' },
  };
  if (body) options.body = JSON.stringify(body);

  const response = await fetch(url, options);
  
  if (!response.ok) {
    const error = await response.json().catch(() => ({}));
    throw new Error(error.message || `Error en la petición: ${response.status}`);
  }
  
  if (response.status === 204) return true;
  
  return response.json();
};

export const urlAPI = {
  usuarios: `${URL_BASE}/usuarios`,
  vehiculos: `${URL_BASE}/vehiculos`,
  celdas: `${URL_BASE}/celdas`,
  tarifas: `${URL_BASE}/tarifas`,
  estadias: `${URL_BASE}/estadias`,
};

// Exportamos la base para endpoints de analítica
export const analyticsBase = ANALYTICS_BASE;