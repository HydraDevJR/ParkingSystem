const UsuariosList = ({ usuarios, onEdit, onDelete }) => {
    if (usuarios.length === 0) {
        return (
            <div className="rounded-xl border border-slate-200 bg-white p-8 text-center text-slate-500">
                No hay usuarios registrados.
            </div>
        );
    }

    return (
        <div className="rounded-xl border border-slate-200 bg-white shadow-sm overflow-hidden">
            <div className="overflow-x-auto">
                <table className="w-full text-sm">
                    <thead className="bg-slate-50 border-b border-slate-200">
                        <tr>
                            <th className="px-5 py-3 text-left text-slate-600 font-semibold">Documento</th>
                            <th className="px-5 py-3 text-left text-slate-600 font-semibold">Nombre completo</th>
                            <th className="px-5 py-3 text-left text-slate-600 font-semibold">Email</th>
                            <th className="px-5 py-3 text-left text-slate-600 font-semibold">Teléfono</th>
                            <th className="px-5 py-3 text-left text-slate-600 font-semibold">Tipo</th>
                            <th className="px-5 py-3 text-center text-slate-600 font-semibold">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        {usuarios.map((user) => (
                            <tr key={user.id} className="border-b border-slate-100 hover:bg-slate-50">
                                <td className="px-5 py-3">{user.documento}</td>
                                <td className="px-5 py-3">{user.nombre} {user.apellido}</td>
                                <td className="px-5 py-3">{user.email}</td>
                                <td className="px-5 py-3">{user.telefono}</td>
                                <td className="px-5 py-3">{user.tipoUsuario}</td>
                                <td className="px-5 py-3 text-center space-x-2">
                                    <button
                                        onClick={() => onEdit(user)}
                                        className="text-blue-600 hover:text-blue-800 transition-colors"
                                        title="Editar"
                                    >
                                        ✏️
                                    </button>
                                    <button
                                        onClick={() => onDelete(user.id)}
                                        className="text-red-600 hover:text-red-800 transition-colors"
                                        title="Eliminar"
                                    >
                                        🗑️
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default UsuariosList;