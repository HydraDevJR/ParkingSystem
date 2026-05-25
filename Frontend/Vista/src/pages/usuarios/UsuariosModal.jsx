import { useState, useEffect } from 'react';

const initialForm = {
    documento: '',
    nombre: '',
    apellido: '',
    email: '',
    password: '',
    telefono: '',
    fechaNacimiento: '',
    genero: '',
    tipoDocumento: '', // se llenará dinámicamente
    tipoUsuario: '',   // se llenará dinámicamente
};

const UsuarioModal = ({ isOpen, onClose, onSave, initialData, tiposDocumento, tiposUsuario }) => {
    const [form, setForm] = useState(initialForm);

    // Efecto para resetear el formulario cuando se abre el modal o cambian los enums
    useEffect(() => {
        if (isOpen) {
            if (initialData) {
                // Edición: cargar datos existentes
                const { password, ...rest } = initialData;
                setForm({
                    ...rest,
                    fechaNacimiento: rest.fechaNacimiento ? rest.fechaNacimiento.split('T')[0] : '',
                    password: '', // no se rellena la contraseña
                });
            } else {
                // Usar los primeros valores de los enums como predeterminados
                setForm({
                    ...initialForm,
                    tipoDocumento: tiposDocumento[0] || '',
                    tipoUsuario: tiposUsuario[0] || '',
                });
            }
        }
    }, [initialData, tiposDocumento, tiposUsuario, isOpen]);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const dataToSend = { ...form };
        if (initialData && !dataToSend.password) {
            delete dataToSend.password;
        }
        onSave(dataToSend);
    };

    // Función para formatear los valores de los enums (ej. "CEDULA_CIUDADANIA" -> "Cédula Ciudadanía")
    const formatEnumOption = (value) => {
        if (!value) return '';
        return value
            .toLowerCase()
            .replace(/_/g, ' ')
            .replace(/\b\w/g, (char) => char.toUpperCase());
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
            <div className="bg-white rounded-xl shadow-xl w-full max-w-md p-6 max-h-[90vh] overflow-y-auto">
                <h2 className="text-xl font-bold text-slate-900 mb-4">
                    {initialData ? 'Editar usuario' : 'Nuevo usuario'}
                </h2>
                <form onSubmit={handleSubmit} className="space-y-3">
                    {/* Campos de texto simple */}
                    <input name="documento" placeholder="Documento" value={form.documento} onChange={handleChange} className="w-full border border-slate-200 rounded-lg p-2" required />
                    <input name="nombre" placeholder="Nombre" value={form.nombre} onChange={handleChange} className="w-full border border-slate-200 rounded-lg p-2" required />
                    <input name="apellido" placeholder="Apellido" value={form.apellido} onChange={handleChange} className="w-full border border-slate-200 rounded-lg p-2" required />
                    <input name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} className="w-full border border-slate-200 rounded-lg p-2" required />
                    <input name="password" type="password" placeholder="Contraseña" value={form.password} onChange={handleChange} className="w-full border border-slate-200 rounded-lg p-2" required={!initialData} />
                    <input name="telefono" placeholder="Teléfono" value={form.telefono} onChange={handleChange} className="w-full border border-slate-200 rounded-lg p-2" required />
                    <input name="fechaNacimiento" type="date" value={form.fechaNacimiento} onChange={handleChange} className="w-full border border-slate-200 rounded-lg p-2" required />

                    <select name="genero" value={form.genero} onChange={handleChange} className="w-full border border-slate-200 rounded-lg p-2" required>
                        <option value="">Género</option>
                        <option>Masculino</option>
                        <option>Femenino</option>
                        <option>Otro</option>
                    </select>

                    {/* Select dinámico para tipoDocumento (consumido del backend) */}
                    <select name="tipoDocumento" value={form.tipoDocumento} onChange={handleChange} className="w-full border border-slate-200 rounded-lg p-2" required>
                        <option value="">Seleccione tipo documento</option>
                        {tiposDocumento.map(tipo => (
                            <option key={tipo} value={tipo}>
                                {formatEnumOption(tipo)}
                            </option>
                        ))}
                    </select>

                    {/* Select dinámico para tipoUsuario (consumido del backend) */}
                    <select name="tipoUsuario" value={form.tipoUsuario} onChange={handleChange} className="w-full border border-slate-200 rounded-lg p-2" required>
                        <option value="">Seleccione tipo usuario</option>
                        {tiposUsuario.map(tipo => (
                            <option key={tipo} value={tipo}>
                                {formatEnumOption(tipo)}
                            </option>
                        ))}
                    </select>

                    <div className="flex justify-end gap-2 mt-4">
                        <button type="button" onClick={onClose} className="px-4 py-2 border rounded-lg hover:bg-slate-50">Cancelar</button>
                        <button type="submit" className="px-4 py-2 bg-[#0A2647] text-white rounded-lg hover:bg-[#0A2647]/90">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default UsuarioModal;