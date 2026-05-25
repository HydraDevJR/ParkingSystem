import { RouterProvider } from 'react-router-dom';
import { router } from './routes/appRoutes';
import Contacto from './pages/Contacto.jsx';
import Ayuda from './pages/Ayuda.jsx';
import Terminos from './pages/Terminos.jsx';

export default function App() {
  return (
    <RouterProvider router={router} />
  );
}

