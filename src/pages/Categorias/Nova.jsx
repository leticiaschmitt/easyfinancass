import { useState } from "react";
import { api } from "../../api/client";
import { useNavigate } from "react-router-dom";

export default function CategoriasNova(){
  const nav = useNavigate();
  const [nome, setNome] = useState("");
  const [corHex, setCorHex] = useState("#00BFFF");
  const [tipo, setTipo] = useState("DESPESA");
  const [ativa, setAtiva] = useState(true);

  function salvar(e){
    e.preventDefault();
    api.post("/categorias", { nome, corHex, tipo, ativa })
      .then(()=>nav("/categorias"))
      .catch(e=> alert(e.response?.data?.message || "Erro ao salvar"));
  }

  return (
    <div style={{padding:24}}>
      <h2>Nova Categoria</h2>
      <form onSubmit={salvar} style={{display:"grid", gap:8, maxWidth:360}}>
        <input placeholder="Nome" value={nome} onChange={e=>setNome(e.target.value)} />
        <input placeholder="#RRGGBB" value={corHex} onChange={e=>setCorHex(e.target.value)} />
        <select value={tipo} onChange={e=>setTipo(e.target.value)}>
          <option>ENTRADA</option><option>CUSTO</option><option>DESPESA</option>
        </select>
        <label><input type="checkbox" checked={ativa} onChange={e=>setAtiva(e.target.checked)} /> Ativa</label>
        <button type="submit">Salvar</button>
      </form>
    </div>
  );
}
