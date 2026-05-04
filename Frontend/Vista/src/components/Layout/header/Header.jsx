import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getLocalStorage } from '../../../helpers/local-storage';

const Header = ({ onMenuClick }) => {
  const [user, setUser] = useState(null);

  // Cargar usuario desde localStorage al montar el componente
  useEffect(() => {
    const storedUser = getLocalStorage('user');
    setUser(storedUser);
  }, []);

  // Escuchar cambios en localStorage (por si se loguea desde otra pestaña)
  useEffect(() => {
    const handleStorageChange = (e) => {
      if (e.key === 'user') {
        const newUser = getLocalStorage('user');
        setUser(newUser);
      }
    };
    window.addEventListener('storage', handleStorageChange);
    return () => window.removeEventListener('storage', handleStorageChange);
  }, []);

  return (
    <header className="fixed top-0 left-0 w-full z-30 bg-white shadow-md">
      <div className="container mx-auto px-4 py-3 flex items-center justify-between">
        {/* Logo + Hamburguesa juntos a la izquierda */}
        <div className="flex items-center gap-4">
          <button
            className="flex flex-col gap-1.5"
            onClick={onMenuClick}
            aria-label="Abrir menú"
          >
            <span className="w-6 h-0.5 bg-[#0A2647] rounded" />
            <span className="w-6 h-0.5 bg-[#0A2647] rounded" />
            <span className="w-6 h-0.5 bg-[#0A2647] rounded" />
          </button>
          <Link to="/" className="text-2xl font-bold text-[#0A2647]">
            Parking System
          </Link>
        </div>

        {/* Zona derecha: saludo al usuario o botón login */}
        <div>
          {user ? (
            <span className="text-slate-700 font-medium">
              Hola, {user.nombres || user.email || 'Usuario'}
            </span>
          ) : (
            <Link
              to="/"
              className="bg-[#0A2647] text-white px-4 py-2 rounded-lg hover:bg-[#0A2647]/90 transition"
            >
              Iniciar sesión
            </Link>
          )}
        </div>
      </div>
    </header>
  );
};

export default Header;