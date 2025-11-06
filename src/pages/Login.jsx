import { useState } from "react";
import { loginMock, isAuth } from "../auth";
import { Navigate, useNavigate } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const nav = useNavigate();
  if (isAuth()) return <Navigate to="/" replace />;

  function onSubmit(e){
    e.preventDefault();
    loginMock(email || "user@teste.com");
    nav("/");
  }

  return (
    <div style={{padding:24}}>
      <h1>Easy Finanças — Login</h1>
      <form onSubmit={onSubmit} style={{display:"grid", gap:8, maxWidth:320}}>
        <input placeholder="e-mail" value={email} onChange={e=>setEmail(e.target.value)}/>
        <input placeholder="senha" type="password"/>
        <button type="submit">Entrar</button>
      </form>
      <p style={{marginTop:12, fontSize:12, color:"#666"}}>
        * Login simulado para fins acadêmicos.
      </p>
    </div>
  );
}
