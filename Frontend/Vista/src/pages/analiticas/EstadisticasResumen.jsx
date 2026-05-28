import { formatCurrency } from '../../utils/formatters';

const EstadisticasResumen = ({ data }) => {
    const { totalIngresos, totalEstadias, promedioDiario } = data;
    return (
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-4">
            <div className="bg-white rounded-xl border border-slate-200 p-4 text-center">
                <p className="text-slate-500 text-sm">Ingresos totales</p>
                <p className="text-2xl font-bold text-slate-900">{formatCurrency(totalIngresos)}</p>
            </div>
            <div className="bg-white rounded-xl border border-slate-200 p-4 text-center">
                <p className="text-slate-500 text-sm">Estadías registradas</p>
                <p className="text-2xl font-bold text-slate-900">{totalEstadias}</p>
            </div>
            <div className="bg-white rounded-xl border border-slate-200 p-4 text-center">
                <p className="text-slate-500 text-sm">Ingreso promedio diario</p>
                <p className="text-2xl font-bold text-slate-900">{formatCurrency(promedioDiario)}</p>
            </div>
        </div>
    );
};

export default EstadisticasResumen;