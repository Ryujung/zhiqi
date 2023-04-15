import Cookies from "js-cookie";

const tokenKey = 'Admin-Token'

export function getToken() {
  return Cookies.get(tokenKey)
}

export function setToken(token) {
  Cookies.set(tokenKey, token)
}

export function removeToken() {
  Cookies.remove(tokenKey)
}
