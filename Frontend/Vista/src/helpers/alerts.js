import Swal from 'sweetalert2';

/**
 * Muestra una alerta de error genérica
 * @param {string} message - Mensaje a mostrar
 * @param {string} title - Título (opcional, por defecto "Error")
 */
export const showErrorAlert = (message, title = 'Error') => {
    Swal.fire({
        title,
        text: message,
        icon: 'error',
        confirmButtonColor: '#0A2647',
        confirmButtonText: 'Entendido',
        backdrop: true,
    });
};

/**
 * Muestra una alerta de éxito
 * @param {string} message - Mensaje a mostrar
 * @param {string} title - Título (opcional, por defecto "Éxito")
 * @param {string} redirectUrl - URL opcional a redirigir después de cerrar
 */
export const showSuccessAlert = (message, title = 'Éxito', redirectUrl = null) => {
    const swalOptions = {
        title,
        text: message,
        icon: 'success',
        confirmButtonColor: '#0A2647',
        confirmButtonText: 'OK',
        backdrop: true,
    };
    
    if (redirectUrl) {
        swalOptions.timer = 2000;
        swalOptions.willClose = () => {
            window.location.href = redirectUrl;
        };
    }
    
    Swal.fire(swalOptions);
};

/**
 * Alerta de confirmación para acciones peligrosas (eliminar, etc.)
 * @param {string} message - Mensaje de confirmación
 * @param {string} title - Título (opcional)
 * @returns {Promise<boolean>} - true si el usuario confirma, false si cancela
 */
export const showConfirmAlert = async (message, title = '¿Estás seguro?') => {
    const result = await Swal.fire({
        title,
        text: message,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#0A2647',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar',
        backdrop: true,
    });
    return result.isConfirmed;
};

/**
 * Alerta de error HTTP que extrae el mensaje del backend automáticamente
 * @param {Error|Object} error - Error capturado en el catch
 * @param {string} defaultMessage - Mensaje por defecto si no se puede extraer
 */
export const showHttpErrorAlert = (error, defaultMessage = 'Ocurrió un error inesperado') => {
    let message = defaultMessage;
    
    // Intentar extraer mensaje del error
    if (error.message) {
        message = error.message;
    } else if (typeof error === 'string') {
        message = error;
    } else if (error.response?.data?.message) {
        message = error.response.data.message;
    } else if (error.response?.data) {
        // Si el backend devuelve un string plano
        message = error.response.data;
    }
    
    Swal.fire({
        title: 'Error',
        text: message,
        icon: 'error',
        confirmButtonColor: '#0A2647',
        confirmButtonText: 'Entendido',
        backdrop: true,
    });
};

/**
 * Alerta de información (aviso, consejos, etc.)
 * @param {string} message - Mensaje a mostrar
 * @param {string} title - Título (opcional)
 */
export const showInfoAlert = (message, title = 'Información') => {
    Swal.fire({
        title,
        text: message,
        icon: 'info',
        confirmButtonColor: '#0A2647',
        confirmButtonText: 'Entendido',
        backdrop: true,
    });
};

/**
 * @param {string} title - Título
 * @param {string} message - Mensaje
 * @param {string} icon - Icono ('success', 'error', 'warning', 'info')
 * @param {string} url - URL a redirigir después del tiempo
 * @param {number} timer - Tiempo en ms (default 2000)
 */
export const redirectAlert = (title, message, icon, url, timer = 2000) => {
    Swal.fire({
        title,
        html: message,
        timer,
        timerProgressBar: true,
        icon,
        didOpen: () => {
            Swal.showLoading();
        },
        willClose: () => {
            window.location.href = url;
        }
    });
};