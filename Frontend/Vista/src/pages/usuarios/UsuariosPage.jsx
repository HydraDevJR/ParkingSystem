import { useState, useEffect } from 'react';
import { usuarioService } from '../../services/usuarioService';
import { enumService } from '../../services/enumService';
import UsuariosList from '../usuarios/UsuariosList';
import UsuarioModal from '../usuarios/UsuariosModal';
import { 
    showSuccessAlert, 
    showHttpErrorAlert, 
    showConfirmAlert 
} from '../../helpers/alerts';

const UsuariosPage = () => {
    const [usuarios, setUsuarios] = useState([]);
    const [loading, setLoading] = useState(true);
    const [modalOpen, setModalOpen] = useState(false);
    const [editingUser, setEditingUser] = useState(null);

    // Estados para los enums
    const [tiposDocumento, setTiposDocumento] = useState([]);
    const [tiposUsuario, setTiposUsuario] = useState([]);
    const [enumsLoading, setEnumsLoading] = useState(true);

    useEffect(() => {
        cargarUsuarios();
        cargarEnums();
    }, []);

    const cargarUsuarios = async () => {
        setLoading(true);
        try {
            const data = await usuarioService.getAll();
            setUsuarios(data);
        } catch (error) {
            console.error(error);
            showHttpErrorAlert(error, 'No se pudieron cargar los usuarios');
        } finally {
            setLoading(false);
        }
    };

    const cargarEnums = async () => {
        setEnumsLoading(true);
        try {
            const [tDoc, tUsr] = await Promise.all([
                enumService.getTiposDocumento(),
                enumService.getTiposUsuario(),
            ]);
            setTiposDocumento(tDoc);
            setTiposUsuario(tUsr);
        } catch (error) {
            console.error('Error cargando enums para usuarios', error);
            showHttpErrorAlert(error, 'No se pudieron cargar los tipos de documento o usuario');
        } finally {
            setEnumsLoading(false);
        }
    };

    const handleCreate = () => {
        setEditingUser(null);
        setModalOpen(true);
    };

    const handleEdit = (user) => {
        setEditingUser(user);
        setModalOpen(true);
    };

    const handleDelete = async (id) => {
        const confirmed = await showConfirmAlert(
            'Este usuario perderá sus datos relacionados (vehículos, estadías). ¿Deseas continuar?',
            'Eliminar usuario'
        );
        if (!confirmed) return;

        try {
            await usuarioService.delete(id);
            showSuccessAlert('Usuario eliminado correctamente');
            cargarUsuarios(); // recargar lista
        } catch (error) {
            showHttpErrorAlert(error);
        }
    };

    const handleSave = async (userData) => {
        try {
            if (editingUser) {
                await usuarioService.update(editingUser.id, userData);
                showSuccessAlert('Usuario actualizado correctamente');
            } else {
                await usuarioService.create(userData);
                showSuccessAlert('Usuario creado correctamente');
            }
            setModalOpen(false);
            cargarUsuarios(); // recargar lista
        } catch (error) {
            showHttpErrorAlert(error);
        }
    };

    if (loading || enumsLoading) {
        return (
            <div className="flex justify-center items-center h-64">
                <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-[#3498DB]"></div>
            </div>
        );
    }

    return (
        <div className="space-y-6">
            <div className="flex justify-between items-center">
                <div>
                    <h1 className="text-slate-900 text-2xl font-bold">Usuarios</h1>
                    <p className="text-slate-600">Gestión de propietarios de vehículos</p>
                </div>
                <button
                    onClick={handleCreate}
                    className="bg-[#0A2647] text-white px-4 py-2 rounded-lg text-sm font-semibold hover:bg-[#0A2647]/90"
                >
                    + Nuevo usuario
                </button>
            </div>

            <UsuariosList
                usuarios={usuarios}
                onEdit={handleEdit}
                onDelete={handleDelete}
            />

            <UsuarioModal
                isOpen={modalOpen}
                onClose={() => setModalOpen(false)}
                onSave={handleSave}
                initialData={editingUser}
                tiposDocumento={tiposDocumento}
                tiposUsuario={tiposUsuario}
            />
        </div>
    );
};

export default UsuariosPage;