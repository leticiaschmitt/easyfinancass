export function isAuth() {
    return !!localStorage.getItem("ef_token");
  }
  export function loginMock(email) {
    localStorage.setItem("ef_token", email || "user@teste.com");
  }
  export function logout() {
    localStorage.removeItem("ef_token");
  }
  