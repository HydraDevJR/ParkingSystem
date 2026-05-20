import Dashboard from '../pages/Dashboard'
import Login from '../pages/Login'
import Register from '../pages/Register'
import Layout from '../components/layout/Layout'
import RegistroEntrada from '../pages/RegistroEntrada'
import Contacto from '../pages/Contacto'
import Ayuda from '../pages/Ayuda'
import Terminos from '../pages/Terminos'


export let router = [
    {
        path: "/",
        element: <Login />
    },
    {
        path: "/register",
        element: <Register />
    },
    {
        path: "/dashboard",
        element: <Layout />,
        children: [
            { index: true, element: <Dashboard /> },
            { path: "registro-entrada", element: <RegistroEntrada /> },
            
        ]
    },
    {
        path: "/contacto",
        element: <Contacto />
    },
    {
        path: "/ayuda",
        element: <Ayuda />
    },
    {
        path: "/terminos",
        element: <Terminos />
    }

]