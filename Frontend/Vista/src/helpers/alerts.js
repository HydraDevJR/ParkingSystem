import Swal from "sweetalert2";

export function redirectAlert(title, message, icon, url){
    let timerInterval;
    Swal.fire({
    title,
    html: message,
    timer: 2000,
    timerProgressBar: true,
    icon,
    didOpen: () => {
        Swal.showLoading();
        const timer = Swal.getPopup().querySelector("b");
        timerInterval = setInterval(() => {
        timer.textContent = `${Swal.getTimerLeft()}`;
        }, 100);
    },
    willClose: () => {
        clearInterval(timerInterval);
        window.location.href = url; //Como hacer la redireccion con useNavigate o Navigate
    }
    })
}

export function generalAlert(){

}