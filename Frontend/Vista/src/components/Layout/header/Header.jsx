import React, { useState, useEffect } from 'react';  //Diego
import './Header.css';
import { navLinks, handleResize } from './Header.js';

const Header = () => {
  const [menuOpen, setMenuOpen] = useState(false);

  useEffect(() => {
    const onResize = () => handleResize(setMenuOpen);
    window.addEventListener("resize", onResize);
    return () => window.removeEventListener("resize", onResize);
  }, []);

  return (
    <header className="header">
      <div className="header-inner">

        {/* LOGO */}
        <a className="logo" href="/">
          <div>
            <div className="logo-text">CESDE</div>
            <div className="logo-sub">Parking System</div>
          </div>
        </a>

        {/* NAV DESKTOP */}
        <nav className="nav-desktop">
          {navLinks.map((link) => (
            <a key={link.label} className="nav-link" href={link.href}>
              {link.label}
            </a>
          ))}
          <button className="btn-login">Iniciar sesión</button>
        </nav>

        {/* HAMBURGER */}
        <button
          className={`hamburger ${menuOpen ? "open" : ""}`}
          onClick={() => setMenuOpen(!menuOpen)}
          aria-label="Abrir menú"
        >
          <span />
          <span />
          <span />
        </button>
      </div>

      {/* NAV MOBILE */}
      <nav className={`nav-mobile ${menuOpen ? "open" : ""}`}>
        {navLinks.map((link) => (
          <a
            key={link.label}
            className="nav-link"
            href={link.href}
            onClick={() => setMenuOpen(false)}
          >
            {link.label}
          </a>
        ))}
        <button className="btn-login">Iniciar sesión</button>
      </nav>
    </header>
  );
};

export default Header;