/*Crear, Consultar, Eliminar*/

export function saveLocalStorage(key, value){
    localStorage.setItem(key, JSON.stringify(value))
}

export function getLocalStorage(key){
    const item = localStorage.getItem(key)
    return item ? JSON.parse(item) : null
}

export function removeLocalStorage(key){
    localStorage.removeItem(key)
}