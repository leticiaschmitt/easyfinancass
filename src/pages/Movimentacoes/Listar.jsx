import { useEffect, useState } from "react";
import { api } from "../../api/client";
import { Link } from "react-router-dom";

export default function MovListar(){
  const [page, setPage] = useState({content:[]});
  useEffect(()=>{ fetchPage(0); },[]);
  function fetchPage(p){ api.get(`/movimentacoes?page=${p}&size=10`).then(r=>setPage(r.data)); }
  function del(id){ if(window.confirm("Excluir movimentação?")) api.delete(`/movimentacoes/${id}`).then(()=>fetchPage(0)); }

  return (
    <div style={{padding:24}}>
      <h2>Movimentações</h2>
      <Link to="/movimentacoes/nova">+ Nova Movimentação</Link>
      <ul>{page.content.map(m=>(
        <li key={m.id}>
          #{m.id} — {m.tipo} — R$ {Number(m.valor).toFixed(2)} — {m.data} — Conta:{m.contaId} — Cat:{m.categoriaId || "-"}
          <button onClick={()=>del(m.id)} style={{marginLeft:8}}>excluir</button>
        </li>
      ))}</ul>
    </div>
  );
}
