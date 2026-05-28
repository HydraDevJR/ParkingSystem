import pandas as pd

class DataProcessor:
    @staticmethod
    def procesar_estadias(estadias_data):
        df = pd.DataFrame(estadias_data)
        # Eliminar nulos en columnas clave
        df.dropna(subset=['fechaInicio', 'fechaFin', 'valorTotal'], inplace=True)
        # Convertir fechas
        df['fechaInicio'] = pd.to_datetime(df['fechaInicio'])
        df['fechaFin'] = pd.to_datetime(df['fechaFin'])
        # Extraer día para análisis temporal
        df['dia'] = df['fechaInicio'].dt.date
        # Agrupar ingresos por día
        ingresos_diarios = df.groupby('dia')['valorTotal'].sum().reset_index()
        return df, ingresos_diarios

    @staticmethod
    def agregar_tipo_vehiculo(df, vehiculos_data):
        # Crear diccionario id -> tipoVehiculo
        vehiculos_df = pd.DataFrame(vehiculos_data)
        tipo_map = dict(zip(vehiculos_df['id'], vehiculos_df['tipoVehiculo']))
        # Mapear a cada estadía (asumiendo columna vehiculo.id)
        df['tipoVehiculo'] = df['vehiculo'].apply(lambda v: tipo_map.get(v['id']) if pd.notna(v) else None)
        return df.dropna(subset=['tipoVehiculo'])