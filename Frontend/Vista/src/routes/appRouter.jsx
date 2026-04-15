//Hacer import de los componentes a usar en las rutas
import App from "../App"
import Login from "../pages/Login"
import Register from "../pages/Register"

export let router = [ // Rutas para pages
    {
        path: "/",
        element: <Login />
    },
    {
        path: "/register",
        element: <Register />
    }
]