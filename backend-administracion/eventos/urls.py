from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import EventoViewSet, DepartamentoViewSet, CarreraViewSet, TipoEventoViewSet

router = DefaultRouter()
router.register(r'eventos', EventoViewSet)
router.register(r'departamentos', DepartamentoViewSet)
router.register(r'carreras', CarreraViewSet)
router.register(r'tipos', TipoEventoViewSet)

urlpatterns = [
    path('', include(router.urls)),
]
