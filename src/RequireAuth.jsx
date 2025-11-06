import { Navigate } from "react-router-dom";
import { isAuth } from "./auth";

export default function RequireAuth({ children }) {
  if (!isAuth()) return <Navigate to="/login" replace />;
  return children;
}
