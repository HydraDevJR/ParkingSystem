import { useState } from 'react';
import { Outlet } from 'react-router-dom';
import Header from './header/Header';
import SideBar from './side-bar/SideBar';

const Layout = () => {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);
  const openSidebar = () => setIsSidebarOpen(true);
  const closeSidebar = () => setIsSidebarOpen(false);

  return (
    <div className="min-h-screen bg-slate-100">
      <Header onMenuClick={openSidebar} />
      <SideBar isOpen={isSidebarOpen} onClose={closeSidebar} />
      <main className="pt-20 px-4 md:px-6">
        <Outlet /> {/* Aquí se renderizan las rutas hijas */}
      </main>
    </div>
  );
};

export default Layout;