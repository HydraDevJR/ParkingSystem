const URL_BASE = "https://app-gastos-personales-api.onrender.com" + "/"

export let urlAPI = {
    users: URL_BASE + "users/", /* localhost:8080/users/ */
    merchants: URL_BASE + "merchants/",
    expenses: URL_BASE + "expenses/",
    payment_methods: URL_BASE + "payment_methods/",
    category: URL_BASE + "category/"
}