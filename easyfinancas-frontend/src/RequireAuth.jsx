import { Navigate } from "react-router-dom";

export default function RequireAuth({ children }) {
  const isAuthenticated = localStorage.getItem("token"); // ou outro método de login
  return isAuthenticated ? children : <Navigate to="/login" />;
}
