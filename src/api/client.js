import axios from "axios";

export const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080/api",
  timeout: 10000,
});

// (opcional) interceptor de erro:
api.interceptors.response.use(
  r => r,
  err => {
    const data = err.response?.data;
    const base = data?.error || data?.message || err.message || "Erro";
    const fields = Array.isArray(data?.fields) ? "\n" + data.fields.join("\n") : "";
    err.__pretty = base + fields;
    return Promise.reject(err);
  }
);
