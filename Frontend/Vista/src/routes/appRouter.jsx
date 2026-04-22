import Dashboard from '../pages/Dashboard'
import Login from '../pages/Login'
import Register from '../pages/Register'
import Layout from '../components/layout/Layout'
import RegistroEntrada from '../pages/RegistroEntrada'

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

]