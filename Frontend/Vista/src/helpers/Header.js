// Links de navegación
export const navLinks = [
  { label: "Servicios", href: "#servicios" },
  { label: "Nosotros", href: "#nosotros" },
  { label: "Contacto", href: "#contacto" },
];

// Cierra el menú móvil si se redimensiona a desktop
export function handleResize(setMenuOpen) {
  if (window.innerWidth > 768) {
    setMenuOpen(false);
  }
}