import { useEffect, useState } from "react";
import { api } from "../../api/client";
import { useNavigate } from "react-router-dom";

export default function MovNova(){
  const nav = useNavigate();
  const [contas, setContas] = useState([]);
  const [cats, setCats] = useState([]);

  const [tipo, setTipo] = useState("DESPESA");
  const [valor, setValor] = useState(0);
  const [data, setData] = useState(new Date().toISOString().slice(0,10));
  const [descricao, setDescricao] = useState("");
  const [contaId, setContaId] = useState();
  const [categoriaId, setCategoriaId] = useState();

  useEffect(()=>{
    api.get("/contas?page=0&size=50").then(r=> setContas(r.data.content||[]));
    api.get("/categorias?page=0&size=50").then(r=> setCats(r.data.content||[]));
  },[]);

  function salvar(e){
    e.preventDefault();
    api.post("/movimentacoes", {
      tipo, valor: Number(valor), data, descricao, contaId: Number(contaId), categoriaId: categoriaId? Number(categoriaId): null
    }).then(()=> nav("/movimentacoes"))
      .catch(e=> alert(e.response?.data?.message || "Erro ao salvar"));
  }

  return (
    <div style={{padding:24}}>
      <h2>Nova Movimentação</h2>
      <form onSubmit={salvar} style={{display:"grid", gap:8, maxWidth:420}}>
        <select value={tipo} onChange={e=>setTipo(e.target.value)}>
          <option>ENTRADA</option><option>CUSTO</option><option>DESPESA</option>
        </select>
        <input type="number" step="0.01" value={valor} onChange={e=>setValor(e.target.value)} placeholder="Valor" />
        <input type="date" value={data} onChange={e=>setData(e.target.value)} />
        <input value={descricao} onChange={e=>setDescricao(e.target.value)} placeholder="Descrição" />
        <select value={contaId||""} onChange={e=>setContaId(e.target.value)}>
          <option value="" disabled>Selecione a conta</option>
          {contas.map(c=> <option key={c.id} value={c.id}>#{c.id} — {c.nome}</option>)}
        </select>
        <select value={categoriaId||""} onChange={e=>setCategoriaId(e.target.value)}>
          <option value="">(Opcional) Categoria</option>
          {cats.map(c=> <option key={c.id} value={c.id}>{c.nome} ({c.tipo})</option>)}
        </select>
        <button type="submit">Salvar</button>
      </form>
    </div>
  );
}
