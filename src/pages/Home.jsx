import { useEffect, useState } from "react";
import { api } from "../api/client";

export default function Home(){
  const [contas, setContas] = useState([]);
  useEffect(()=>{
    api.get("/contas?page=0&size=50").then(res=>{
      setContas(res.data.content || []);
    });
  },[]);
  const saldoTotal = contas.reduce((acc,c)=> acc + Number(c.saldo||0), 0);

  return (
    <div style={{padding:24}}>
      <h1>Dashboard</h1>
      <div>Saldo total (todas as contas): <b>R$ {saldoTotal.toFixed(2)}</b></div>
    </div>
  );
}
