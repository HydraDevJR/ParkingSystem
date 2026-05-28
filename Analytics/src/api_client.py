import requests

class APIClient:
    def __init__(self, base_url="http://localhost:8080/parkingsystem/v1"):
        self.base_url = base_url

    def get_estadias(self):
        url = f"{self.base_url}/estadias"
        response = requests.get(url)
        response.raise_for_status()
        return response.json()

    def get_vehiculos(self):
        url = f"{self.base_url}/vehiculos"
        response = requests.get(url)
        response.raise_for_status()
        return response.json()