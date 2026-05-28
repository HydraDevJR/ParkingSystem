import os
import base64
import pandas as pd
from jinja2 import Template
from reportlab.lib.pagesizes import letter
from reportlab.pdfgen import canvas
from reportlab.lib.utils import ImageReader

class ReportGenerator:
    @staticmethod
    def generar_html(df_resumen, img_linea_path, img_barras_path, output_path="outputs/reporte.html"):
        """
        Genera un reporte HTML con las imágenes incrustadas en Base64.
        """
        # Verificar que los archivos de imagen existen
        if not os.path.exists(img_linea_path):
            raise FileNotFoundError(f"No se encuentra la imagen: {img_linea_path}")
        if not os.path.exists(img_barras_path):
            raise FileNotFoundError(f"No se encuentra la imagen: {img_barras_path}")

        # Leer y codificar imágenes en Base64
        with open(img_linea_path, "rb") as f:
            img_linea_b64 = base64.b64encode(f.read()).decode('utf-8')
        with open(img_barras_path, "rb") as f:
            img_barras_b64 = base64.b64encode(f.read()).decode('utf-8')

        # Plantilla HTML con estilo y marcado para incrustar las imágenes
        html_template = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Reporte Analítico - Parqueadero</title>
            <style>
                body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; }
                h1 { color: #0A2647; border-bottom: 2px solid #0A2647; }
                h2, h3 { color: #0A2647; margin-top: 20px; }
                .grafico { margin: 30px 0; text-align: center; background: #f9f9f9; padding: 15px; border-radius: 8px; }
                img { max-width: 90%; border: 1px solid #ccc; border-radius: 8px; box-shadow: 2px 2px 5px rgba(0,0,0,0.1); }
                table { border-collapse: collapse; width: 70%; margin: 20px auto; font-size: 14px; }
                th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
                th { background-color: #f2f2f2; }
                footer { margin-top: 50px; font-size: 12px; text-align: center; color: #888; }
            </style>
        </head>
        <body>
            <h1>Reporte de Análisis - Parking System</h1>
            <p><strong>Fecha de generación:</strong> {{ fecha }}</p>
            <h2>Resumen de Ingresos Diarios</h2>
            {{ tabla_resumen | safe }}
            <div class="grafico">
                <h3>Evolución de Ingresos Diarios</h3>
                <img src="data:image/png;base64,{{ img_linea_b64 }}" alt="Gráfico de líneas - Ingresos diarios">
            </div>
            <div class="grafico">
                <h3>Promedio de Ingresos por Tipo de Vehículo</h3>
                <img src="data:image/png;base64,{{ img_barras_b64 }}" alt="Gráfico de barras - Promedio por tipo">
            </div>
            <footer>
                <p>Reporte generado automáticamente por Parking System Analytics</p>
            </footer>
        </body>
        </html>
        """
        template = Template(html_template)
        # Convertir DataFrame a tabla HTML
        tabla_html = df_resumen.to_html(index=False, classes='tabla', border=0)
        html_content = template.render(
            fecha=pd.Timestamp.now().strftime("%Y-%m-%d %H:%M:%S"),
            tabla_resumen=tabla_html,
            img_linea_b64=img_linea_b64,
            img_barras_b64=img_barras_b64
        )

        # Asegurar que el directorio de salida existe
        os.makedirs(os.path.dirname(output_path), exist_ok=True)
        with open(output_path, 'w', encoding='utf-8') as f:
            f.write(html_content)
        return output_path

    @staticmethod
    def generar_pdf(img_linea_path, img_barras_path, output_path="outputs/reporte.pdf"):
        """
        Genera un reporte PDF con los dos gráficos insertados.
        """
        # Verificar que los archivos de imagen existen
        if not os.path.exists(img_linea_path):
            raise FileNotFoundError(f"No se encuentra la imagen: {img_linea_path}")
        if not os.path.exists(img_barras_path):
            raise FileNotFoundError(f"No se encuentra la imagen: {img_barras_path}")

        # Crear directorio de salida si no existe
        os.makedirs(os.path.dirname(output_path), exist_ok=True)
        c = canvas.Canvas(output_path, pagesize=letter)
        width, height = letter

        # Título
        y = height - 50
        c.setFont("Helvetica-Bold", 16)
        c.drawString(50, y, "Reporte Analítico - Parking System")
        y -= 30
        c.setFont("Helvetica", 10)
        c.drawString(50, y, f"Generado: {pd.Timestamp.now().strftime('%Y-%m-%d %H:%M:%S')}")
        y -= 50

        # Insertar gráfico de líneas
        try:
            img_linea = ImageReader(img_linea_path)
            c.drawImage(img_linea, 50, y-200, width=500, height=200, preserveAspectRatio=True)
            y -= 220
        except Exception as e:
            c.drawString(50, y-50, f"Error al cargar gráfico de línea: {str(e)}")
            y -= 70

        # Insertar gráfico de barras
        try:
            img_barras = ImageReader(img_barras_path)
            c.drawImage(img_barras, 50, y-200, width=500, height=200, preserveAspectRatio=True)
        except Exception as e:
            c.drawString(50, y-50, f"Error al cargar gráfico de barras: {str(e)}")

        c.save()
        return output_path