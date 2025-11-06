import { useEffect, useState } from "react";
import { api } from "../../api/client";
import { Link } from "react-router-dom";

export default function ContasListar(){
  const [page, setPage] = useState({content:[]});
  useEffect(()=>{ fetchPage(0); },[]);
  function fetchPage(p){ api.get(`/contas?page=${p}&size=10`).then(r=>setPage(r.data)); }
  function del(id){ if(window.confirm("Excluir conta?")) api.delete(`/contas/${id}`).then(()=>fetchPage(0)); }

  return (
    <div style={{padding:24}}>
      <h2>Contas</h2>
      <Link to="/contas/nova">+ Nova Conta</Link>
      <ul>{page.content.map(c=>(
        <li key={c.id}>
          #{c.id} — {c.nome} — Saldo: R$ {Number(c.saldo).toFixed(2)} —
          <Link to={`/contas/${c.id}/editar`}> editar</Link> — 
          <button onClick={()=>del(c.id)}>excluir</button>
        </li>
      ))}</ul>
    </div>
  );
}
