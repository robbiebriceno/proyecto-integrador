import { useAuthStore } from "../store/authStore"

export default function Perfil() {
    const { user, logout } = useAuthStore()

    return (
        <div className="container">
            <h2>Mi Perfil</h2>
            {user ? (
                <>
                    <p><strong>Usuario:</strong> {user.username}</p>
                    <p><strong>ID:</strong> {user.user_id}</p>
                    <button onClick={logout}>Cerrar Sesión</button>
                </>
            ) : (
                <p>No se encontró información del usuario.</p>
            )}
        </div>
    )
}
