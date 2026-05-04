import { useState, useEffect } from "react"
import { urlAPI } from "../services/api"
import { redirectAlert } from "../helpers/alerts"
import { saveLocalStorage } from "../helpers/local-storage"
import { Link } from "react-router-dom"

const Login = () => {
  const [getEmail, setEmail] = useState("")
  const [getPassword, setPassword] = useState("")
  const [users, setUsers] = useState([])

  function getUsers() {
    fetch(urlAPI.users)
      .then((response) => response.json())
      .then((data) => setUsers(data))
  }

  useEffect(() => {
    getUsers()
  }, [])

  function findUser() {
    let auth = users.find((item) => getEmail == item.email && getPassword == item.password)
    return auth
  }

  function signIn() {
    let user = findUser()
    if (user) {
      saveLocalStorage("user", user)
      redirectAlert("Bienvenido al sistema " + user.nombres, "Iniciando sesión", "success", "/dashboard")
    } else {
      redirectAlert("Error de credenciales", "Intente nuevamente", "error", "/")
    }
  }

  console.log(users)

  return (
    <div>
      <div className="relative flex min-h-screen w-full flex-col overflow-x-hidden">
        <div className="layout-container flex h-full grow flex-col items-center justify-center px-4">
          <div className="w-full max-w-[440px] flex flex-col gap-8">
            <header className="flex flex-col items-center gap-6">
              <div className="size-12 flex items-center justify-center rounded-xl bg-[#3498DB]/10">
                <svg className="text-[#3498DB] size-8" fill="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.85 7h10.29l1.04 3H5.81l1.04-3zM19 17H5v-4.5h14V17zM7.5 14c-.83 0-1.5-.67-1.5-1.5S6.67 11 7.5 11s1.5.67 1.5 1.5S8.33 14 7.5 14zm9 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5z"/>
                </svg>
              </div>
              <div className="flex flex-col gap-2 text-center">
                <h1 className="text-slate-900 dark:text-slate-400 text-3xl font-bold tracking-tight">Parking System</h1>
                <p className="text-slate-300 dark:text-slate-500 text-base">Please enter your details to sign in</p>
              </div>
            </header>
            <div className="flex flex-col gap-5">
                <div className="flex flex-col gap-2">
                    <div className="flex items-center justify-between">
                    <label className="text-slate-700 dark:text-slate-300 text-sm font-semibold leading-none">
                        Email
                    </label>
                    </div>
                    <input
                    onChange={(e) => setEmail(e.target.value)}
                    className="flex w-full rounded-lg border border-slate-200 bg-white px-4 py-2 text-lg ring-offset-white placeholder:text-slate-500 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-[#3498DB] focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 min-h-[40px]"
                    placeholder="  Email"
                    type="email"
                    />
                </div>
                <div className="flex flex-col gap-2">
                    <div className="flex items-center justify-between">
                    <label className="text-slate-700 dark:text-slate-300 text-sm font-semibold leading-none">
                        Password
                    </label>
                    </div>
                    <input
                    onChange={(e) => setPassword(e.target.value)}
                    className="flex w-full rounded-lg border border-slate-200 bg-white px-4 py-2 text-lg ring-offset-white placeholder:text-slate-500 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-[#3498DB] focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 min-h-[40px]"
                    placeholder="  ••••••••"
                    type="password"
                    />
                </div>
                <div className="flex flex-col gap-4 pt-4">
                    <button
                    onClick={signIn}
                    type="button"
                    className="inline-flex items-center justify-center rounded-lg bg-[#0A2647] px-4 py-2 text-lg font-bold text-white transition-colors hover:bg-[#0A2647]/90 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-[#0A2647] focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 min-h-[40px]"
                    >
                    Log In
                    </button>
                </div>
            </div>
            <footer className="text-center">
              <p className="text-sm text-slate-600 dark:text-slate-400">
                Don't have an account?
                <Link className="font-semibold text-[#3498DB] hover:underline ml-1" to="/register">
                    <br />Sign up free
                </Link>
              </p>
            </footer>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Login