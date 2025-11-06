import { useState } from "react";
import { api } from "../../api/client";
import { useNavigate } from "react-router-dom";

export default function ContasNova(){
  const [nome, setNome] = useState("");
  const [saldoInicial, setSaldoInicial] = useState(0);
  const nav = useNavigate();

  function submit(e){
    e.preventDefault();
    api.post("/contas", { nome, saldoInicial: Number(saldoInicial) })
      .then(()=> nav("/contas"));
  }

  return (
    <div style={{padding:24}}>
      <h2>Nova Conta</h2>
      <form onSubmit={submit} style={{display:"grid", gap:8, maxWidth:320}}>
        <input placeholder="Nome" value={nome} onChange={e=>setNome(e.target.value)} />
        <input placeholder="Saldo Inicial" type="number" step="0.01" value={saldoInicial} onChange={e=>setSaldoInicial(e.target.value)} />
        <button type="submit">Salvar</button>
      </form>
    </div>
  );
}
