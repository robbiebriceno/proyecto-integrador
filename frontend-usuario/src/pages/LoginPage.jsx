import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';
import { jwtDecode } from 'jwt-decode';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';

export default function LoginPage() {
  const navigate = useNavigate();

  const handleSuccess = async (credentialResponse) => {
    const decoded = jwtDecode(credentialResponse.credential);
    
    // Validar dominio institucional
    if (!decoded.email.endsWith('@tecsup.edu.pe')) {
      alert('Solo correos institucionales permitidos');
      return;
    }

    try {
      // Enviar token al backend Spring Boot para verificación
      const response = await api.post('/auth/google', {
        token: credentialResponse.credential
      });
      
      localStorage.setItem('token', response.data.token);
      navigate('/dashboard');
    } catch (error) {
      console.error('Error de autenticación:', error);
    }
  };

  return (
    <div className="login-container">
      <h1>Eventos Instituto</h1>
      <GoogleOAuthProvider clientId="TU_CLIENT_ID_GOOGLE">
        <GoogleLogin
          onSuccess={handleSuccess}
          onError={() => console.log('Login Failed')}
          useOneTap
          auto_select
        />
      </GoogleOAuthProvider>
    </div>
  );
}