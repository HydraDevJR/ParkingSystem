import React, { useState } from 'react';
import Header from './header/Header';
import SideBar from './side-bar/SideBar';

const Layout = ({ children }) => {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);

  const openSidebar = () => setIsSidebarOpen(true);
  const closeSidebar = () => setIsSidebarOpen(false);

  return (
    <div className="min-h-screen bg-slate-100">
      <Header onMenuClick={openSidebar} />
      <SideBar isOpen={isSidebarOpen} onClose={closeSidebar} />
      <main className="pt-20 px-4 md:px-6">
        {children}
      </main>
    </div>
  );
};

export default Layout;