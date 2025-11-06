import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import RequireAuth from "./RequireAuth";
import Login from "./pages/Login";
import Home from "./pages/Home";
import ContasListar from "./pages/Contas/Listar";
import ContasNova from "./pages/Contas/Nova";
import ContasEditar from "./pages/Contas/Editar";
import CategoriasListar from "./pages/Categorias/Listar";
import CategoriasNova from "./pages/Categorias/Nova";
import MovListar from "./pages/Movimentacoes/Listar";
import MovNova from "./pages/Movimentacoes/Nova";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import { logout, isAuth } from "./auth";

export default function App() {
  return (
    <BrowserRouter>
      <nav style={{display:"flex", gap:12, padding:12, borderBottom:"1px solid #eee"}}>
        <Link to="/">Home</Link>
        <Link to="/contas">Contas</Link>
        <Link to="/categorias">Categorias</Link>
        <Link to="/movimentacoes">Movimentações</Link>
        {isAuth() && (
          <button
            onClick={() => { logout(); window.location.href = "/login"; }}
            style={{ marginLeft: "auto" }}
          >
            Sair
          </button>
        )}
      </nav>

      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<RequireAuth><Home /></RequireAuth>} />
        <Route path="/contas" element={<RequireAuth><ContasListar /></RequireAuth>} />
        <Route path="/contas/nova" element={<RequireAuth><ContasNova /></RequireAuth>} />
        <Route path="/contas/:id/editar" element={<RequireAuth><ContasEditar /></RequireAuth>} />
        <Route path="/categorias" element={<RequireAuth><CategoriasListar /></RequireAuth>} />
        <Route path="/categorias/nova" element={<RequireAuth><CategoriasNova /></RequireAuth>} />
        <Route path="/movimentacoes" element={<RequireAuth><MovListar /></RequireAuth>} />
        <Route path="/movimentacoes/nova" element={<RequireAuth><MovNova /></RequireAuth>} />
        <Route path="*" element={<div style={{ padding: 24 }}>Página não encontrada.</div>} />
      </Routes>
    </BrowserRouter>
  );
}
