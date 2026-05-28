import Dashboard from '../pages/Dashboard'
import UsuariosPage from '../pages/usuarios/UsuariosPage'
import VehiculosPage from '../pages/vehiculos/VehiculosPage'
import EstadiasPage from '../pages/estadias/EstadiasPage'
import TarifasPage from '../pages/tarifas/TarifasPage'
import CeldasPage from '../pages/celdas/CeldasPage'
import Login from '../pages/Login'
import Register from '../pages/Register'
import AnaliticaPage from '../pages/analiticas/AnaliticaPage'
import Layout from '../components/layout/Layout'


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
        ]
    },
    {
        path: "/usuarios",
        element: <Layout />,
        children: [
            { index: true, element: <UsuariosPage /> },
        ]
    },
    {
        path: "/vehiculos",
        element: <Layout />,
        children: [
            { index: true, element: <VehiculosPage /> },
        ]
    },
    {
        path: "/estadias",
        element: <Layout />,
        children: [
            { index: true, element: <EstadiasPage /> },
        ]
    },
    {
        path: "/tarifas",
        element: <Layout />,
        children: [
            { index: true, element: <TarifasPage /> },
        ]
    },
    {
        path: "/celdas",
        element: <Layout />,
        children: [
            { index: true, element: <CeldasPage /> },
        ]
    },
    {
        path: "/analiticas",
        element: <Layout />,
        children: [
            { index: true, element: <AnaliticaPage /> },
        ]
    }


]