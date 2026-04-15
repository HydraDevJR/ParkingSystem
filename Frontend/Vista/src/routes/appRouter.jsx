//Hacer import de los componentes a usar en las rutas
import App from "../App"
import Login from "../pages/Login"

export let router = [ // Rutas para pages
    {
        path: "/",
        element: <Login />
    }
]