import React from 'react';

export default function Contacto() {
  return (
    <main className="page page--contacto">
      <header className="page__hero">
        <h1>Contacto</h1>
        <p>Escribe tu consulta y te responderemos pronto.</p>
      </header>

      <section className="contact-card">
        <form className="contact-form" onSubmit={(e)=> e.preventDefault()}>
          <label>
            Nombre
            <input type="text" name="nombre" placeholder="Tu nombre" />
          </label>
          <label>
            Email
            <input type="email" name="email" placeholder="tucorreo@ejemplo.com" />
          </label>
          <label>
            Mensaje
            <textarea name="mensaje" rows="5" placeholder="Escribe tu mensaje..." />
          </label>
          <div className="contact-actions">
            <button type="submit" className="btn-primary">Enviar</button>
            <button type="button" className="btn-ghost">Cancelar</button>
          </div>
        </form>

        <aside className="contact-info">
          <p>soporte@parkingsystem.local</p>
          <p>Tel: +34 600 000 000</p>
        </aside>
      </section>
    </main>
  );
}