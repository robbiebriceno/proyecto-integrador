"""
URL configuration for config project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from administracion import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('login/', views.LoginView.as_view(), name='login'),
    path('logout/', views.LogoutView.as_view(), name='logout'),
    path('usuarios/', views.UsuariosListView.as_view(), name='usuarios_list'),
    path('usuarios/crear/', views.UsuarioCreateView.as_view(), name='usuario_create'),
    path('usuarios/eliminar/<int:user_id>/', views.UsuarioDeleteView.as_view(), name='usuario_delete'),
    path('usuarios/registro/', views.UsuarioRegistroView.as_view(), name='usuario_registro'),
    path('login/google/', views.GoogleLoginView.as_view(), name='login_google'),
    path('oauth2/callback/google', views.GoogleCallbackView.as_view(), name='google_callback'),
    path('eventos/', views.EventosListView.as_view(), name='eventos_list'),
    path('publicaciones/', views.PublicacionesListView.as_view(), name='publicaciones_list'),
    path('publicaciones/eliminar/<str:tipo>/<int:pub_id>/', views.PublicacionDeleteView.as_view(), name='publicacion_delete'),
    path('publicaciones/editar/<str:tipo>/<int:pub_id>/', views.PublicacionEditView.as_view(), name='publicacion_edit'),
]
