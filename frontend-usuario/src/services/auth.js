import api from './api';

export const isAuthenticated = () => {
  return localStorage.getItem('token') !== null;
};

export const getToken = () => {
  return localStorage.getItem('token');
};

export const logout = () => {
  localStorage.removeItem('token');
  window.location.href = '/login';
};

export const validateInstitutionalEmail = (email) => {
  return email.endsWith('@instituto.edu');
};