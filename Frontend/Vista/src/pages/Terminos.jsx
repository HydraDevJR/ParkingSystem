import React from 'react';
import "../components/Layout/footer/Footer.css";


export default function Terminos() {
  return (
    <main className="page page--terminos">
      <header className="page__hero">
        <h1>Términos y Condiciones</h1>
        <p>Resumen y políticas de uso del servicio.</p>
      </header>

      <article className="terms-card">
        <section>
          <h3>1. Aceptación</h3>
          <p>Al usar ParkingSystem aceptas estas condiciones.</p>
        </section>

        <section>
          <h3>2. Uso del servicio</h3>
          <p>Las reservas están sujetas a disponibilidad y tiempo límite.</p>
        </section>

        <section>
          <h3>3. Limitación de responsabilidad</h3>
          <p>ParkingSystem no se responsabiliza de daños en plazas públicas.</p>
        </section>
        <footer className="terms-footer">Última actualización: 2026</footer>
      </article>
    </main>
  );
}