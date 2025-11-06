import { useEffect, useState } from "react";
import { api } from "../../api/client";
import { Link } from "react-router-dom";

export default function CategoriasListar(){
  const [page, setPage] = useState({content:[]});
  useEffect(()=>{ fetchPage(0); },[]);
  function fetchPage(p){ api.get(`/categorias?page=${p}&size=10`).then(r=>setPage(r.data)); }
  function del(id){ if(window.confirm("Excluir categoria?")) api.delete(`/categorias/${id}`).then(()=>fetchPage(0)); }

  return (
    <div style={{padding:24}}>
      <h2>Categorias</h2>
      <Link to="/categorias/nova">+ Nova Categoria</Link>
      <ul>{page.content.map(c=>(
        <li key={c.id}>
          #{c.id} — {c.nome} ({c.tipo}) {c.ativa ? "ATIVA" : "INATIVA"} — {c.corHex || "-"}
          <button onClick={()=>del(c.id)} style={{marginLeft:8}}>excluir</button>
        </li>
      ))}</ul>
    </div>
  );
}
