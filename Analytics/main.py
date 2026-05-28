from src.api_client import APIClient
from src.data_processor import DataProcessor
from src.charts import ChartGenerator
from src.report_generator import ReportGenerator
import os

def main():
    # Crear directorio de outputs si no existe
    os.makedirs("outputs", exist_ok=True)

    # Consumir API
    client = APIClient()
    estadias = client.get_estadias()
    vehiculos = client.get_vehiculos()

    # Procesar datos
    df_estadias, ingresos_diarios = DataProcessor.procesar_estadias(estadias)
    df_completo = DataProcessor.agregar_tipo_vehiculo(df_estadias, vehiculos)

    # Generar gráficos
    img_linea = ChartGenerator.grafico_linea_ingresos(ingresos_diarios)
    img_barras = ChartGenerator.grafico_barras_promedio_por_tipo(df_completo)

    # Generar reportes
    ReportGenerator.generar_html(ingresos_diarios, img_linea, img_barras)
    ReportGenerator.generar_pdf(img_linea, img_barras)

    print("Análisis completado. Reportes generados en la carpeta 'outputs'.")

if __name__ == "__main__":
    main()