from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import EventoViewSet, DepartamentoViewSet, CarreraViewSet, TipoEventoViewSet, EventosPublicosList

router = DefaultRouter()
router.register(r'eventos', EventoViewSet)
router.register(r'departamentos', DepartamentoViewSet)
router.register(r'carreras', CarreraViewSet)
router.register(r'tipos', TipoEventoViewSet)

urlpatterns = [
    path('', include(router.urls)),
    path('eventos-publicos/', EventosPublicosList.as_view(), name='eventos-publicos'),  # << AGREGADO
]
