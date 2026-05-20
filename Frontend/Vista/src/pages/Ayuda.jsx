import React from 'react';
import { Link } from 'react-router-dom';


const faqs = [
  { q: '¿Cómo reservo una plaza?', a: 'Selecciona la plaza en el mapa y confirma la reserva.' },
  { q: '¿Cómo pago?', a: 'Aceptamos tarjeta y PayPal; se mostrará el método al confirmar.' },
  { q: '¿Puedo cancelar?', a: 'Puedes cancelar hasta 30 min antes sin coste.' },
];

export default function Ayuda() {
  return (
    <main className="page page--ayuda">
      <header className="page__hero">
        <h1>Ayuda</h1>
        <p>Preguntas frecuentes y recursos rápidos.</p>
      </header>

      <section className="faq-list">
        {faqs.map((f, i) => (
          <details key={i} className="faq-item">
            <summary>{f.q}</summary>
            <div className="faq-answer">{f.a}</div>
          </details>
        ))}
      </section>
    </main>
  );
}