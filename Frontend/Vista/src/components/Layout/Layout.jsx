import { useState } from 'react';
import { Outlet } from 'react-router-dom';
import Header from './header/Header';
import SideBar from './side-bar/SideBar';
import Footer from './footer/Footer';

const Layout = () => {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);
  const openSidebar = () => setIsSidebarOpen(true);
  const closeSidebar = () => setIsSidebarOpen(false);

  return (
    <div className="h-full flex flex-col bg-slate-100">
      <Header onMenuClick={openSidebar} />
      <SideBar isOpen={isSidebarOpen} onClose={closeSidebar} />
      <main className="flex-1 pt-20 px-4 md:px-6 pb-8">   {/* ← pb-8 agrega espacio abajo */}
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default Layout;