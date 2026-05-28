import matplotlib.pyplot as plt
import seaborn as sns

class ChartGenerator:
    @staticmethod
    def grafico_linea_ingresos(ingresos_diarios, output_path="outputs/grafico_linea.png"):
        plt.figure(figsize=(10, 6))
        plt.plot(ingresos_diarios['dia'], ingresos_diarios['valorTotal'], marker='o', linestyle='-', color='#3498DB')
        plt.title('Evolución de Ingresos Diarios')
        plt.xlabel('Fecha')
        plt.ylabel('Ingresos (COP)')
        plt.xticks(rotation=45)
        plt.tight_layout()
        plt.savefig(output_path)
        plt.close()
        return output_path

    @staticmethod
    def grafico_barras_promedio_por_tipo(df, output_path="outputs/grafico_barras.png"):
        # Calcular promedio de valorTotal por tipo de vehículo
        promedio = df.groupby('tipoVehiculo')['valorTotal'].mean().reset_index()
        plt.figure(figsize=(8, 5))
        sns.barplot(data=promedio, x='tipoVehiculo', y='valorTotal', palette='Blues_d')
        plt.title('Promedio de Ingresos por Tipo de Vehículo')
        plt.xlabel('Tipo de Vehículo')
        plt.ylabel('Valor Promedio (COP)')
        plt.tight_layout()
        plt.savefig(output_path)
        plt.close()
        return output_path