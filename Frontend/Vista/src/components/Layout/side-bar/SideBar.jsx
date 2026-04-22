import React from 'react';
import { NavLink } from 'react-router-dom';

const SideBar = ({ isOpen, onClose }) => {
  const menuItems = [
    { name: 'Inicio', path: '/dashboard'},
    { name: 'Usuarios', path: '/usuarios'},
    { name: 'Vehículos', path: '/vehiculos'},
    { name: 'Estadías', path: '/estadias',},
    { name: 'Tarifas', path: '/tarifas'},
  ];

  return (
    <>
      {isOpen && (
        <div
          className="fixed inset-0 left-0 right-0 bg-black/50 z-40 transition-opacity duration-300"
          style={{ top: '56px' }}
          onClick={onClose}
        />
      )}

      <aside
        className={`
          fixed left-0 w-72 bg-[#0A2647] shadow-2xl z-50
          transform transition-transform duration-300 ease-in-out
          flex flex-col
          ${isOpen ? 'translate-x-0' : '-translate-x-full'}
        `}
        style={{ top: '56px', height: 'calc(100vh - 56px)' }}
      >
        <div className="flex items-center justify-between p-4 border-b border-[#3498DB]/20">
          <div className="flex items-center gap-2">
            <div className="size-10 flex items-center justify-center rounded-xl bg-[#3498DB]/10">
              <svg className="text-[#3498DB] size-6" fill="currentColor" viewBox="0 0 24 24">
                <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.85 7h10.29l1.04 3H5.81l1.04-3zM19 17H5v-4.5h14V17zM7.5 14c-.83 0-1.5-.67-1.5-1.5S6.67 11 7.5 11s1.5.67 1.5 1.5S8.33 14 7.5 14zm9 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5z"/>
              </svg>
            </div>
            <span className="text-white text-xl font-bold">Parking System</span>
          </div>
          <button
            onClick={onClose}
            className="text-white/70 hover:text-white p-1 rounded-md hover:bg-white/10 transition"
            aria-label="Cerrar menú"
          >
            ✕
          </button>
        </div>

        {/* Navegación */}
        <nav className="flex-1 px-4 py-6 overflow-y-auto">
          <ul className="flex flex-col gap-2">
            {menuItems.map((item) => (
              <li key={item.path}>
                <NavLink
                  to={item.path}
                  onClick={onClose}
                  className={({ isActive }) =>
                    `flex items-center gap-3 px-4 py-3 rounded-lg text-base font-medium transition-all duration-200 ${
                      isActive
                        ? 'bg-[#3498DB] text-white shadow-md'
                        : 'text-slate-200 hover:bg-[#3498DB]/20 hover:text-white'
                    }`
                  }
                >
                  <span className="text-xl">{item.icon}</span>
                  {item.name}
                </NavLink>
              </li>
            ))}
          </ul>
        </nav>

        <div className="p-4 border-t border-[#3498DB]/20">
          <NavLink
            to="/"
            onClick={onClose}
            className="flex items-center justify-center gap-2 w-full px-4 py-2 rounded-lg text-slate-300 hover:bg-red-500/20 hover:text-red-300 transition-colors"
          >
            Cerrar sesión
          </NavLink>
        </div>
      </aside>
    </>
  );
};

export default SideBar;