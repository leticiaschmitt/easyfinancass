import { useEffect, useState } from "react";
import { api } from "../../api/client";
import { useNavigate, useParams } from "react-router-dom";

export default function ContasEditar(){
  const { id } = useParams();
  const nav = useNavigate();
  const [nome, setNome] = useState("");
  const [saldo, setSaldo] = useState(0);

  useEffect(()=>{
    api.get(`/contas/${id}`).then(r=>{
      setNome(r.data.nome);
      setSaldo(r.data.saldo);
    }).catch(()=>nav("/contas"));
  },[id, nav]);

  function salvar(e){
    e.preventDefault();
    api.put(`/contas/${id}`, { nome, saldo })
      .then(()=> nav("/contas"));
  }

  return (
    <div style={{padding:24}}>
      <h2>Editar Conta #{id}</h2>
      <form onSubmit={salvar} style={{display:"grid", gap:8, maxWidth:320}}>
        <input value={nome} onChange={e=>setNome(e.target.value)} />
        <input type="number" step="0.01" value={saldo} onChange={e=>setSaldo(e.target.value)} />
        <button type="submit">Salvar</button>
      </form>
    </div>
  );
}
