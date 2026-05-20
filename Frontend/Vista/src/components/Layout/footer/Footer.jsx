import React from 'react';
import { Link } from 'react-router-dom';
import './Footer.css';

export default function Footer() {
    return (
        <footer className="Footer">
            <div className="Footer__inner">
                <div className="Footer__brand">
                    <strong>ParkingSystem</strong>
                    <div className="Footer__tag">Gestiona tus espacios con estilo</div>
                </div>

                <nav className="Footer__links" aria-label="enlaces">
                    <Link to="/contacto">Contacto</Link>
                    <Link to="/ayuda">Ayuda</Link>
                    <Link to="/terminos">Términos</Link>
                </nav>

                <div className="Footer__social" aria-hidden="true">
                    {/* ...social icons... */}
                </div>
            </div>

            <div className="Footer__bottom">
                <span>© ParkingSystem · Todos los derechos reservados</span>
            </div>
        </footer>
    );
}

