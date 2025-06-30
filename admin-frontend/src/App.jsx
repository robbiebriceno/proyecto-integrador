import { Routes, Route, Navigate, Outlet } from "react-router-dom"
import Login from "./pages/Login"
import Dashboard from "./pages/Dashboard"
import Departamentos from "./pages/Departamentos"
import Carreras from "./pages/Carreras"
import TipoEventos from "./pages/TipoEventos"
import TodosEventos from "./pages/TodosEventos"
import Perfil from "./pages/Perfil"
import PrivateRoute from "./components/PrivateRoute"
import NavBar from "./components/NavBar"
import Footer from "./components/Footer" // 👈 Agregado

function LayoutConNavYFooter() {
    return (
        <>
            <NavBar />
            <div style={{ padding: "1rem", minHeight: "calc(100vh - 120px)" }}>
                <Outlet />
            </div>
            <Footer /> {/* 👈 Agregado aquí */}
        </>
    )
}

export default function App() {
    return (
        <Routes>
            <Route path="/login" element={<Login />} />

            <Route
                element={
                    <PrivateRoute>
                        <LayoutConNavYFooter />
                    </PrivateRoute>
                }
            >
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/departamentos" element={<Departamentos />} />
                <Route path="/carreras" element={<Carreras />} />
                <Route path="/tipo-eventos" element={<TipoEventos />} />
                <Route path="/eventos" element={<TodosEventos />} />
                <Route path="/perfil" element={<Perfil />} />
            </Route>

            <Route path="/" element={<Navigate to="/login" />} />
            <Route path="*" element={<Navigate to="/login" />} />
        </Routes>
    )
}
